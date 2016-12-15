package com.example.administrator.iwillbe.homepageviewgroup.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;
import com.example.administrator.iwillbe.homepageviewgroup.LGNineGrideView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/13.
 */
public class TextActivity extends Activity {
    private static String TAG = "ListViewActivity";
    ListView text_listview;
    private List<DataModel> models = new ArrayList();
    private MyListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_listview);
        text_listview = (ListView) findViewById(R.id.text_listview);
        adapter = new MyListAdapter(this,models);
        text_listview.setAdapter(adapter);
        loadMore();
    }
    private void loadMore(){
        Log.d(TAG,"loadMore...");
        Random random = new Random();
        int modeSize = models.size();
        int count = 100;
        for (int i = 0; i < count; ++i){
            DataModel model = new DataModel();
            ArrayList<String> lists = new ArrayList();
            int picNum = random.nextInt(9) + 1;
            for (int j = 0; j < picNum; ++j){
                int index = random.nextInt(picNum);
                lists.add(TestData.urlsArray[index]);
            }
            model.setUrls(lists);
            model.setText("num:" + (modeSize + i) + " pic size:" + lists.size());
            models.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    private class DataModel{
        private List<String> urls;
        private String text;

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    private static class MyListAdapter extends BaseAdapter {
        private Context context;
        private List<DataModel> datas;

        public MyListAdapter(Context context,List<DataModel> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public DataModel getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        static class ViewHolder{
            TextView tv;
            LGNineGrideView grideView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final DataModel model = getItem(position);
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = ((LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.text_list_item,parent,false);
                TextView title = (TextView)convertView.findViewById(R.id.textview);
                LGNineGrideView LGNineGrideView = (LGNineGrideView)convertView.findViewById(R.id.text_giroupview1);
                holder.tv = title;
                holder.grideView = LGNineGrideView;
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tv.setText(model.getText());
            holder.grideView.setUrls(model.getUrls());
            holder.grideView.setOnItemClickListener(new LGNineGrideView.OnItemClickListener() {
                @Override
                public void onClickItem(int position, View view) {
//                    Intent intent = new Intent(context, ViewPagerActivity.class);
//                    intent.putExtra("position",position);
//                    intent.putStringArrayListExtra(ViewPagerActivity.KEY_IMAGE_URLS,(ArrayList<String>) model.getUrls());
//                    context.startActivity(intent);
                }
            });
            return convertView;
        }
    }
    
}
