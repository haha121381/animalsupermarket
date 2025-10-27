#!/usr/bin/env bash
set -e

# 简短说明：在仓库根目录执行：./scripts/force-push.sh [remote] [branch] [--force]
# remote 默认 origin，branch 默认当前分支。默认使用 --force-with-lease（更安全）。

REMOTE=${1:-origin}
BRANCH=${2:-$(git rev-parse --abbrev-ref HEAD)}
PLAIN_FORCE=false
if [[ "$3" == "--force" || "$2" == "--force" || "$1" == "--force" ]]; then
	PLAIN_FORCE=true
fi

echo "Remote: $REMOTE"
echo "Branch: $BRANCH"

# 确保是一个 git 仓库
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
	echo "错误：当前目录不是 git 仓库。"
	exit 1
fi

# 检查未提交改动
if [[ -n "$(git status --porcelain)" ]]; then
	echo "警告：存在未提交的本地更改。请先提交或 stash。"
	git status --porcelain
	read -p "继续并可能丢失本地状态？(y/N) " yn
	case "$yn" in
		[Yy]*) ;;
		*) echo "已取消"; exit 1;;
	esac
fi

# 获取远程分支引用（如果不存在则提示）
if ! git ls-remote --exit-code "$REMOTE" "refs/heads/$BRANCH" >/dev/null 2>&1; then
	echo "注意：远程分支 $REMOTE/$BRANCH 不存在，操作将创建远程分支。"
fi

# 展示将被推送的提交差异（远程..本地）
echo
echo "即将推送（远程 -> 本地之间的差异）："
git fetch "$REMOTE" "$BRANCH" --quiet || true
echo "---- BEGIN remote..local log ----"
git --no-pager log --oneline "$REMOTE/$BRANCH"..HEAD || echo "(无差异或远程分支不存在)"
echo "----  END  remote..local log ----"
echo

# 确认
echo "将要执行的命令："
if $PLAIN_FORCE; then
	echo "git push $REMOTE HEAD:$BRANCH --force"
else
	echo "git push $REMOTE HEAD:$BRANCH --force-with-lease"
fi
read -p "确认执行？此操作会覆盖远程历史！(type 'yes' to proceed): " ans
if [[ "$ans" != "yes" ]]; then
	echo "已取消"
	exit 1
fi

# 执行推送
if $PLAIN_FORCE; then
	git push "$REMOTE" "HEAD:$BRANCH" --force
else
	git push "$REMOTE" "HEAD:$BRANCH" --force-with-lease
fi

echo "完成。请注意检查远程仓库状态。"
