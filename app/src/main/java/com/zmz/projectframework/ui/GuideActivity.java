package com.zmz.projectframework.ui;

import android.content.Intent;

import com.zmz.projectframework.R;
import com.zmz.projectframework.base.BaseActivity;
import com.zmz.projectframework.base.BaseFragment;
import com.zmz.projectframework.inter.ILoginState;
import com.zmz.projectframework.ui.fragment.LaunchFragment;

/**
 * @ProjectName: StudentStandAlone
 * @Package: com.zmz.studentstandalone.activity
 * @ClassName: GuideActivity
 * @Description: 启动页面
 * @Author: zmz
 * @CreateDate: 2020/4/8 10:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/8 10:40
 * @UpdateRemark: 更新说明
 * @Version: 1.6.7
 */
public class GuideActivity extends BaseActivity implements ILoginState {

    @Override
    public BaseFragment setRootFragment() {
        return new LaunchFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_layout;
    }

    @Override
    public void enterLoginPage() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    public void enterHomePage() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
