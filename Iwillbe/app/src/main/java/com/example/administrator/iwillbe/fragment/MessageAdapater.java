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
 * Created by Administrator on 2016/12/13.
 */
public class MessageAdapater extends BaseAdapter{
    Context context;
    List<MessageView> list;
    public MessageAdapater(Context context,List<MessageView> list){
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
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.layout_message_view,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_message);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name_message);
            viewHolder.context = (TextView) convertView.findViewById(R.id.context_message);
            viewHolder.view_message = convertView.findViewById(R.id.view_message);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.imageView.setImageResource(list.get(position).getImage());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.context.setText(list.get(position).getContext());
        if(position<1||position>1){
            viewHolder.view_message.setVisibility(View.GONE);
        }else {
            viewHolder.view_message.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView context;
        View view_message;
    }
}
