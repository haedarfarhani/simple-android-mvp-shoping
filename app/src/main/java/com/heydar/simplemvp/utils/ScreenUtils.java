package com.heydar.simplemvp.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

public class ScreenUtils {

    private ScreenUtils() {
        // This utility class is not publicly instantiable
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getStatusBarHeight(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsets insets = window.getDecorView().getRootWindowInsets();
            return insets != null ? insets.getInsets(WindowInsets.Type.statusBars()).top : 0;
        } else {
            Context context = window.getContext();
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 25;
        }
    }
}