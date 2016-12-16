package com.example.administrator.iwillbe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class FindFragment extends Fragment {
    ListView listView;
    List<FindView> list;
    Context context;
    TextView serent;
    TextView need;
    View view_one;
    View view_two;
    int[] image={R.mipmap.discover_img,R.mipmap.discover_img,R.mipmap.discover_img,R.mipmap.discover_img,R.mipmap.discover_img};
    String[] name = {"Panela91","Panela91","Panela91","Panela91","Panela91"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_find,null);
        listView = (ListView) view.findViewById(R.id.listview_find);

        context = getActivity();
        list = getData();
        FindAdapater findAdapater = new FindAdapater(context,list);
        listView.setAdapter(findAdapater);
//        视图转换器
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view1 = layoutInflater.inflate(R.layout.layout_find_top,null);
        listView.addHeaderView(view1);
        serent = (TextView) view1.findViewById(R.id.serent);
        need = (TextView) view1.findViewById(R.id.need);
        view_one = view1.findViewById(R.id.view_one);
        view_two = view1.findViewById(R.id.view_two);

        serent.setOnClickListener(onClickListener);
        need.setOnClickListener(onClickListener);
        return view;
    }
    public List<FindView> getData(){
        list = new ArrayList<FindView>();
        for(int i= 0;i<image.length;i++){
            FindView findView = new FindView();
            findView.setImage1(image[i]);
            findView.setName(name[i]);
            list.add(findView);
        }
        return list;
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //点击服务
                case R.id.serent:
                    serent.setTextColor(getResources().getColor(R.color.black));
                    need.setTextColor(getResources().getColor(R.color.need_grey));
                    view_one.setVisibility(View.VISIBLE);
                    view_two.setVisibility(View.INVISIBLE);
                    break;
                //点击需求
                case R.id.need:
                    serent.setTextColor(getResources().getColor(R.color.need_grey));
                    need.setTextColor(getResources().getColor(R.color.black));
                    view_two.setVisibility(View.VISIBLE);
                    view_one.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
}
