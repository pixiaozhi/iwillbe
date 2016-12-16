package com.example.administrator.iwillbe.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonHomepageAdapter extends BaseAdapter {
    Context context;
    ArrayList<PersonHomepage> list;
    LayoutInflater layoutInflater;

    public PersonHomepageAdapter(Context context, ArrayList<PersonHomepage> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if (view == null) {
            viewHoder = new ViewHoder();
            view = layoutInflater.inflate(R.layout.item_person_homepage, null);
            viewHoder.headImag = (ImageView) view.findViewById(R.id.head_imag);
            viewHoder.personName = (TextView) view.findViewById(R.id.person_name);
            viewHoder.personTime = (TextView) view.findViewById(R.id.person_time);
            viewHoder.detaiclarsText = (TextView) view.findViewById(R.id.detaiclars_text);
            viewHoder.gridImage = (GridView) view.findViewById(R.id.grid_imag);
            viewHoder.commentLinear = (LinearLayout) view.findViewById(R.id.comment_linear);
            view.setTag(viewHoder);
        }
        PersonHomepage personHomepage = list.get(i);
        //头像
        view.setTag(viewHoder);
        Headhandler headhandler = new Headhandler();
        headhandler.setViewHoder(viewHoder);
        HeadThred headThred = new HeadThred();
        headThred.setMyHandler(headhandler);
        headThred.setIamgeURL(personHomepage.getImagUrl());
        headThred.start();

        viewHoder.personName.setText(personHomepage.getUserName());
        viewHoder.personName.setText(personHomepage.getTime());
        viewHoder.personName.setText(personHomepage.getIntroText());
        //发表集合
        String introImag[] = personHomepage.getIntroimag();
        String intro[] = null;
        for(int k = 0; k < introImag.length; k++){
            if (introImag.length > 9){


            }
        }
        IntroIamgHandler introIamgHandler = new IntroIamgHandler();
        introIamgHandler.setViewHoder(viewHoder);
        IntroIamgThread introIamgThread = new IntroIamgThread();
        introIamgThread.setIntroIamgHandler(introIamgHandler);
        introIamgThread.setIntroIamgURL(introImag);
        introIamgThread.start();

        String comment[] = personHomepage.getComment();
        for (int j = 0; j < comment.length; j++){
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setText(comment[i]);
            viewHoder.commentLinear.addView(textView, layoutParams);
        }
        return view;
    }

    class ViewHoder {
        ImageView headImag;
        TextView personName;
        TextView personTime;
        TextView detaiclarsText;
        GridView gridImage;
        LinearLayout commentLinear;

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
    class Headhandler extends Handler {
        ViewHoder viewHoder;
        Bitmap bitmap;
        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public void setViewHoder(ViewHoder viewHoder) {
            this.viewHoder = viewHoder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (bitmap != null) {
                viewHoder.headImag.setImageBitmap(bitmap);
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
        ViewHoder viewHoder;
        Bitmap introIamgBitmap;
        public void setIntroIamgBitmap(Bitmap introIamgBitmap){
            this.introIamgBitmap = introIamgBitmap;
        }
        public void setViewHoder(ViewHoder viewHoder){
            this.viewHoder = viewHoder;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (introIamgBitmap != null){
                //viewHoder.gridImage.

            }
        }
    }
}
