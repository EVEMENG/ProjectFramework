package com.zmz.projectframework.utils;

import com.zmz.projectframework.base.utils.PreferenceUtil;

public class AppPreference {
    private static AppPreference preference;

    public final static AppPreference getIntance() {
        synchronized (AppPreference.class) {
            if (preference == null) {
                preference = new AppPreference();
            }
        }
        return preference;
    }

    /**
     * 设置是否第一次登陆
     *
     * @param islogin
     */
    public final void setFirstLogin(boolean islogin) {
        PreferenceUtil.getIntance().setBoolean(APPTag.IS_FRIST_ENTER.name(), islogin);
    }

    /**
     * 获取是否第一次登陆
     *
     * @return
     */
    public final boolean getIsFirstLogin() {
        return PreferenceUtil.getIntance().getBoolean(APPTag.IS_FRIST_ENTER.name(), true);
    }

    /**
     * 设置用户登录状态
     *
     * @param state
     */
    public final void setAccountLonginState(boolean state) {
        PreferenceUtil.getIntance().setBoolean(APPTag.LOGIN_STATE.name(), state);
    }

    /**
     * 获取用户登录状态
     *
     * @return
     */
    public final boolean getAccountLonginState() {
        return PreferenceUtil.getIntance().getBoolean(APPTag.LOGIN_STATE.name(), false);
    }


//    /**
//     * 保存登录用户数据
//     *
//     * @param model
//     */
//    public final void setAccountData(UserModel model) {
//        PreferenceUtil.getIntance().saveObject(APPTag.ACCOUNT_DATA.name(), model);
//    }
//
//    /**
//     * 获取登录用户数据
//     *
//     * @return
//     */
//    public final UserModel getAccountData() {
//        return (UserModel) PreferenceUtil.getIntance().readObject(APPTag.ACCOUNT_DATA.name());
//    }

    /**
     * 保存登录用户密码
     *
     * @param password
     */
    public final void setAccountPassword(String password) {
        PreferenceUtil.getIntance().setString(APPTag.ACCOUNT_PASSWORD.name(), password);
    }

    /**
     * 获取登录用户密码
     *
     * @return
     */
    public final String getAccountPassword() {
        return PreferenceUtil.getIntance().getString(APPTag.ACCOUNT_PASSWORD.name());
    }

    /**
     * 保存登录用户账号
     *
     * @param name
     */
    public final void setAccountName(String name) {
        PreferenceUtil.getIntance().setString(APPTag.ACCOUNT_NAME.name(), name);
    }

    /**
     * 获取登录用户账号
     *
     * @return
     */
    public final String getAccountName() {
        return PreferenceUtil.getIntance().getString(APPTag.ACCOUNT_NAME.name());
    }

    /**
     * 保存登录用户头像
     *
     * @param name
     */
    public final void setAccountHead(String name) {
        PreferenceUtil.getIntance().setString(APPTag.ACCOUNT_HEAD.name(), name);
    }

    /**
     * 获取登录用户头像
     *
     * @return
     */
    public final String getAccountHead() {
        return PreferenceUtil.getIntance().getString(APPTag.ACCOUNT_HEAD.name());
    }
}
