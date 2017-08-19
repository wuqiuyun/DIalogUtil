package com.example.joey.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joey.R;
import com.example.joey.adapter.GridSelectorAdapter;
import com.example.joey.adapter.ListSelectorAdapter;
import com.example.joey.bean.Fruit;
import com.example.joey.callback.OnDoubleDialogListener;
import com.example.joey.callback.OnListViewDialogListener;

import java.util.List;

/**
 * @Description dialog工具类
 * @Author WuQiuyun
 * @Date 2017/8/13
 */
public class DialogUtil {

    /**
     * @param context   上下文
     * @param title     标题文字
     * @param prompt    提示内容
     * @param cancel    取消提示文字
     * @param determine 确定提示文字
     * @Description 双选择提示框
     */
    public static void showDoubleTextSelection(final Context context, String title, String prompt, String cancel, String determine, final OnDoubleDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_dialog);
        builder.setCancelable(false);

        View view = LayoutInflater.from(context).inflate(R.layout.double_text_dialog_layout, null);
        Button mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        Button mBtnCommit = (Button) view.findViewById(R.id.btn_commit);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mTvPrompt = (TextView) view.findViewById(R.id.tv_prompt);

        final AlertDialog dialog = builder.create();

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        } else {
            mTvTitle.setText(context.getResources().getString(R.string.warm_prompt));
        }

        mTvPrompt.setText(prompt);

        if (!TextUtils.isEmpty(cancel)) {
            mBtnCancel.setText(cancel);
        } else {
            mBtnCancel.setText(context.getResources().getString(R.string.cancel));
        }

        if (!TextUtils.isEmpty(determine)) {
            mBtnCommit.setText(determine);
        } else {
            mBtnCommit.setText(context.getResources().getString(R.string.commit));
        }

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onClick(v, false);
                }

            }
        });

        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onClick(v, true);
                }

            }
        });

        dialog.show();
        dialog.setContentView(view);

    }

    /**
     * @param context   上下文
     * @param title     标题文字
     * @param prompt    提示内容
     * @param determine 确定提示文字
     * @Description 单选择提示框
     */
    public static void showSingleTextSelection(Context context, String title, String prompt, String determine) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_dialog);
        builder.setCancelable(false);

        View view = LayoutInflater.from(context).inflate(R.layout.single_text_dialog_layout, null);
        Button mBtnCommit = (Button) view.findViewById(R.id.btn_commit);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mTvPrompt = (TextView) view.findViewById(R.id.tv_prompt);

        final AlertDialog dialog = builder.create();

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        } else {
            mTvTitle.setText(context.getResources().getString(R.string.warm_prompt));
        }

        mTvPrompt.setText(prompt);

        if (!TextUtils.isEmpty(determine)) {
            mBtnCommit.setText(determine);
        } else {
            mBtnCommit.setText(context.getResources().getString(R.string.commit));
        }

        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
        dialog.setContentView(view);
    }

    /**
     * List View 选择dialog
     *
     * @param context 上下文
     * @param title   标题
     * @param list    数据集合
     */
    public static void showListSelectorSelection(final Context context, String title, List<String> list, final OnListViewDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_list_view_dialog);
        builder.setCancelable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.list_selector_dialog_layout, null);

        final ListSelectorAdapter adapter = new ListSelectorAdapter(context, list);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        ListView mLvData = (ListView) view.findViewById(R.id.lv_item);

        final AlertDialog dialog = builder.create();

        mTvTitle.setText(title);

        if (list != null) {
            mLvData.setAdapter(adapter);
            adapter.setData(list);
        }

        dialog.show();
        dialog.setContentView(view);

        mLvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onItemClick(i);
                }
            }
        });
    }

    /**
     * 底部list view    对话框
     *
     * @param context 上下文
     * @param list    数据集合
     */
    public static void showBottomListViewDialog(final Context context, List<String> list, final OnListViewDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_list_view_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_list_view_dialog_layout, null);

        ListSelectorAdapter adapter = new ListSelectorAdapter(context, list);
        ListView mLvData = (ListView) view.findViewById(R.id.lv_data);

        final AlertDialog dialog = builder.create();

        if (list != null) {
            mLvData.setAdapter(adapter);
            adapter.setData(list);
        }

        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.setContentView(view);

        mLvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onItemClick(i);
                }
            }
        });
    }

    /**
     * 底部GridView 对话框
     *
     * @param context 上下文
     * @param list    数据集合
     */
    public static void showBottomGridViewDialog(final Context context, List<Fruit> list, final OnListViewDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.theme_list_view_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_grid_view_dialog_layout, null);

        GridSelectorAdapter adapter = new GridSelectorAdapter(context, list);
        GridView mGvData = (GridView) view.findViewById(R.id.gv_data);

        final AlertDialog dialog = builder.create();

        if (list != null) {
            mGvData.setAdapter(adapter);
            adapter.setData(list);
        }
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.setContentView(view);

        mGvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onItemClick(i);
                }
            }
        });
    }
}
