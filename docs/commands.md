# NativeBot 指令大全

## Minecraft 指令
#### /qq
- 权限：nativebot.qq （默认任何玩家拥有）
- 用途：玩家自行绑定QQ号
- 用法：/qq <QQ号>

#### /qq-admin
- 权限：nativebot.admin （默认OP拥有）
- 用途：管理员绑定和改绑玩家所绑定的QQ号
- 用法：/qq-admin <QQ号> <玩家ID>

## CoolQ 指令
#### /help 或 /about 或 /bot
- 权限：null
- 用途：显示机器人指令（群）

#### /op
- 权限：null
- 用途：查看自己是否是OP

#### /op
- 权限：OP
- 用途：设置QQ号为OP
- 用法：/op <QQ号>

#### /perm
- 权限：OP
- 用途：查看用户是否拥有权限
- 用法：/perm <QQ号> <权限名称>

#### /perm
- 权限：OP
- 用途：给予或剥夺用户权限
- 用法：/perm <QQ号> <权限名称> <true/false>
- 备注：`true`代表给予，`false`代表剥夺

#### /console
- 权限：OP
- 用途：以控制台（Console）的身份执行指令
- 用法：/console <指令>
- 备注：返回只有`成功`和`失败`，如果需要更多信息，请自行到控制台查看