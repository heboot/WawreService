package com.wawre.service.in;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by Heboot on 16/6/1.
 */
public interface IBaseActivity {

    /**
     * 获取 Application 对象
     */
    public abstract Application getApplication();

    /**
     * 判断是否有网络连接
     */
    public abstract boolean validateInternet();

    /**
     * 显示Toast
     */
    public abstract void showToast(String content,int duration);




}
