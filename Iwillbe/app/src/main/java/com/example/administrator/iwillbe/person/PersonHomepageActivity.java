package com.example.administrator.iwillbe.person;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.iwillbe.R;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonHomepageActivity extends Activity{
    ListView personPublishList;
    LayoutInflater layoutInflater;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_person_homepage);

        personPublishList = (ListView) findViewById(R.id.person_publish_list);

        layoutInflater = LayoutInflater.from(PersonHomepageActivity.this);
        view = layoutInflater.inflate(R.layout.person_homepage_head, null);
        personPublishList.addHeaderView(view);
    }
}
