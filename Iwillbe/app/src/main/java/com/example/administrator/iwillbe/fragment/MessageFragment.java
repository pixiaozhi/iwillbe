package com.example.administrator.iwillbe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.iwillbe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class MessageFragment extends Fragment {
    ListView listView;
    List<MessageView> list;
    Context context;
    int[] image={R.mipmap.message_icon_system,R.mipmap.message_icon_inform,R.mipmap.message_icon_head,R.mipmap.homepage_head_portrait};
    String[] name={"通知消息","系统消息","穿靴子的猫","多啦没有梦"};
    String[] contest={"通知消息","系统消息","你还没走？？？","快来玩啊！！！"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_message,null);
        listView = (ListView) view.findViewById(R.id.listview_message);
        context =getActivity();
        list = getData();
        MessageAdapater messageAdapater = new MessageAdapater(context,list);
        listView.setAdapter(messageAdapater);
        return view;
    }
    public List<MessageView> getData(){
        list = new ArrayList<MessageView>();
        for(int i= 0;i<image.length;i++){
            MessageView messageView= new MessageView();
            messageView.setImage(image[i]);
            messageView.setName(name[i]);
            messageView.setContext(contest[i]);
            list.add(messageView);
        }
        return list;
    }
}
