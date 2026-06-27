<h1 align="center"> 自由解锁-fork </h1>

<p align="center"> 让屏幕解锁再次迅速 </p>

## 功能
禁用Android中每72小时必须使用密码解锁屏幕的限制

## 使用方式
1. 安装一个支持 Modern Xposed API 的框架（如 LSPosed）
2. 在 [Github Release](https://github.com/wjm13206/FreeUnlock/releases/latest) 中下载最新的 Release 版本 APK
3. 安装 APK
4. 打开 Xposed 管理器，启用模块并**勾选系统框架（包名 android）作为作用域**
5. 重启设备
6. 完成

重启后首次用密码解锁设备，可在 Lsposed 管理器的日志中看到模块输出的一条日志。

## 支持的 Android 版本
不明确，具体情况请自行测试 (也许所有支持每72小时必须使用密码解锁屏幕的安卓都支持?)
已确认功能有效的设备：
- Redmi K30 5G Picasso（Project Infinity X v3.5 | Android 16 Baklava | 由 ManukaTM 提供的非官方构建）
- Realme GT8 RMX6699（realme ui 7.0 16.0.2.473 | Android 16 Baklava | 国行官方系统）

## 实现机制
模块通过 Modern Xposed API 的拦截器链，拦截 `LockSettingsStrongAuth.rescheduleStrongAuthTimeoutAlarm`，阻止系统设置强认证计时器。没有计时器就不会触发每 72 小时的密码强认证。


## 与上游的区别
本仓库基于 [FreeUnlock](https://github.com/TheWhiteDog9487/FreeUnlock) 进行 fork，已将 Xposed API 升级至 API 102：
- 使用 API102 替代遗留 legacy
- 基于拦截器链的 Hook 机制（OkHttp 风格）
- 原生支持热重载、Invoker、RemotePreferences 等新特性

## 感谢
- [HyperCeiler](https://github.com/ReChronoRain/HyperCeiler) 提供的注入点参考
- [libxposed](https://github.com/libxposed/api) 提供的  API
- [FreeUnlock](https://github.com/TheWhiteDog9487/FreeUnlock) 原作者提供的原始实现参考

## 开源许可
WTFPL