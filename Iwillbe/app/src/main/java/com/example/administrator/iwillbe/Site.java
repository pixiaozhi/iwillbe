package com.example.administrator.iwillbe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Site extends Activity {
    GridView gridview_edit_visit;
    GridView gridview_edit_hot;
    List<SiteView> list;
    List<SiteView> listone;
    String[] visit = {"北京","广州","重庆"};
    String[] hot = {"上海","北京","广州","深圳","武汉","天津","成都","重庆"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_site);

        gridview_edit_visit = (GridView) findViewById(R.id.gridview_edit_visit);
        gridview_edit_hot = (GridView) findViewById(R.id.gridview_edit_hot);

        list = getData();
        SiteAdapter siteAdapter = new SiteAdapter(Site.this,list);
        gridview_edit_hot.setAdapter(siteAdapter);
        listone = getDate();
        SiteAdapter siteAdapterone = new SiteAdapter(Site.this,listone);
        gridview_edit_visit.setAdapter(siteAdapterone);
    }
    public List<SiteView> getData(){
        list = new ArrayList<SiteView>();
        for(int i= 0;i<hot.length;i++){
            SiteView siteView = new SiteView();
            siteView.setText(hot[i]);
            list.add(siteView);
        }
        return list;
    }
    public List<SiteView> getDate(){
        listone = new ArrayList<SiteView>();
        for(int i= 0;i<visit.length;i++){
            SiteView siteView = new SiteView();
            siteView.setText(visit[i]);
            listone.add(siteView);
        }
        return listone;
    }
}
