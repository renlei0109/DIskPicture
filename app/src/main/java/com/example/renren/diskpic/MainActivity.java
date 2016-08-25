package com.example.renren.diskpic;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("匹配");
    }

    @Override
    public View getLeftView(Context context, ViewGroup parentView) {
        return null;
    }


}
