package com.example.administrator.iwillbe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class Site extends Activity {
    TextView site_china;
    TextView site_haiwai;
    ImageView exit_site;
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
        exit_site = (ImageView) findViewById(R.id.exit_site);
        site_china = (TextView) findViewById(R.id.site_china);
        site_haiwai = (TextView) findViewById(R.id.site_haiwai);
        list = getData();
        SiteAdapter siteAdapter = new SiteAdapter(Site.this,list);
        gridview_edit_hot.setAdapter(siteAdapter);
        listone = getDate();
        SiteAdapter siteAdapterone = new SiteAdapter(Site.this,listone);
        gridview_edit_visit.setAdapter(siteAdapterone);
        exit_site.setOnClickListener(onClickListener);
        site_china.setOnClickListener(onClickListener);
        site_haiwai.setOnClickListener(onClickListener);
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
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.exit_site:
                    finish();
                    break;
                case R.id.site_haiwai:
                    site_china.setBackgroundDrawable(getResources().getDrawable(R.drawable.site_red_change_btn));
                    site_haiwai.setBackgroundDrawable(getResources().getDrawable(R.drawable.site_white_change_btn));
                    site_haiwai.setTextColor(getResources().getColor(R.color.white));
                    site_china.setTextColor(getResources().getColor(R.color.red));
                    break;
                case R.id.site_china:
                    site_china.setBackgroundDrawable(getResources().getDrawable(R.drawable.site_red_btn));
                    site_haiwai.setBackgroundDrawable(getResources().getDrawable(R.drawable.site_white_btn));
                    site_china.setTextColor(getResources().getColor(R.color.white));
                    site_haiwai.setTextColor(getResources().getColor(R.color.red));
                    break;
            }
        }
    };
}
