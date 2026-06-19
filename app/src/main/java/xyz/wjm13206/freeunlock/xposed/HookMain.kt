package xyz.wjm13206.freeunlock.xposed

import android.util.Log
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.SystemServerStartingParam

class HookMain : XposedModule() {
    companion object {
        const val TAG = "FreeUnlock"
        private const val TARGET_CLASS = "com.android.server.locksettings.LockSettingsStrongAuth"
        private const val TARGET_METHOD = "rescheduleStrongAuthTimeoutAlarm"
    }

    override fun onSystemServerStarting(param: SystemServerStartingParam) {
        try {
            val targetClass = param.classLoader.loadClass(TARGET_CLASS)
            val targetMethod = targetClass.getDeclaredMethod(
                TARGET_METHOD, Long::class.java, Int::class.java
            )

            deoptimize(targetMethod)

            hook(targetMethod).intercept {
                log(Log.INFO, TAG, "成功拦截强认证调度请求。")
                null
            }
        } catch (e: Throwable) {
            log(Log.ERROR, TAG, "Hook 初始化失败", e)
        }
    }
}
