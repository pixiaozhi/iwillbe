package com.example.administrator.iwillbe.turnserver;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.HashMap;

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

        ArrayList<HashMap<String, String>> listdata = issListData();
        String from[] = {"textff"};
        int to[] = {R.id.issue_server_list_text};
        SimpleAdapter arrayAdapter = new SimpleAdapter(IssueServerActivity.this, listdata, R.layout.issue_server_list, from, to);
        issueServerList.setAdapter(arrayAdapter);

        ArrayList<HashMap<String, String>> gridData = issGridData();
        String from1[] = {"hjhjgh"};
        int to1[] = {R.id.issue_server_grid_text};
        SimpleAdapter adapter = new SimpleAdapter(IssueServerActivity.this, gridData, R.layout.item_issur_server_grid, from1, to1);
        issueServerGrid.setAdapter(adapter);
    }

    public ArrayList<HashMap<String, String>> issListData() {
        String[] str = {"手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计", "手绘设计"};
        ArrayList<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < str.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("textff", str[i] + i);
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
}
