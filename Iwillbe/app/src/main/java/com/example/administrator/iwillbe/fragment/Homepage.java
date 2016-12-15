package com.example.administrator.iwillbe.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.iwillbe.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Homepage extends Fragment {
    TextView popuper_fuwu;
    TextView popuper_xuqiu;
    ListView listView;
    List<FindView> list_button;
    Context context;
    LinearLayout daohang_top;
    ImageView image_daohang_top;
    GridView gridview_homepage;
    List<Homepageview> list;
    int[] image = {R.mipmap.home_icon_one,R.mipmap.home_icon_twe,R.mipmap.home_icon_three,R.mipmap.home_icon_four,
            R.mipmap.home_icon_five,R.mipmap.home_icon_six,R.mipmap.home_icon_seven,R.mipmap.home_icon_eight,R.mipmap.home_icon_nine,
            R.mipmap.home_icon_ten};
    String[] text = {"创意手工","手绘设计","摄影摄像","心理咨询","兴趣培训","游戏娱乐","美容时尚","策划营销",
            "居家装修","更多功能"};
    int[] image_head_portrait={R.mipmap.discover_img,R.mipmap.discover_img,R.mipmap.discover_img};
    String[] name = {"Panela91","Panela91","Panela91"};
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.layout_listview,null);

        context = getActivity();
        listView = (ListView) view.findViewById(R.id.listview_homepage);

        list_button = getDate();
        FindAdapater findAdapater = new FindAdapater(context,list_button);
        listView.setAdapter(findAdapater);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view1 = layoutInflater.inflate(R.layout.layout_homepage,null);
        listView.addHeaderView(view1);

        gridview_homepage = (GridView) view1.findViewById(R.id.gridview_homepage);
        gridview_homepage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,250));

        image_daohang_top= (ImageView) view1.findViewById(R.id.image_daohang_top);

        image_daohang_top.setOnClickListener(onClickListener);

        list = getData();
        HomepageAdapter homepageAdapter = new HomepageAdapter(context,list);
        gridview_homepage.setAdapter(homepageAdapter);

        return view;
    }
    public List<FindView> getDate(){
        list_button = new ArrayList<FindView>();
        for(int i= 0;i<image_head_portrait.length;i++){
            FindView findView = new FindView();
            findView.setImage1(image_head_portrait[i]);
            findView.setName(name[i]);
            list_button.add(findView);
        }
        return list_button;
    }
    public List<Homepageview> getData(){
        list = new ArrayList<Homepageview>();
        for(int i= 0;i<image.length;i++){
            Homepageview homepageview = new Homepageview();
            homepageview.setImage(image[i]);
            homepageview.setText(text[i]);
            list.add(homepageview);
        }
        return list;
    }

    /*
    * 创建PopupWindow
    */
    PopupWindow mypopupWindow;
    public void showpopupWindow(){
        LayoutInflater layoutInflater = LayoutInflater.from(this.getActivity());
        View popupWindow = layoutInflater.inflate(R.layout.layout_popuerwindow,null);
        daohang_top = (LinearLayout) popupWindow.findViewById(R.id.daohang_top);
        popuper_fuwu = (TextView) popupWindow.findViewById(R.id.popuper_fuwu);
        popuper_xuqiu = (TextView) popupWindow.findViewById(R.id.popuper_xuqiu);
        mypopupWindow = new PopupWindow(popupWindow);
        //设置宽高
        mypopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mypopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mypopupWindow.showAsDropDown(image_daohang_top);
        mypopupWindow.setTouchable(true);
        mypopupWindow.setFocusable(false);
        mypopupWindow.setOutsideTouchable(true);
        popuper_xuqiu.setOnClickListener(onClickListener);
        popuper_fuwu.setOnClickListener(onClickListener);
        // 注册监听事件，点击别的地方消失
        mypopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getPopupwindow();
                return false;
            }
        });
    }

    /*
     * 获取PopupWindow实例
     */
    public void getPopupwindow(){
        if(mypopupWindow != null && mypopupWindow.isShowing()){
            mypopupWindow.dismiss();
            Log.i("getPopupwindow","==========dismiss");
            mypopupWindow = null;
        }else {
            showpopupWindow();
            Log.i("getPopupwindow","==========create");
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.image_daohang_top:
                    getPopupwindow();
                    break;
                case R.id.popuper_fuwu:
                    Toast.makeText(context,"服务被点击了",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.popuper_xuqiu:
                    Toast.makeText(context,"需求被点击了",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
