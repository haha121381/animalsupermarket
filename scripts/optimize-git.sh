#!/usr/bin/env bash
# 一键为当前仓库设置 Git 局部优化（在仓库根执行）

set -e

echo "设置本地 git 配置以加速 push/pull（只影响当前仓库）"

# 增大上传缓冲（注意：过大可能导致其它问题，根据网络调整）
git config --local http.postBuffer 524288000
git config --local http.maxRequestBuffer 524288000

# 在传输大文件时关闭压缩可以减少 CPU 开销（加快网络密集型操作）
git config --local core.compression 0

# 控制 pack 参数
git config --local pack.windowMemory 100m
git config --local pack.threads 4

# 接收端可调（如果需要）
git config --local receive.unpackLimit 1000

echo "已应用："
git config --local --list | grep -E 'http.postBuffer|maxRequestBuffer|core.compression|pack.windowMemory|pack.threads|receive.unpackLimit' || true

echo
echo "建议："
echo " - 尽量使用 SSH（git@github.com:...），减少 HTTPS 的认证/握手延迟。"
echo " - 对历史大文件使用 git-lfs（安装后：git lfs install && git lfs track \"*.png\" …）。"
echo " - 对于仅需当前分支的 CI 或开发，可使用浅克隆：git clone --depth=1 <repo>。"
echo
echo "完成。"
