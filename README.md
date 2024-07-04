插件简介
此插件可以通过WebSocket对接 OneBot 以及 Mirai 机器人框架
对接 Mirai 机器人请自行安装 mirai-api-http 插件
推荐使用Mirai机器人框架，若实在不会搭建可以使用 OneBot标准 的机器人

此插件因只作为一个前置Api使用所以只有一个绑定功能且默认关闭

为什么是对接而不是把机器人直接写入插件？

如果是直接将机器人写入插件可能会导致多服服主或多端服主头疼
因为这样你可能需要准备更多的QQ号
但如果是直接接入框架本身的话
首先你也能正常使用框架本身的插件并且还能对接服务器

插件功能
此插件使用 WebSocket 进行QQ消息操作
所以必须通过指令连接一次 WebSocket 才能正常使用
在配置文件中可以开启自动连接，但需要提前开启 OneBot 或 Mirai 机器人
绑定信息支持 MySQL 储存，若不开启则为本地文件储存

此插件作为单独使用的话只有一个绑定玩家功能
而这个绑定功能也没有任何实质性作用
但是可以通过其他附属插件达到想要的作用

兼容核心
插件只在下列核心进行测试，正常使用，其它服务端核心未知，请自行测试。

1.8.8版本：Paper、Spigot
1.12.2版本：CatServer、spigot
1.16.5版本：CatServer、spigot
1.19.2版本：paper、Spigot
1.20.2版本：mohist、arclight

指令权限&介绍
管理员指令介绍（所需权限：XinxinBotApi.admin）

/xbot bind [QQ] [玩家ID] —— 手动绑定一个QQ
/xbot delbind [QQ] —— 删除一个QQ的绑定账号
/xbot connect —— 连接WebSocket
/xbot close —— 断开WebSocket
/xbot reload —— 重载插件

开始对接机器人框架
当安装好插件后我们只需要将插件连接任意OneBot协议机器人或Mirai机器人即可正常使用
我也非常贴心的为各位准备了3个框架的安装以及对接教程
各位根据教程安装好任意一个框架并对接插件后即可正常使用辣！
目前个人建议使用的框架 LLOneBot 安装对接教程

LLOneBot 安装对接教程
本文档为我个人制作，可能不适用于所有人
如果此教程不能满足你请前往 LLOneBot

安装QQ
先前往 QQ官网 下载最新版的QQNT也就是QQ
下载安装好后进入下一步

Windows 用户一键安装方案
下载exe，双击运行即可，之后打开QQ的设置，看到了 LLOneBot 就代表安装成功了.

通用安装方法
安装 LiteLoaderQQNT
注意在安装之前请先关闭 QQ ！

推荐使用 一键安装脚本 支持 Windows、Linux、MacOS

安装成功后打开QQ的设置，看到了 LiteLoaderQQNT 就代表安装成功了.

手动安装见 LiteLoaderQQNT 文档

安装 LLOneBot
下载 LLOneBot 最新版本 解压放到 plugins 目录下，然后重启 QQ 即可.

plugins 目录可以在 LiteLoaderQQNT 的页面找到并一键打开.

检查是否安装成功
重启 QQ 后，打开设置，看到 LLOneBot ，代表安装成功.

对接 XinxinBotApi
当我们成功安装好 LLOneBot 插件在QQNT上后
需要打开服务端 XinxinBotApi 插件的 config.yml 配置文件将我们的 BotFrame 设置为 OneBot

然后将 LLOneBot 配置项中的WebSocket 端口写入 XinxinBotApi 配置中

#对接go-cqhttp需要设置的配置
OneBot:
  #go-cqhttp的config内设置的WebSocket地址+端口
  WebSocket: "ws://127.0.0.1:3001"
  #机器人设置的AccessToken，默认是无的
  AccessToken: ""
  #go-cqhttp的config内设置的Http地址+端口
  Http: "http://127.0.0.1:3000"
填写好配置文件后重载XinxinBotApi插件
然后发送 /xbot connect 若出现下方提示则代表对接成功

[Server thread/INFO]: [新鑫互联] ➥ 已启动WebSocket连接，开始监听事件.
[WebSocketConnectReadThread-95896/INFO]: [新鑫互联] 连接成功
想要每次启动服务端都能自动连接机器人的话需要在config.yml文件设置 AutoOpen: true

如果你不知道你的WebSocket端口是多少只需要打开QQ设置点击 LLOneBot 即可查看

此时，LLOneBot机器人对接已完成，你已可以使用所有附属插件

Mirai 安装对接教程
本文档为我个人制作，可能不适用于所有人
如果此教程不能满足你请前往 Mirai用户手册

必备环境安装
使用Mirai机器人必须使用Java11+启动，所以可以前往官网下载
https://www.oracle.com/cn/java/technologies/javase/jdk11-archive-downloads.html

或者加入我们QQ交流群 1124109145 直接在群文件免登录下载

下载好后我们双击安装包进行安装，一路回车即可

机器人的下载
我们先前往Github下载 console-runtime 整合包

5849678f5f39b92d8cd881aec2f91051.png

