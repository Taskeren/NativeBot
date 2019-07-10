# NativeBot 开发帮助

## 关于向本项目贡献代码
1. 和其他项目一样，先给自己的账户 Fork 一份，然后添加完代码后向主仓库提交 Pull-Request。
1. 要求代码格式遵循《Google Java Style Guide》。
1. 要求在你添加的代码周围添加上代码注释，和作者名称。
1. 在改动原有代码时，向原作者商议。

## 关于用户系统
用户系统的核心为两个用户实例，分别为`UserTencent`和`UserMinecraft`，这两个用户实例分别管理游戏内和游戏外的账号，一个是 Minecraft 账号，一个是 QQ 账号。

#### 1. 获取用户的账号实例
```java
// player 是 org.bukkit.entity.Player
// 传入的数据是玩家的游戏名（没有任何样式）
UserMinecraft um = UserMinecraft.of(player.getName());

// user 是 cc.moecraft.icq.user.User
// 传入的数值是用户QQ号
UserTencent ut = UserTencent.of(user.getUserId());

// 也可以根据 UserMinecraft 绑定的QQ号来获取 UserTencent
UserTencent ut = um.getTencentUser();
```

#### 2. 设置和获取 UserMinecraft 的QQ号
```java
UserMinecraft um = UserMinecraft.of("Taskeren"); // 先获取UM实例
um.setTencentId(3070190799L); // 传入的是QQ号，类型是长整数
long qqid = um.getTencentId(); // 获取到的QQ号是长整数，如果没有设置则返回 -1L
```

## 关于权限系统
权限系统主要是使用`SimpleDataStorage`进行存储，`UserTencent`和`UserMinecraft`所获取和设置的权限都是存于`UserTencent`中，如果没有绑定QQ号的`UserMinecraft`读取权限，则返回`-1`为QQ号的错误`UserTencent`，即永远返回`false`。

#### 1. 获取和设置权限
`UserTencent`和`UserMinecraft`使用完全相同，因为`UserMinecraft`的`setPermision(String, boolean)`和`getPermission(String)`两个方法都是`UserTencent`的代理方法。
```java
UserTencent ut = UserTencent(3070190799L); // 先获取UT实例
ut.setPermission("chatting.minecraft", true); // 传入权限名称和设置的值
boolean isAuthorized = ut.getPermission("chatting.tencent"); // 传入权限名称
```

#### 2. 注册机器人指令，机器人监听器，机器人权限
新建一个 Bukkit 的监听类，然后监听 EventBotRegistration 事件。
```java
public class RegistrationListener implements Listener {

    public RegistrationListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, PLUGIN);
    }

    @EventHandler
    public void onRegistrationEvent(EventBotRegistration evt) {
        evt.registerCommand(commands); // 注册指令
        evt.registerListeners(listeners); // 注册监听器
        evt.registerPermission(permissions); // 注册权限
    }

}
```