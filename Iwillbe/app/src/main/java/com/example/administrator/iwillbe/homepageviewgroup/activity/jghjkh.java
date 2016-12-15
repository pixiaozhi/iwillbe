package com.example.administrator.iwillbe.homepageviewgroup.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.iwillbe.R;
import com.example.administrator.iwillbe.homepageviewgroup.LGNineGrideView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class jghjkh extends Activity {
    private com.example.administrator.iwillbe.homepageviewgroup.LGNineGrideView lgNineGrideView;
    private List<String> urls = new ArrayList<>();
    private int countIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_haha);
        for (int i = 0; i < 9; ++i){
            urls.add(TestData.urlsArray[i]);
        }
        lgNineGrideView = (LGNineGrideView)findViewById(R.id.lalal);
        assert lgNineGrideView != null;
        lgNineGrideView.setUrls(urls);
    }
}
