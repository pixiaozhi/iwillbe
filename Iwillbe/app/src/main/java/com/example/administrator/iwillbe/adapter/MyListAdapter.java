package com.example.administrator.iwillbe.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.iwillbe.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/16.
 */
public class MyListAdapter extends BaseAdapter {

    private final Context mContext;
    private final HashMap<Integer, String> mData;
    private int mTouchItemPosition = -1;
    private OnChangedTextListener onChangedTextListener;
    public MyListAdapter(Context context, HashMap<Integer, String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public HashMap<Integer, String> getmData() {
        return mData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_person_homepage, null);
            viewHolder.editText = (EditText) convertView.findViewById(R.id.comment_edit);
            viewHolder.sendBtn = (TextView) convertView.findViewById(R.id.send_btn);
            viewHolder.mTextWatcher = new MyTextChangeWatch();
            //设置数据监听
            viewHolder.editText.addTextChangedListener(viewHolder.mTextWatcher);
            viewHolder.upDataPosition(position);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.upDataPosition(position);
        }
        viewHolder.editText.setText(mData.get(position));
        viewHolder.editText.setTag(position);
        viewHolder.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //使用getTag 记录position
                mTouchItemPosition = (int) v.getTag();
                return false;
            }
        });
        /**
         * 解决焦点问题
         * 当editView获取焦点时,listview会重新绘制,致使editView的焦点光标失去
         */
        if (mTouchItemPosition == position) {
            viewHolder.editText.requestFocus();
            viewHolder.editText.setSelection(viewHolder.editText.getText().length());
        } else {
            viewHolder.editText.clearFocus();
        }
        return convertView;
    }

    class ViewHolder {
        EditText editText;
        TextView sendBtn;
        MyTextChangeWatch mTextWatcher;
        //记录position,防止数据复用时,错乱
        public void upDataPosition(int position) {
            mTextWatcher.upDataPosition(position);
        }
    }

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
            mData.put(position, s.toString());
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
