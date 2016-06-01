package com.wawre.service.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.wawre.service.R;
import com.wawre.service.in.IBaseActivity;
import com.wawre.service.utils.NetWorkUtils;

/**
 * Created by Heboot on 16/6/1.
 */
public class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private SuperToast superToast;
    private LinearLayout rootLayout;
    private TextView toolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolBarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        }
    }

    protected void setToolBarTitle(String title) {
        if (toolBarTitle != null) {
            toolBarTitle.setText(title);
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    /**
     * 判断是否有网络连接
     */
    @Override
    public boolean validateInternet() {
        return NetWorkUtils.isConnectedByState(this);
    }

    /**
     * 显示Toast
     */
    @Override
    public void showToast(String content, int duration) {
        initSuperToast();
        superToast.setDuration(duration);
        superToast.setText(content);
        superToast.show();

    }

    /**
     * 显示ToolBar
     */
    protected void showToolBar(String title, boolean displayHomeAsUpEnabled, Integer homeAsUpResId) {
        setToolBarTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (displayHomeAsUpEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (homeAsUpResId != null) {
                getSupportActionBar().setHomeAsUpIndicator(homeAsUpResId);
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }


    /**
     * 初始化SuperToast
     */
    private void initSuperToast() {
        if (superToast == null) {
            superToast = new SuperToast(this);
            superToast.setAnimations(SuperToast.Animations.FLYIN);
            superToast.setTextSize(SuperToast.TextSize.SMALL);
            superToast.setBackground(SuperToast.Background.RED);
            superToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
        }
    }
}
