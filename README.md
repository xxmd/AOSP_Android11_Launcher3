# Launcher桌面接入负一屏功能

## 步骤

### 引入 launcher_client.jar

- 往[libs](libs) 目录中加入jar包和mk文件声明
- 在[Android.mk](Android.mk)文件的 ``LOCAL_STATIC_JAVA_LIBRARIES`` 变量中加入 lib_launcher_client 库

### 自定义负一屏管理类，实现如下接口

| 接口类名                                   | 所处位置                | 作用                                  | 备注 |
|----------------------------------------|---------------------|-------------------------------------|----|
| LauncherOverlayManager                 | Launcher3源码         | 桌面浮动层整体管理（涉及浮动层创建，绑定，开关）            |    |
| LauncherOverlayManager.LauncherOverlay | Launcher3源码         | 桌面浮动层滚动事件回调（滚动开始，进行中，结束），属于桌面->负一屏向 |    |
| LauncherClientCallbacks                | launcher_client.jar | 浮动层事件回调（服务状态和滚动进度变更），属于负一屏->桌面向     |    |

自定义负一屏管理类示例

```java
// 建议使用 【品牌名称+LauncherOverlayManager】 作为类名
public class ElfunLauncherOverlayManager implements LauncherOverlayManager, LauncherOverlayManager.LauncherOverlay, LauncherClientCallbacks {

}
```

### 将自定义负一屏管理类注册至 Launcher 中

```java
package com.android.launcher3.uioverrides;

import com.elfun.magazine.ElfunLauncherOverlayManager;

public class QuickstepLauncher extends BaseQuickstepLauncher {
    // 通过复写 getDefaultOverlay 方法，注入我们自定义的负一屏管理类
    @Override
    protected LauncherOverlayManager getDefaultOverlay() {
        return new ElfunLauncherOverlayManager(this);
    }
}
```

### 通过 LauncherClient 实现所有方法

LauncherClient初始化和方法覆写详见该类：
[ElfunLauncherOverlayManager.java](quickstep/recents_ui_overrides/src/com/elfun/magazine/ElfunLauncherOverlayManager.java)
