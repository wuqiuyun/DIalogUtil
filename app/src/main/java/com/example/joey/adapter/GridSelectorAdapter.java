package com.example.joey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.joey.R;
import com.example.joey.bean.Fruit;

import java.util.List;

/** 
*  
* @author joey
* created at 2017/8/14 21:20 
*/

public class GridSelectorAdapter extends android.widget.BaseAdapter {
    private Context mContext;
    private List<Fruit> mData;
    private LayoutInflater inflater;

    public GridSelectorAdapter(Context mContext, List<Fruit> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Fruit> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Fruit getItem(int i) {
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
            view = inflater.inflate(R.layout.grid_view_item, null);
            holder.tvTitle = view.findViewById(R.id.tv_string);
            holder.imageView = view.findViewById(R.id.image_view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Fruit fruit = getItem(i);
        holder.tvTitle.setText(fruit.getName().toString());
        holder.imageView.setImageResource(fruit.getPhoto());
        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView tvTitle;
    }
}
