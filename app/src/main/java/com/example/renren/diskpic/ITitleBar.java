package com.example.renren.diskpic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by renlei
 * DATE: 16-1-6
 * Time: 上午11:06
 * Email: lei.ren@renren-inc.com
 */
public interface ITitleBar {
    public View getLeftView(Context context, ViewGroup parentView);
    public View getMiddleView(Context context, ViewGroup parentView);
    public View getRightView(Context context, ViewGroup parentView);
}
