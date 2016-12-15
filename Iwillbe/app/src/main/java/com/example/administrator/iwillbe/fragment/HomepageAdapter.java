package com.example.administrator.iwillbe.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class HomepageAdapter extends BaseAdapter {
    Context context;
    List<Homepageview> list;
    public HomepageAdapter(Context context,List<Homepageview> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.layout_homepage_view,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.textview = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.imageView.setImageResource(list.get(position).getImage());
        viewHolder.textview.setText(list.get(position).getText());
        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textview;
    }
}
