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
    public   Application getApplication();

    /**
     * 判断是否有网络连接
     */
    public   boolean validateInternet();

    /**
     * 显示Toast
     */
    public   void showToast(String content,int duration);




}
