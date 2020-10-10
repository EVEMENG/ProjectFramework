package com.zmz.projectframework.ui;

import com.zmz.projectframework.base.BaseActivity;
import com.zmz.projectframework.base.BaseFragment;
import com.zmz.projectframework.R;

public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment setRootFragment() {
        return null;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
