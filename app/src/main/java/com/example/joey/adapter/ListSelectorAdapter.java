package com.example.joey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joey.R;

import java.util.List;

/**
 * Created by ASUS on 2017/8/13.
 */

public class ListSelectorAdapter extends android.widget.BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater inflater;

    public ListSelectorAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<String> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_item, null);
            holder.tvTitle = view.findViewById(R.id.tv_string);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String itemText = getItem(i);
        holder.tvTitle.setText(itemText);
        return view;
    }

    private class ViewHolder {
        private TextView tvTitle;
    }
}
