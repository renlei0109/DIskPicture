/**
 * 
 */
package com.example.renren.diskpic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
/**
 * 右侧带有清空按钮的文本编辑框
 * <p>
 * <p>
 * 使用方法：
 * <p>
 * 将layout.xml中的 EditText 换成
 * com.renren.mobile.android.view.EditTextWithClearButton 即可。
 * <p>
 * <p>
 * layout中可使用的属性：
 * <p>
 * clearButtonOffset: 清空按钮的左右偏移，正值往右偏移，负值往左偏移。不定义该属性时， 清空按钮在离上、下、右三个边边缘等距离的位置绘制；
 * <p>
 * clearButtonDrawable: 清空按钮的绘图 Drawable 素材，可定制清空按钮行为样式；
 * 
 */
public class EditTextWithClearButton extends EditText implements
        OnTouchListener {

    private Drawable mClearButtonDrawable;

    private int mClearButtonOffset;

    private OnTouchListener mOnTouchListener;

    private OnClickListener mOnClearButtonClickListener;

    public OnClickListener getOnClearButtonClickListener() {
        return mOnClearButtonClickListener;
    }

    public void setOnClearButtonClickListener(OnClickListener l) {
        this.mOnClearButtonClickListener = l;
    }

    public EditTextWithClearButton(Context context) {
        this(context, null);
    }

    public EditTextWithClearButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent(context, attrs);
    }

    public EditTextWithClearButton(Context context, AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
        initComponent(context, attrs);
    }

    /**
     * 初始化自定义控件
     */
    private final void initComponent(Context context, AttributeSet attrs) {
        TypedArray params = context.obtainStyledAttributes(attrs,
                R.styleable.EditTextWithClearButton);
        try {
            mClearButtonOffset = (int) params.getDimension(
                    R.styleable.EditTextWithClearButton_clearButtonOffset, 0);

            setClearButtonDrawable();

            // 注入 OnTouch 处理， 首先截获处理 OnTouch 事件
            super.setOnTouchListener(this);
        } finally {
            params.recycle();
        }
    }

    /**
     * 获取清空按钮x轴方向绘制偏移
     * 
     * @return
     */
    public int getClearButtonOffset() {
        return mClearButtonOffset;
    }

    /**
     * 设置清空按钮x轴方向绘制偏移
     * 
     * @param clearButtonOffset
     *            清空按钮x轴方向绘制偏移
     */
    public void setClearButtonOffset(int clearButtonOffset) {
        this.mClearButtonOffset = clearButtonOffset;
        setClearButtonBounds();
    }

    /**
     * 显示清空按钮
     */
    public  final void showClearButton() {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], mClearButtonDrawable,
                drawables[3]);
    }

    /**
     * 隐藏清空按钮
     */
    public final void hideClearButton() {
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], null, drawables[3]);
    }

    /**
     * 清空按钮是否显示
     * 
     * @return 清空按钮是否显示
     */
    private final boolean isClearButtonVisiable() {
        return null != getCompoundDrawables()[2];
    }

    /**
     * 当前坐标点是否在清空按钮上（目前只用 x 坐标，忽略 y 坐标）
     * 
     * @param x
     *            判定点的x坐标
     * @return 是否在清空按钮上
     */
    private final boolean isOnClearButton(float x) {
        Rect bounds = mClearButtonDrawable.getBounds();
        return x > getWidth() - getPaddingRight() - bounds.width()
                + bounds.left;
    }

    /**
     * 设置清除按钮的Drawable
     */
    public void setClearButtonDrawable() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.btn_edit_text_del);
        Matrix matrix = new Matrix();
        matrix.postScale(Methods.computePixelsWithDensity(16) / (float) bitmap.getWidth(),
                Methods.computePixelsWithDensity(16) / (float) bitmap.getHeight()); //长和宽放大缩小的比例
        Bitmap scaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);
        mClearButtonDrawable = new BitmapDrawable(getResources(),scaleBitmap);
        setClearButtonBounds();
    }

    /**
     * 计算设置清空按钮的绘制区域
     */
    private void setClearButtonBounds() {
        // 默认的绘制偏移是到上下右侧边缘等距离
        int xOffset = (getHeight() - mClearButtonDrawable.getIntrinsicHeight())
                / 2 + mClearButtonOffset;

        mClearButtonDrawable.setBounds(xOffset, 0,
                mClearButtonDrawable.getIntrinsicWidth() + xOffset,
                mClearButtonDrawable.getIntrinsicHeight());
        setCompoundDrawablePadding(mClearButtonDrawable.getIntrinsicWidth() / 2
                - xOffset);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 按键抬起 && 清空按钮显示 && 点击在清空按钮上 时
        if (MotionEvent.ACTION_UP == event.getAction()
                && isClearButtonVisiable() && isOnClearButton(event.getX())) {

            setText("");
            if (null != mOnClearButtonClickListener) {
                mOnClearButtonClickListener.onClick(this);
            }
        }

        if (null != mOnTouchListener) {
            return mOnTouchListener.onTouch(v, event);
        }

        return false;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        // 注入 OnTouch 处理
        mOnTouchListener = l;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        // 有输入时显示，无输入内容时隐藏
        if (TextUtils.isEmpty(text)) {
            hideClearButton();
        } else {
            showClearButton();
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(focused && !TextUtils.isEmpty(EditTextWithClearButton.this.getText())){
            showClearButton();
        }else{
            hideClearButton();
        }
    }
}
