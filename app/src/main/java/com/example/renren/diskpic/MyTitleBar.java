package com.example.renren.diskpic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by renlei
 * DATE: 16-1-6
 * Time: 上午11:01
 * Email: lei.ren@renren-inc.com
 */
public class MyTitleBar extends ViewGroup {
    private LinearLayout mLeftContainer;
    private LinearLayout mMiddleContainer;
    private LinearLayout mRightContainer;

    private View mLeftView;
    private View mMiddleView;
    private View mRightView;
    private int titleBarHeight;
    private ITitleBar mITitleBar;
    private Context mContext;

    public MyTitleBar(Context context) {
        this(context, null, 0);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initTitleBar(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int resetWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (mLeftView != null) {
            mLeftContainer.measure(resetWidth + MeasureSpec.AT_MOST, titleBarHeight + MeasureSpec.EXACTLY);
        }
        if (mRightView != null) {
            mRightContainer.measure(resetWidth - mLeftContainer.getMeasuredWidth() + MeasureSpec.AT_MOST, titleBarHeight + MeasureSpec.EXACTLY);
        }

        if (mMiddleView != null) {
            mMiddleContainer.measure(resetWidth - 2 * Math.max(mLeftContainer.getMeasuredWidth(), mRightContainer.getMeasuredWidth()) + MeasureSpec.EXACTLY, titleBarHeight + MeasureSpec.EXACTLY);
        }
        setMeasuredDimension(resetWidth, titleBarHeight);//设置自身的宽高，新定义的viewgroup是没有宽高的
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        if (mLeftContainer != null) {
            mLeftContainer.layout(0, 0, mLeftContainer.getMeasuredWidth(), titleBarHeight);
        }

        if (mRightContainer != null) {
            mRightContainer.layout(width - mRightContainer.getMeasuredWidth(), 0, width, titleBarHeight);
        }

        if (mMiddleContainer != null) {
            mMiddleContainer.layout(width / 2 - mMiddleContainer.getMeasuredWidth() / 2, 0, width / 2 + mMiddleContainer.getMeasuredWidth() / 2, titleBarHeight);
        }
    }

    private void initTitleBar(Context context) {
        titleBarHeight = (int) context.getResources().getDimension(R.dimen.titlebar_height);
        this.setBackgroundColor(context.getResources().getColor(R.color.titlebar_bg_color));
        mLeftContainer = new LinearLayout(context);
        mMiddleContainer = new LinearLayout(context);
        mRightContainer = new LinearLayout(context);

        mLeftContainer.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mMiddleContainer.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
        mRightContainer.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

        this.addView(mLeftContainer);
        this.addView(mMiddleContainer);
        this.addView(mRightContainer);

        if (mITitleBar != null) {
            mLeftView = mITitleBar.getLeftView(context, mLeftContainer);
            mMiddleView = mITitleBar.getMiddleView(context, mMiddleContainer);
            mRightView = mITitleBar.getRightView(context, mRightContainer);
        }
    }

    public void setTitleBarListener(ITitleBar l){
        if (l!=null){
            this.mITitleBar = l;
            mLeftContainer.removeAllViews();
            mRightContainer.removeAllViews();
            mMiddleContainer.removeAllViews();

            mLeftView = mITitleBar.getLeftView(mContext,mLeftContainer);
            mRightView = mITitleBar.getRightView(mContext,mRightContainer);
            mMiddleView = mITitleBar.getMiddleView(mContext,mRightContainer);

            if(mLeftView!=null){
                mLeftContainer.addView(mLeftView);
            }
            if (mRightView!=null){
                mRightContainer.addView(mRightView);
            }

            if (mMiddleView!=null){
                mMiddleContainer.addView(mMiddleView);
            }
            requestLayout();
        }
    }
}
