package com.zmz.projectframework.config;

import android.app.Activity;

import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * APP的配置文件
 * Created by longshao on 2017/7/22.
 */

public class Configurator {
    private static final HashMap<Object, Object> LONG_CONFIG = new HashMap<>();
    private static final ArrayList<LoggerInterceptor> INTERCEPTORS = new ArrayList<>();//拦截器

    private Configurator() {
        LONG_CONFIG.put(ConfigType.CONFIG_REDAY, false);//APP还未配置完成
    }

    public static Configurator getIntance() {
        return Builder.INTANCE;
    }

    public final HashMap<Object, Object> getLongConfig() {
        return LONG_CONFIG;
    }

    /**
     * 初始化配置服务器公用的地址
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LONG_CONFIG.put(ConfigType.API_HOST, host);
        return this;
    }

    /**
     * 初始化配置服务器公用的地址2
     *
     * @param otherHost
     * @return
     */
    public final Configurator withApiOhterHost(String otherHost){
        LONG_CONFIG.put(ConfigType.OTHER_HOST, otherHost);
        return this;
    }

    /**
     * 配置微信的APPID
     *
     * @param appId
     * @return
     */
    public final Configurator withWeChatAPPID(String appId) {
        LONG_CONFIG.put(ConfigType.WXCHAT_APP_ID, appId);
        return this;
    }

    /**
     * 配置微信的私钥
     *
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatSecret(String appSecret) {
        LONG_CONFIG.put(ConfigType.WXCHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * 配置QQ的APPID
     *
     * @param appId
     * @return
     */
    public final Configurator withQQAppId(String appId) {
        LONG_CONFIG.put(ConfigType.QQ_APP_ID, appId);
        return this;
    }

    /**
     * 配置当前的activity
     *
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        LONG_CONFIG.put(ConfigType.ACTIVITY, activity);
        return this;
    }


    /**
     * 加入测试拦截器
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(LoggerInterceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LONG_CONFIG.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    /**
     * 加入测试拦截器
     *
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<LoggerInterceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LONG_CONFIG.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置完成
     */
    public final void configure() {
        LONG_CONFIG.put(ConfigType.CONFIG_REDAY, true);//设置初始化完成
    }


    @SuppressWarnings("unused")
    public final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LONG_CONFIG.get(key);
        if (value == null)
            return null;
        return (T) LONG_CONFIG.get(key);
    }

    /**
     * 实现单例
     */
    private static class Builder {
        private static final Configurator INTANCE = new Configurator();
    }


    /**
     * 检查是否初始化配置
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LONG_CONFIG.get(ConfigType.CONFIG_REDAY);
        if (!isReady) {
            throw new RuntimeException("Configurator is not ready,call Configurator");
        }
    }
}
