package com.zmz.projectframework.base.utils;

import android.util.Log;

import com.zmz.projectframework.utils.Contants;
/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.utils
 * @ClassName: LogUtil
 * @Description: Log工具类
 * @Author: zmz
 * @CreateDate: 2020/4/8 9:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 9:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogUtil {
    public static boolean showDebugLog = Contants.IS_LOG_DEBUG;

    public static void i(Object objTag, Object objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? "null" : objMsg.toString();
        Log.i(tag, getMsgFormat(msg));
    }

    public static void d(Object objTag, Object objMsg) {
        if (showDebugLog) {
            String tag = getTag(objTag);
            String msg = (objMsg == null || objMsg.toString() == null) ? "null" : objMsg.toString();
            Log.d(tag, getMsgFormat(msg));
        }
    }

    public static void w(Object objTag, Object objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? "null" : objMsg.toString();
        Log.w(tag, getMsgFormat(msg));
    }

    public static void e(Object objTag, Object objMsg) {
        String tag = getTag(objTag);
        String msg = (objMsg == null || objMsg.toString() == null) ? "null" : objMsg.toString();
        Log.e(tag, getMsgFormat(msg));
    }

    private static String getTag(Object objTag) {
        String tag;
        if (objTag instanceof String) {
            tag = (String) objTag;
        } else if (objTag instanceof Class) {
            tag = ((Class<?>) objTag).getSimpleName();
        } else {
            tag = objTag.getClass().getSimpleName();
        }
        return tag;
    }

    /**
     * 获取类名,方法名,行号
     *
     * @return Thread:main, at com.haier.ota.OTAApplication.onCreate(OTAApplication.java:35)
     */
    private static String getFunctionName() {
        try {
            StackTraceElement[] sts = Thread.currentThread().getStackTrace();
            if (sts != null) {
                for (StackTraceElement st : sts) {
                    if (st.isNativeMethod()) {
                        continue;
                    }
                    if (st.getClassName().equals(Thread.class.getName())) {
                        continue;
                    }
                    if (st.getClassName().equals(LogUtil.class.getName())) {
                        continue;
                    }
                    return "Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                            + "(" + st.getFileName() + ":" + st.getLineNumber() + ")";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMsgFormat(String msg) {
        return msg + "----" + getFunctionName();
    }

}
