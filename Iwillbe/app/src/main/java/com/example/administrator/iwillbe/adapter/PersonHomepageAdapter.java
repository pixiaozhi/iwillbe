package com.example.administrator.iwillbe.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;
import com.example.administrator.iwillbe.mvc.PersonHomepage;
import com.example.administrator.iwillbe.person.PersonHomepageActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uitl.Setdata;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonHomepageAdapter extends BaseAdapter {
    Context context;
    ArrayList<PersonHomepage> list;
    LayoutInflater layoutInflater;
    String nameList;
    String toNameList;
    String contentList;
    int position = -1;
    private int mTouchItemPosition = -1;
    //private final HashMap<Integer, String> mData;
    private OnChangedTextListener onChangedTextListener;
    //
    public PersonHomepageAdapter(Context context, ArrayList<PersonHomepage> list,String nameList,String toNameList,String contentList) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.nameList = nameList;
        this.toNameList = toNameList;
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    ViewHolder viewHolder = null;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        position = i;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item_person_homepage, null);
            viewHolder.headImag = (ImageView) view.findViewById(R.id.head_imag);
            viewHolder.personName = (TextView) view.findViewById(R.id.person_name);
            viewHolder.personTime = (TextView) view.findViewById(R.id.person_time);
            viewHolder.detaiclarsText = (TextView) view.findViewById(R.id.detaiclars_text);
//            viewHolder.gridImage = (GridView) view.findViewById(R.id.grid_imag);
            viewHolder.commentLinear = (LinearLayout) view.findViewById(R.id.comment_linearLayout);
            viewHolder.commentEdit = (EditText) view.findViewById(R.id.comment_edit);
            viewHolder.sendBtn = (TextView) view.findViewById(R.id.send_btn);
            view.setTag(viewHolder);

        }
        PersonHomepage personHomepage = list.get(i);
        //头像
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.commentEdit.setTag(position);
        viewHolder.sendBtn.setTag(position);
        viewHolder.commentLinear.setTag(position);
        Headhandler headhandler = new Headhandler();
        headhandler.setViewHolder(viewHolder);
        HeadThred headThred = new HeadThred();
        headThred.setMyHandler(headhandler);
        headThred.setIamgeURL(personHomepage.getImagUrl());
        headThred.start();

//        viewHoder.personName.setText(personHomepage.getUserName());
//        viewHoder.personName.setText(personHomepage.getTime());
//        viewHoder.personName.setText(personHomepage.getIntroText());
        /**
         * 解决焦点问题
         * 当editView获取焦点时,listview会重新绘制,致使editView的焦点光标失去
         */
        if (mTouchItemPosition == i) {
            viewHolder.commentEdit.requestFocus();
            viewHolder.commentEdit.setSelection(viewHolder.commentEdit.getText().length());
        } else {
            viewHolder.commentEdit.clearFocus();
        }

        viewHolder.commentEdit.addTextChangedListener(textWatcher);
        viewHolder.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addcomment();
//                viewHolder.commentEdit.setText("");
//                notifyDataSetChanged();
            }
        });



        //发表集合
        String introImag[] = personHomepage.getIntroimag();
        String intro[] = null;
        for(int k = 0; k < introImag.length; k++){
            if (introImag.length > 9){

            }
        }
        IntroIamgHandler introIamgHandler = new IntroIamgHandler();
        introIamgHandler.setViewHolder(viewHolder);
        IntroIamgThread introIamgThread = new IntroIamgThread();
        introIamgThread.setIntroIamgHandler(introIamgHandler);
        introIamgThread.setIntroIamgURL(introImag);
        introIamgThread.start();

        return view;
    }

    class ViewHolder{
        TextView sendBtn;
        ImageView headImag;
        TextView personName;
        TextView personTime;
        TextView detaiclarsText;
        GridView gridImage;
        LinearLayout commentLinear;
        EditText commentEdit;
        MyTextChangeWatch mTextWatcher;
        //记录position,防止数据复用时,错乱
        public void upDataPosition(int position) {
            mTextWatcher.upDataPosition(position);
        }
    }

    //个人空间头像加载图片
    class HeadThred extends Thread {
        String headImag;
        Headhandler headHandler;

        public void setMyHandler(Headhandler headHandler) {
            this.headHandler = headHandler;
        }

        public void setIamgeURL(String headImag) {
            this.headImag = headImag;
        }

        @Override
        public void run() {
            try {
                URL headurl = new URL(headImag);
                Bitmap headBitmap = BitmapFactory.decodeStream(headurl.openStream());
                if (headBitmap != null) {
                    headHandler.setBitmap(headBitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addcomment(){
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Setdata setdata = new Setdata(context,textView,nameList,toNameList,contentList);
        setdata.mSetdata();
        if(viewHolder.commentLinear.getTag()==viewHolder.sendBtn.getTag()){
            viewHolder.commentLinear.addView(textView);
        }
        notifyDataSetChanged();
    }

    class Headhandler extends Handler {
        ViewHolder viewHolder;
        Bitmap bitmap;
        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public void setViewHolder(ViewHolder viewHoder) {
            this.viewHolder = viewHoder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (bitmap != null) {
                viewHolder.headImag.setImageBitmap(bitmap);
            }
        }
    }

    //    发表图片加载
    class IntroIamgThread extends Thread {
        String introIamgURL[];
        IntroIamgHandler introIamgHandler;
        public void setIntroIamgHandler(IntroIamgHandler introIamgHandler){
            this.introIamgHandler = introIamgHandler;
        }
        public void setIntroIamgURL(String introIamgURL[]){
            this.introIamgURL = introIamgURL;
        }
        @Override
        public void run() {
            String iamgURL = null;
            for (int i = 0; i < introIamgURL.length; i++){
                iamgURL = introIamgURL[i];
            }
            try {
                URL introIamgURL = new URL(iamgURL);
                Bitmap introBitmap = BitmapFactory.decodeStream(introIamgURL.openStream());
                if (introBitmap != null){
                    introIamgHandler.setIntroIamgBitmap(introBitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class IntroIamgHandler extends Handler{
        ViewHolder viewHolder;
        Bitmap introIamgBitmap;
        public void setIntroIamgBitmap(Bitmap introIamgBitmap){
            this.introIamgBitmap = introIamgBitmap;
        }
        public void setViewHolder(ViewHolder viewHoder){
            this.viewHolder = viewHoder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (introIamgBitmap != null){
                //viewHoder.gridImage.

            }
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            contentList=editable.toString();
            Log.i("===",""+contentList);
        }
    };

    class MyTextChangeWatch implements TextWatcher {
        private int position;

        public void upDataPosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //mData.put(position, s.toString());
            contentList=s.toString();
            if(onChangedTextListener !=null){
                //使用回调将数据传递出去,进行数据检测
                onChangedTextListener.onChangedTextListener(position, s.toString());
            }
        }
    }
    public void setOnChangedTextListener(OnChangedTextListener onChangedTextListener) {
        this.onChangedTextListener = onChangedTextListener;
    }
    interface OnChangedTextListener {
        void onChangedTextListener(int position, String str);
    }

}
