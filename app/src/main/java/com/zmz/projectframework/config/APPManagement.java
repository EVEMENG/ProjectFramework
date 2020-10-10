package com.zmz.projectframework.config;

import android.content.Context;
/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.app
 * @ClassName: APPManagement
 * @Description: 用于管理整个APP的类
 * @Author: zmz
 * @CreateDate: 2020/4/8 10:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 10:31
 * @UpdateRemark: 更新说明
 * @Version: 1.6.7
 */
public final class APPManagement {

    public static Configurator init(Context context) {
        getConfigurator().getLongConfig().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getIntance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getIntance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
}
