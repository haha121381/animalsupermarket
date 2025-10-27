param(
	[string]$Remote = "origin",
	[string]$Branch = $(git rev-parse --abbrev-ref HEAD 2>$null),
	[switch]$Force
)

# 用法: .\scripts\force-push.ps1 -Remote origin -Branch main [-Force]
if (-not (Test-Path ".git")) {
	Write-Error "当前目录不是 git 仓库。"
	exit 1
}

if (-not $Branch) {
	$Branch = git rev-parse --abbrev-ref HEAD
}

Write-Host "Remote: $Remote"
Write-Host "Branch: $Branch"

$porcelain = git status --porcelain
if ($porcelain) {
	Write-Host "警告：存在未提交的本地更改："
	Write-Host $porcelain
	$confirm = Read-Host "继续并可能丢失本地状态？(y/N)"
	if ($confirm -ne 'y' -and $confirm -ne 'Y') {
		Write-Host "已取消"
		exit 1
	}
}

# 尝试 fetch
git fetch $Remote $Branch 2>$null | Out-Null

Write-Host "`n---- BEGIN remote..local log ----"
try {
	git --no-pager log --oneline "$Remote/$Branch"..HEAD
} catch {
	Write-Host "(无差异或远程分支不存在)"
}
Write-Host "----  END  remote..local log ----`n"

if ($Force) {
	Write-Host "将执行：git push $Remote HEAD:$Branch --force"
} else {
	Write-Host "将执行：git push $Remote HEAD:$Branch --force-with-lease"
}

$ans = Read-Host "确认执行？此操作会覆盖远程历史！输入 'yes' 继续"
if ($ans -ne "yes") {
	Write-Host "已取消"
	exit 1
}

if ($Force) {
	git push $Remote "HEAD:$Branch" --force
} else {
	git push $Remote "HEAD:$Branch" --force-with-lease
}

Write-Host "完成。"
