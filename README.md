# 强制推送脚本说明（安全提示）

仓库内提供两个脚本：
- scripts/force-push.sh  （类 Unix / Git Bash）
- scripts/force-push.ps1 （Windows PowerShell）

用途：在确认后执行强制推送到指定 remote/branch。默认使用更安全的 --force-with-lease。  
示例：
- Linux/macOS/Git-Bash: ./scripts/force-push.sh origin main
- Windows PowerShell: .\scripts\force-push.ps1 -Remote origin -Branch main

风险提示：强制推送会覆盖远程历史，可能导致他人工作丢失。请务必在团队内确认并备份重要历史。
