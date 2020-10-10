package com.zmz.projectframework.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.zmz.projectframework.R;
import com.zmz.projectframework.base.BaseFragment;
import com.zmz.projectframework.base.BaseTimerTask;
import com.zmz.projectframework.base.inter.ITimerListener;
import com.zmz.projectframework.inter.ILoginState;
import com.zmz.projectframework.utils.AppPreference;

import java.text.MessageFormat;
import java.util.Timer;


/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.fragment
 * @ClassName: LaunchFragment
 * @Description: 启动展示页Fragment
 * @Author: zmz
 * @CreateDate: 2020/4/8 11:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 11:55
 * @UpdateRemark: 更新说明
 * @Version: 1.6.7
 */
public class LaunchFragment extends BaseFragment implements ITimerListener, View.OnClickListener {

    private AppCompatTextView launcher_timer_tv;

    private Timer mTimer = null;
    private int mCount = 3;
    private ILoginState mILoginState;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILoginState) {
            mILoginState = (ILoginState) activity;
        }
    }

    @Override
    public Object getResId() {
        return R.layout.fragment_launch;
    }

    @Override
    public void onInitView(@Nullable Bundle savedInstanceState) {
        launcher_timer_tv = (AppCompatTextView) findViewById(R.id.launcher_timer_tv);
        launcher_timer_tv.setOnClickListener(this);
    }

    @Override
    public void onInitData(@Nullable Bundle savedInstanceState) {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (launcher_timer_tv != null) {
                    launcher_timer_tv.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    private void checkIsShowScroll() {
//        if (AppPreference.getIntance().getIsFirstLogin()) {//判断是否第一次登陆
//            getSupportDelegate().startWithPop(new GuideFragment());
//        } else {
        //检查用户是否登陆了APP
        if (AppPreference.getIntance().getAccountLonginState()) {
            if (mILoginState != null) {
                mILoginState.enterHomePage();
            }
        } else {
            if (mILoginState != null) {
                mILoginState.enterLoginPage();
            }
        }
//        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.launcher_timer_tv) {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScroll();
            }
        }
    }
}

