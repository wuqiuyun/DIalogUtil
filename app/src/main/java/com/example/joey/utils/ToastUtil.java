package com.example.joey.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具類
 * Created by ASUS on 2017/8/14.
 */

public class ToastUtil {
    /** 
    *  土司提示 
    */
    public static void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
