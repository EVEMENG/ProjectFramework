package com.zmz.projectframework.base.utils;

import android.content.Context;

/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.utils
 * @ClassName: UtilApp
 * @Description: java类作用描述
 * @Author: zmz
 * @CreateDate: 2020/4/8 11:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 11:35
 * @UpdateRemark: 更新说明
 * @Version: 1.6.7
 */
public class UtilApp {
    private Context mContext;

    private static final class Holder {
        private static final UtilApp INSTANCE = new UtilApp();
    }

    public static UtilApp getIntance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        mContext = context;

//        /*初始化log打印器*/
//        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public Context getApplicationContext() {
        if (mContext == null)
            throw new RuntimeException("UtilApp is not init");
        return mContext;
    }
}
