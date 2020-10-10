package com.zmz.projectframework.base.inter;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 *
 */

public interface IFragment {

    void onInitView(@Nullable Bundle savedInstanceState);

    void onInitData(@Nullable Bundle savedInstanceState);

    Object getResId();

    boolean isSwipeBack();
}
