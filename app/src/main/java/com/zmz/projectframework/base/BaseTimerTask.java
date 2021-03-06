package com.zmz.projectframework.base;


import com.zmz.projectframework.base.inter.ITimerListener;

import java.util.TimerTask;

/**
 * Created by longshao on 2017/8/1.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
