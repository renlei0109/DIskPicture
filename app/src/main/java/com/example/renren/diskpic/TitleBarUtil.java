package com.example.renren.diskpic;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by renlei
 * DATE: 16-1-6
 * Time: 下午3:51
 * Email: lei.ren@renren-inc.com
 */
public class TitleBarUtil {
    public static ImageView getLeftBackBtn(Context context){
        ImageView imageView = getImageBtn(context);
//        imageView.setImageResource(R.drawable.common_btn_back);
        imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.common_btn_back));
        return imageView;
    }
    public static ImageView getImageBtn(Context context){
        ImageView imageView =new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.width = (int)context.getResources().getDimension(R.dimen.titlebar_image_btn_width);
        layoutParams.gravity = Gravity.CENTER;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    public static TextView getTitleView(Context context){
        TextView textView =new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(16);
        textView.setTextColor(context.getResources().getColor(R.color.titlebar_text_color));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
