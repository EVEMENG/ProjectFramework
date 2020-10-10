package com.zmz.projectframework.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zmz.projectframework.R;
import com.zmz.projectframework.base.inter.IFragment;
import com.zmz.projectframework.base.inter.IPermissonResultListener;
import com.zmz.projectframework.base.utils.LogUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 基础fragment
 * Created by zmz on 2017/3/23.
 * 说明一下：getSupportFragmentManager() 是在activity中获取fragment堆栈管理对象
 * getFragmentManager()是在fragment中获取父级fragment堆栈管理对象，如果没有父亲级就是获取的是activity中的fragment堆栈管理对象
 * getChildFragmentManager()是获取当前fragment的fragment堆栈管理对象
 */

public abstract class BaseFragment<P extends BasePresenter> extends SwipeBackFragment implements IFragment {
    @IntDef({EMPTY_VIEW, MAIN_VIEW, ERROR_VIEW, NONET_VIEW})
    public @interface ViewType {
    }

    public static final int EMPTY_VIEW = 0x123;
    public static final int MAIN_VIEW = 0x124;
    public static final int ERROR_VIEW = 0x125;
    public static final int NONET_VIEW = 0x126;

    protected String TAG = "";
    private Toast cdToast;

    protected View mView;
    protected P mPresenter;

    public BaseFragment() {
        try {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<P> pClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                Constructor[] constructors = pClass.getDeclaredConstructors();
                constructors[0].setAccessible(true);
                mPresenter = pClass.newInstance();
                mPresenter.attachView(this);
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
    }

    /**
     * 创建对象
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachContext(getActivity());
        }
    }

    /**
     * 创建fragment视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getResId() instanceof Integer) {
            mView = inflater.inflate((Integer) getResId(), container, false);
        } else if (getResId() instanceof View) {
            mView = (View) getResId();
        } else {
            throw new RuntimeException("getResId() must Integer or View");
        }
        hideSoftInput();
        if (isSwipeBack()) {
            return attachToSwipeBack(mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusBarCompat.setStatusBarColor(_mActivity, ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        try {
            onInitView(savedInstanceState);
        } catch (Exception e) {
            if (e.getClass().equals(NullPointerException.class)) {
                LogUtil.e(TAG,"如果你使用了toolbar，你需要在onInitView()继承super.onInitView();");
            } else {
                e.printStackTrace();
            }
        }
        onInitData(savedInstanceState);
    }

    /**
     * 当activity中的onCreate()方法返回的时候调用，一般用在横竖屏切换
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        hideSoftInput();
//        MobclickAgent.onPause(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        invokeFragmentManagerNoteStateNotSaved();
    }

    /**
     * 当fragment与activity取消关联的时候调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 当使用add()+show()，hide()跳转新的Fragment时，旧的Fragment回调onHiddenChanged()，
     * 不会回调onStop()等生命周期方法，而新的Fragment在创建时是不会回调onHiddenChanged()
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            StatusBarCompat.setStatusBarColor(_mActivity, _mActivity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    /**
     * * 表示懒加载
     *
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    /**
     * @param isVisibleToUser 如果true表示当前fragment可见，如果表示false表示隐藏
     *                        一般这里可以用于处理数据的请求，当改fragment处于可见状态下去请求网络数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.popenter_anim, R.anim.popexit_anim, R.anim.enter_anim, R.anim.exit_anim);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }

    public void pop() {
        getBaseActivity().onBackPressedSupport();
    }

    protected <T extends View> T findViewById(int resId) {
        if (resId <= 0) {
            return (T) mView;
        } else {
            return (T) mView.findViewById(resId);
        }
    }

    /**
     * 发送提示消息
     *
     * @param message
     */
    public void showToast(String message) {
        if (TextUtils.isEmpty(message) || message.indexOf("timeout") != -1 || message.indexOf("time out") != -1)
            return;
        if (cdToast == null) {
            cdToast = Toast.makeText(_mActivity, message, Toast.LENGTH_SHORT);
        } else {
            cdToast.setText(message);
            cdToast.setDuration(Toast.LENGTH_SHORT);
        }
        cdToast.show();
    }

    /**
     * 显示输入法
     *
     * @param editText
     */
    public void showSoftInput(EditText editText) {
        editText.requestFocus();
        editText.setFocusable(true);
        InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏输入法
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getBaseActivity().getWindow().peekDecorView();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) _mActivity;
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        super.setSwipeBackEnable(true);
    }

    @Override
    public boolean isSwipeBack() {
        return false;
    }


    /*权限处理模块开始*/
    private IPermissonResultListener permisslistener;
    private List<String> noRequstList = new ArrayList<>();
    private final static int REQUEST_PERMISSON_CODE = 0x999;

    public void onRequestPermisson(String[] perminss, IPermissonResultListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permisslistener = listener;
            noRequstList.clear();
            for (String item : perminss) {
                if (getContext().checkSelfPermission(item) != PackageManager.PERMISSION_GRANTED) {
                    noRequstList.add(item);
                }
            }
            if (noRequstList.size() > 0) {
                requestPermissions(noRequstList.toArray(new String[noRequstList.size()]), REQUEST_PERMISSON_CODE);
            } else {
                if (listener != null)
                    listener.onSuccess();
            }
        } else {
            if (listener != null)
                listener.onSuccess();
        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSON_CODE:
                final int size = noRequstList.size();
                List<String> resultList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        resultList.add(noRequstList.get(i));
                    }
                }
                onDealResult(resultList);
                break;
        }
    }

    private void onDealResult(List<String> list) {
        if (list.size() == 0) {
            if (this.permisslistener != null)
                this.permisslistener.onSuccess();
        } else {
            if (this.permisslistener != null)
                this.permisslistener.onFail(list);
        }
    }

    /*权限处理模块结束*/
    public final void showView(@ViewType int viewType) {
        showView(viewType, "");
    }

    public void showView(@ViewType int viewType, String message) {

    }
}
