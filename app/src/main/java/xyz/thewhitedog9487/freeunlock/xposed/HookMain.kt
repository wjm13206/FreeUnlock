package xyz.thewhitedog9487.freeunlock.xposed

import android.util.Log
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.SystemServerStartingParam

class HookMain : XposedModule() {
    companion object {
        const val TAG = "FreeUnlock"
        const val TargetClassName = "com.android.server.locksettings.LockSettingsStrongAuth"
        const val TargetMethodName = "rescheduleStrongAuthTimeoutAlarm"
    }

    override fun onSystemServerStarting(param: SystemServerStartingParam) {
        val targetClass = Class.forName(TargetClassName, true, param.classLoader)
        val targetMethod = targetClass.getDeclaredMethod(TargetMethodName, Long::class.java, Int::class.java)

        hook(targetMethod).intercept {
            log(Log.INFO, TAG, "成功拦截强认证调度请求。")
            null
        }
    }
}
