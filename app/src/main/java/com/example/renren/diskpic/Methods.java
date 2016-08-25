package com.example.renren.diskpic;

import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by  renlei
 * DATE: 16/8/24
 * Time: 19:00
 */
public class Methods {
    private static  float density ;

    public static int computePixelsWithDensity(int dp) {
        Log.d("Methods","density"+density);
        float scale;
        scale = density;
        return (int) (dp * scale + 0.5);
    }
    public static void setDensity(BaseActivity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;

    }
}