登录账号
下载完成后解压整合包，双击start.cmd启动机器人，当机器人启动好后我们输入

/login qq qq密码 [登录协议]

这里登录协议是可选性，一般默认即可
如果无法登录可以尝试更换其他登录协议直到可以登录为止
若都无法登录请跳转到【本地签名部署教程】

使用命令后将滑块验证地址复制然后前往 ticket获取

I/Bot.1072565329: [SliderCaptcha] Captcha link: https://ssl.captcha.qq.com/template/xxx
将获取到的 ticket 粘贴并回车即可登录成功
如出现需要认证的信息, 请自行认证设备。

I/Bot.1072565329: Login successful.
V/Bot.1072565329: Event: BotOnlineEvent(bot=Bot(1072565329))
I/Bot.1072565329: Bot login successful.
此时, Mirai机器人登录已完成
若无法登录请跳转到【本地签名部署教程】

设置机器人自动登录
我们在机器人框架输入 /autologin add qq 密码
然后发送stop关闭机器人框架后续就可以自动登录了

对接 XinxinBotApi
如果你不是在交流群下载的整合包的话
需要自行安装 mirai-api-http 插件并进行简单的配置

当我们启动好 Mirai 框架并成功登录后
需要打开服务端 XinxinBotApi 插件的 config.yml 配置文件将我们的 BotFrame 设置为 Mirai

然后将 Mirai 配置项中的 QQ 填写上你需要对接的机器人QQ号
并将Verifykey 和 WebSocket 设置为机器人 mirai-api-http 插件对应的值

#对接Mirai需要设置的配置
#若对接其他接口则无需设置
Mirai:
  #机器人所登陆的QQ
  QQ: "123456789"
  #mirai-api-http中设置的verifyKey
  VerifyKey: "1072565329"
  #mirai-api-http中设置的WebSocket地址
  WebSocket: "ws://127.0.0.1:1125"
填写好配置文件后重载 XinxinBotApi 插件
然后发送 /xbot connect 若出现下方提示则代表对接成功

[Server thread/INFO]: [新鑫互联] ➥ 已启动WebSocket连接，开始监听事件.
[WebSocketConnectReadThread-95896/INFO]: [新鑫互联] 连接成功
如果想要每次启动服务端都能自动连接机器人的话需要在config.yml文件设置 AutoOpen: true

如果你不知道你的ws地址和Verifykey是多少，你可以将你机器人窗口翻到最顶部
可以看到 mirai-api-http 启动提示，按照这上面显示填写即可

[ws adapter] is listening at ws://127.0.0.1:1125
Http api server is running with verifyKey: 1072565329
此时，Mirai机器人对接已完成，你已可以使用所有附属插件

go-cqhttp 安装对接教程（框架已停更）
本文档为我个人制作，可能不适用于所有人
如果此教程不能满足你请前往 go-cqhttp用户手册

机器人的下载
首先前往 官方release 界面
下载最新版本对应自己系统的go-cqhttp

Windows下32位文件为 go-cqhttp-v*-windows-386.zip
Windows下64位文件为 go-cqhttp-v*-windows-amd64.zip
Windows下arm用(如使用高通CPU的笔记本)文件为 go-cqhttp-v*-windows-arm.zip
Linux下32位文件为 go-cqhttp-v*-linux-386.tar.gz
Linux下64位文件为 go-cqhttp-v*-linux-amd64.tar.gz
Linux下arm用(如树莓派)文件为 go-cqhttp-v*-linux-arm.tar.gz
MD5文件为 *.md5 ，用于校验文件完整性
启动
双击go-cqhttp.exe此时将提示时
未找到配置文件，正在为您生成配置文件中！
请选择你需要的通信方式:
> 0: HTTP通信
> 1: 云函数服务
> 2: 正向 Websocket 通信
> 3: 反向 Websocket 通信
请输入你需要的编号(0-9)，可输入多个，同一编号也可输入多个(如: 233)
您的选择是:
我们输入 02 选择 HTTP通信 和 正向 Websocket 通信

您的选择是:02
默认配置文件已生成，请修改 config.yml 后重新启动!
打开根目录下config.yml填写所需的参数
（一般情况下我们只用填写uin(QQ)和password(密码)这两项就可以了）
再次双击运行脚本将会提示
[WARNING]: 登录需要滑条验证码, 请验证后重试.
[2024-02-08 02:05:50] [WARNING]: 请选择提交滑块ticket方式:
[2024-02-08 02:05:50] [WARNING]: 1. 自动提交
[2024-02-08 02:05:50] [WARNING]: 2. 手动抓取提交
[2024-02-08 02:05:50] [WARNING]: 请输入(1 - 2)：
我们只需要输入2 选择 手动抓取提交 将他提供的地址复制然后前往 ticket获取

[WARNING]: 请前往该地址验证 -> https://ssl.captcha.qq.com/template/xxxx
[2024-02-08 02:05:53] [WARNING]: 请输入ticket： (Enter 提交)
将获取到的 ticket 粘贴并回车即可登录成功
如出现需要认证的信息, 请自行认证设备。

