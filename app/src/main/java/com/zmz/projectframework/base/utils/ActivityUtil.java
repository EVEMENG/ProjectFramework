package com.zmz.projectframework.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.utils
 * @ClassName: ActivityUtil
 * @Description: 管理activity工具类
 * @Author: zmz
 * @CreateDate: 2020/4/8 10:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 10:28
 * @UpdateRemark: 更新说明
 * @Version: 1.6.7
 */
public class ActivityUtil {
    private static final String TAG = "ActivityUtil";
    private static Stack<Activity> activityStack;
    private static ActivityUtil instance;

    private ActivityUtil() {

    }

    public static ActivityUtil getAppManager() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    /**
     * 添加Activity 到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack == null) {
            return;
        }
        activityStack.remove(activity);
    }

    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();

    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity();
            }
        }
    }

    /**
     * 结束所有的Activity、
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            LogUtil.e(TAG,"退出程序异常");
        }
    }
}
