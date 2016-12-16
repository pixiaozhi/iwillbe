package com.example.administrator.iwillbe.person;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;
import com.example.administrator.iwillbe.adapter.MyListAdapter;
import com.example.administrator.iwillbe.adapter.PersonHomepageAdapter;
import com.example.administrator.iwillbe.mvc.PersonHomepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uitl.Setdata;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonHomepageActivity extends Activity{
    ListView personPublishList;
    LayoutInflater layoutInflater;
    View view;
    HashMap<Integer, String> data;
    ArrayList<PersonHomepage> list = new ArrayList<PersonHomepage>();
    String nameList = "我就是奇迹";
    String toNameList = " ";
    String contentList = "说的有点道理";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_person_homepage);

        personPublishList = (ListView) findViewById(R.id.person_publish_list);

        layoutInflater = LayoutInflater.from(PersonHomepageActivity.this);
        view = layoutInflater.inflate(R.layout.person_homepage_head, null);
        personPublishList.addHeaderView(view);

        //
        getdata();
        PersonHomepageAdapter personHomepageAdapter = new PersonHomepageAdapter(this,list,nameList,toNameList,contentList);
        personPublishList.setAdapter(personHomepageAdapter);

        MyListAdapter myListAdapter = new MyListAdapter(this,data);

//        View view = personPublishList.getChildAtPosition(0);
//        TextView textView = (TextView) view.findViewById(R.id.send_btn);
//        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.comment_linearLayout);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView textView = new TextView(PersonHomepageActivity.this);
//                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                Setdata setdata = new Setdata(PersonHomepageActivity.this,textView,nameList,toNameList,contentList);
//                setdata.mSetdata();
//                linearLayout.addView(textView);
//                //personPublishList.notifyDataSetChanged();
//            }
//        });
    }

    public void getdata(){
        String[] strings = {"个人资料"};
        for(int i = 0 ;i<strings.length;i++){
            PersonHomepage personHomepage = new PersonHomepage();
            personHomepage.setUserName(""+strings[i]);
            list.add(personHomepage);
        }
    }
}
