package com.example.administrator.iwillbe.turnserver;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.administrator.iwillbe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class IssueServerActivity extends Activity {
    ListView issueServerList;
    GridView issueServerGrid;
    View view;
    ImageView returnBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_issue_server);

        issueServerList = (ListView) findViewById(R.id.issue_server_list);
        issueServerGrid = (GridView) findViewById(R.id.issue_server_grid);
        returnBut = (ImageView) findViewById(R.id.return_but);

        returnBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isssueDioglog();
            }
        });

//        ArrayList<HashMap<String, String>> listdata = issListData();
//        String from[] = {"textff"};
//        int to[] = {R.id.issue_server_list_text};
//        SimpleAdapter arrayAdapter = new SimpleAdapter(IssueServerActivity.this, listdata, R.layout.issue_server_list, from, to);
//        issueServerList.setAdapter(arrayAdapter);

        ArrayList<HashMap<String, String>> gridData = issGridData();
        String from1[] = {"hjhjgh"};
        int to1[] = {R.id.issue_server_grid_text};
        SimpleAdapter adapter = new SimpleAdapter(IssueServerActivity.this, gridData, R.layout.item_issur_server_grid, from1, to1);
        issueServerGrid.setAdapter(adapter);

        new Thread(){
            @Override
            public void run() {
                getservicetype();
            }
        }.start();

    }

    HashMap<String, String> map;
    public ArrayList<HashMap<String, String>> issListData() {
        //String[] str = {"手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计"};
        ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < s1.size(); i++) {
            map= new HashMap<String, String>();
            map.put("textff", s1.get(i));
            listdata.add(map);
        }
        return listdata;
    }

    public ArrayList<HashMap<String, String>> issGridData() {
        String[] str = {"手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计"};
        ArrayList<HashMap<String, String>> gridData = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < str.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("hjhjgh", str[i] + i);
            gridData.add(map);
        }
        return gridData;
    }

    LayoutInflater layoutInflater;

    public void isssueDioglog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(IssueServerActivity.this);
        layoutInflater = LayoutInflater.from(IssueServerActivity.this);
        View view = layoutInflater.inflate(R.layout.dioglog_issue_server, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<HashMap<String, String>> listdata = issListData();
            String from[] = {"textff"};
            int to[] = {R.id.issue_server_list_text};
            SimpleAdapter arrayAdapter = new SimpleAdapter(IssueServerActivity.this, listdata, R.layout.issue_server_list, from, to);
            issueServerList.setAdapter(arrayAdapter);
        }
    };


    int status;
    String message;
    JSONObject object;
    List<String> s1 = new ArrayList<String>();
    public void getservicetype(){
        String httpurl = "http://192.168.7.6/index.php/home/index/getservicetype";
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == 200){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("type");
                Log.i("jsonArray","=="+jsonArray);
                for(int i = 0;i<jsonArray.length();i++){
                    object = jsonArray.getJSONObject(i);
                    s1.add(object.getString("type_main_content"));
                    Log.i("jsonobjectArray","=="+s1);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
