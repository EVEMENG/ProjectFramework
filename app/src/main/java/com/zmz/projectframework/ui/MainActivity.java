package com.zmz.projectframework.ui;

import com.zmz.projectframework.base.BaseActivity;
import com.zmz.projectframework.base.BaseFragment;
import com.zmz.projectframework.R;
import com.zmz.projectframework.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment setRootFragment() {
        return new MainFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.activity_layout;
    }
}
