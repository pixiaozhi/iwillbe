package com.example.administrator.iwillbe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class SiteAdapter extends BaseAdapter {
    Context context;
    List<SiteView> list;
    public SiteAdapter(Context context,List<SiteView> list){
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
            convertView = layoutInflater.inflate(R.layout.layout_edit_kuang,null);
            viewHolder.textview = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.textview.setText(list.get(position).getText());
        return convertView;
    }

    public class ViewHolder{
        TextView textview;
    }
}
