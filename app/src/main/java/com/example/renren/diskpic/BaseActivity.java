package com.example.renren.diskpic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends Activity implements ITitleBar{
    private MyTitleBar mTitlebar;
    private BaseActivity currentActivity;
    private TextView titleTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitlebar = new MyTitleBar(this);
        Methods.setDensity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        mTitlebar.setTitleBarListener(this);
        LinearLayout l1 = new LinearLayout(this);
        l1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        l1.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = (int)getResources().getDimension(R.dimen.titlebar_height);
        l1.addView(mTitlebar, layoutParams);


        View mainView = LayoutInflater.from(this).inflate(layoutResID,null);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainView.setLayoutParams(layoutParams2);
        l1.addView(mainView);
        this.setContentView(l1);

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_base, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View getLeftView(Context context, ViewGroup parentView) {
        View v = TitleBarUtil.getLeftBackBtn(context);
        if (v!=null){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.finish();
                }
            });
        }
        return v;
    }

    @Override
    public View getMiddleView(Context context, ViewGroup parentView) {
        if (titleTV ==null){
            titleTV = TitleBarUtil.getTitleView(context);
        }
        return titleTV;
    }

    @Override
    public View getRightView(Context context, ViewGroup parentView) {
        return null;
    }

    public void setTitle(String title){
        if(titleTV!=null){
            titleTV.setText(title);
        }
    }

}