[INFO]: 登录成功 欢迎使用: balabala
此时, go-cqhttp机器人登录已完成
若无法登录请跳转到【本地签名部署教程】

对接 XinxinBotApi
当我们启动好 go-cqhttp 框架并成功登录后
需要打开服务端 XinxinBotApi 插件的 config.yml 配置文件将我们的 BotFrame 设置为 OneBot

然后将 Go-cqhttp 配置项WebSocket 和 Http 设置为机器人启动时配置的值

#对接 OneBot 需要设置的配置
OneBot:
  #go-cqhttp的config内设置的WebSocket地址+端口
  WebSocket: "ws://127.0.0.1:6700"
  #go-cqhttp的config内设置的Http地址+端口
  Http: "http://127.0.0.1:5700"
填写好配置文件后重载XinxinBotApi插件
然后发送 /xbot connect 若出现下方提示则代表对接成功

[Server thread/INFO]: [新鑫互联] ➥ 已启动WebSocket连接，开始监听事件.
[WebSocketConnectReadThread-95896/INFO]: [新鑫互联] 连接成功
如果想要每次启动服务端都能自动连接机器人的话需要在config.yml文件设置 AutoOpen: true

如果你不知道你的WebSocket地址和Http地址是多少
可以看下机器人登录成功提示，按照这上面填写即可

WebSocket默认值一般为: ws://127.0.0.1:8080
Http默认值一般为: http://127.0.0.1:5700
[INFO]: CQ WebSocket 服务器已启动: [::]:8080
[INFO]: CQ HTTP 服务器已启动: [::]:5700
此时，go-cqhttp机器人对接已完成，你已可以使用所有附属插件

ticket获取
我们将复制的地址通过浏览器打开 （这里我使用的Edge）
在网页加载完成后按 F12 打开发者工具，选择右侧的 网络（部分浏览器可能显示是Network）


我们正常的进行验证后,右侧会出现 cap_union_new_verify 的配置项
复制 ticket 里面的内容，粘贴在机器人后台回车即可



本地签名部署教程
如果不想配置本地签名可以加入我们QQ交流群 1124109145
我已经配置好签名的整合包(可以跳过搭建本地签名)

适用情况
登录Mirai机器人出现下方提示
code=45, title=禁止登录, message=登录失败，请前往QQ官网im.qq.com下载最新版QQ后重试，或通过问题反馈与我们联系。, errorInfo=)
或者启动go-cqhttp出现下方提示
[WARNING]: 登录失败: 登录失败，请前往QQ官网im.qq.com下载最新版QQ后重试，或通过问题反馈与我们联系。 Code: 45
[WARNING]: 你的账号被限制登录, 请配置 SignServer 后重试
安装本地签名
这里我本地签名软件是用GitHub上 CikeyQi 制作的 unidbg-fetch-qsign 项目

Windows可视化部署管理Qsign服务
Linux一键快速部署Qsign服务
我这边使用 Windows 来演示如何搭建本地签名

首先前往 Releases 下载最新版本的.zip文件并解压，放置在一个 高性能 的 Windows 服务器上
双击打开.exe文件，点击 添加启动列表，按提示配置好各项
启动成功后，双击API地址列会复制API地址，直接使用即可
如需修改配置项，点击相应列即可设置

Mirai 使用方法
打开 \Project\txlib 找到与你签名服务器版本一致的文件夹进入
复制该文件夹下的 android_phone.json 文件粘贴到 Mirai 更目录下
再打开 KFCFactory.json 文件将签名版本对应的配置写入其中
保存即可重新登录

例如我使用的协议为8.9.93 的文件内容
{
    "8.9.93": {
        "base_url": "http://127.0.0.1:8080",
        "type": "fuqiuluo/unidbg-fetch-qsign",
        "key": "114514"
    }
}
go-cqhttp 使用方法
打开 \Project\txlib 找到与你签名服务器版本一致的文件夹进入
复制该文件夹下的 android_phone.json 文件重命名为 6.json
放至gocq根目录下的 data/versions 文件夹中
再打开gocq的配置文件 config.yml 找到 sign-servers 将签名版本对应的配置写入其中
authorization 请不要填写，保持默认值-即可
保存即可重新登录

例如我使用的协议为8.9.93 的文件内容
sign-servers:
  - url: 'http://127.0.0.1:8080'  # 主签名服务器地址， 必填
    key: '114514'  # 签名服务器所需要的apikey, 如果签名服务器的版本在1.1.0及以下则此项无效
    authorization: '-'   # authorization 内容, 依服务端设置，如 'Bearer xxxx'
⚠️注意：如果是在另一个服务器部署的Qsign请修改 127.0.0.1 为远程服务器公网IP地址，如果配置了签名运行端口请把 8080 修改成对应端口，如果修改了签名密钥请更改 114514 为你设置的密钥，没有修改默认为 114514

结尾
到这里，你已经可以正常使用 XinxinBotApi 和他的 附属插件 了
若想自行开发 XinxinBotApi 附属可 查看插件接口
想要 定制附属 或 付费安装机器人 也可以 联系作者QQ: 1072565329

