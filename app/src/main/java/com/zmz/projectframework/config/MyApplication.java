package com.zmz.projectframework.config;

import android.app.Application;
import android.content.Context;

/**
 * @ProjectName: MyFramework
 * @Package: com.zmz.projectframework.config
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: zmz
 * @CreateDate: 2020/9/10 18:10
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/9/10 18:10
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 全局获取context
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
