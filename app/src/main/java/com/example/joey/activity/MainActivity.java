package com.example.joey.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joey.bean.Fruit;
import com.example.joey.callback.OnListViewDialogListener;
import com.example.joey.utils.DialogUtil;
import com.example.joey.callback.OnDoubleDialogListener;
import com.example.joey.R;
import com.example.joey.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/** 
* MainActivity 类 
* @author joey
* created at 2017/8/13 22:19 
*/
public class MainActivity extends Activity implements View.OnClickListener {
    private MainActivity mActivity;

    private Button mBtnDoubleTextDialog;
    private Button mBtnSingleTextDialog;
    private Button mBtnListSelectorDialog;
    private Button mBtnBottomListViewDialog;
    private Button mBtnBottomGridViewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        initView();
        setListener();
    }

    private void initView() {
        mBtnDoubleTextDialog = (Button) findViewById(R.id.btn_double_text_dialog);
        mBtnSingleTextDialog = (Button) findViewById(R.id.btn_single_text_dialog);
        mBtnListSelectorDialog = (Button) findViewById(R.id.btn_list_selector_dialog);
        mBtnBottomGridViewDialog = (Button) findViewById(R.id.btn_bottom_gridview_dialog);
        mBtnBottomListViewDialog = (Button) findViewById(R.id.btn_bottom_listview_dialog
        );
    }

    private void setListener() {
        mBtnDoubleTextDialog.setOnClickListener(mActivity);
        mBtnSingleTextDialog.setOnClickListener(mActivity);
        mBtnListSelectorDialog.setOnClickListener(mActivity);
        mBtnBottomListViewDialog.setOnClickListener(mActivity);
        mBtnBottomGridViewDialog.setOnClickListener(mActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_double_text_dialog:

                DialogUtil.showDoubleTextSelection(mActivity, "警告兔子", "你最近两天浪费很多米饭", "知错了", "死不悔改", new OnDoubleDialogListener() {
                    @Override
                    public void onClick(View view, boolean isCommit) {
                        if (isCommit) {
                            Toast.makeText(mActivity,"打屁屁",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                break;

            case R.id.btn_single_text_dialog:
                DialogUtil.showSingleTextSelection(mActivity,"亲爱的兔子","明天见咯！爱你！","么么哒");
                break;

            case R.id.btn_list_selector_dialog:
                final List<String> listDialogData = new ArrayList<>();
                listDialogData.add("冰箱");
                listDialogData.add("洗衣机");
                listDialogData.add("空調");
                listDialogData.add("電腦");
                listDialogData.add("电饭锅");
                listDialogData.add("電磁爐");
                DialogUtil.showListSelectorSelection(mActivity, "兔子家具城", listDialogData, new OnListViewDialogListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(mActivity,listDialogData.get(position));
                    }
                });
                break;

            case R.id.btn_bottom_listview_dialog:
                final List<String> bottomListDialogData=new ArrayList<>();
                bottomListDialogData.add("拍照");
                bottomListDialogData.add("从图库中选择");
                bottomListDialogData.add("取消");
                DialogUtil.showBottomListViewDialog(mActivity, bottomListDialogData, new OnListViewDialogListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(mActivity,bottomListDialogData.get(position));
                    }
                });
                break;

            case R.id.btn_bottom_gridview_dialog:

                final List<Fruit> bottomGridDialogData = new ArrayList<>();
                bottomGridDialogData.add(new Fruit(R.mipmap.ic_launcher,"小蘋果"));
                bottomGridDialogData.add(new Fruit(R.mipmap.ic_launcher,"大鴨梨"));
                bottomGridDialogData.add(new Fruit(R.mipmap.ic_launcher,"黃香蕉"));
                bottomGridDialogData.add(new Fruit(R.mipmap.ic_launcher,"哈密瓜"));
                bottomGridDialogData.add(new Fruit(R.mipmap.ic_launcher,"大笨瓜"));
                DialogUtil.showBottomGridViewDialog(mActivity,bottomGridDialogData, new OnListViewDialogListener() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(mActivity,bottomGridDialogData.get(position).getName().toString());
                    }
                });
                break;
        }
    }
}
