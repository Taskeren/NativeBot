# NativeBot 安装教程

## 配置酷Q
1. 从[酷Q官网](https://cqp.cc)下载酷QAir。（只需要Air就行了，Pro版要收费的，而且本插件不需要任何Pro版的内容！）
1. 解压后启动酷Q，等待下载需要的库与其他的内容。
1. 下载完毕后在`/app`里应该会出现一些文件，这些是酷Q自带的插件，不需要，全删掉！
1. 然后配置 `CoolQ-HTTP-API`。

## 配置CQ-HTTP-API
1. 从[CoolQ-HTTP-API](https://github.com/richardchien/coolq-http-api/releases)的项目中下载编译好的二进制文件。
1. 将这个文件放到`/app`中。
1. 启动一次酷Q，启用这个插件，然后关掉酷Q。（如果启动正常的话，应该会有一个黑色窗口出现）
1. 进到`/data/app/io.github.richardchien.coolqhttpapi/`中，新建`config.cfg`。
1. 向这个配置文件中填入如下文本：
```
[general]
host=0.0.0.0
port=插件里配置的发送消息到酷Q的端口号
post_url=http://MC服务器IP:插件里配置的接受酷Q消息的端口号
enable_backward_compatibility=false
```
1. 保存，退出，启动酷Q。
1. 接下来配置插件。

## 配置NativeBot
1. 将NativeBot的二进制文件下载，并放入服务器的`/plugins`文件夹中。
1. 启动一次服务器，然后关闭。（一般会出现一片报错，没关系，正常操作）
1. 进到服务器根目录下的`/NativeBot`文件夹中，打开`config.cfg`。
1. 修改`group`的值为**你服务器群的群号**。
1. 修改`in`的值为**接受酷Q消息的端口号**。
1. 修改`out`的值为**发送消息到酷Q的端口号**。
1. 修改`url`的值为**酷Q服务器的IP**。（如果两个都在同一台电脑上，则填写 `127.0.0.1`）
1. 然后再启动游戏，这时候如果配置正确就不会再报错了。

**以上，就是配置NativeBot的全部内容。**