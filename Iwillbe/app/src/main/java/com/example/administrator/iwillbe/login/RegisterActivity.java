package com.example.administrator.iwillbe.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.iwillbe.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/12.
 */
public class RegisterActivity extends Activity {
    ImageView returnBut;//返回按钮
    EditText phoneEdit;//手机号输入框
    EditText codeEdit;//验证码输入框
    EditText passwrdEdit;//密码输入框
    EditText retypeEdit;//再次输入框
    CheckBox consentCheckbox;//同意确认
    TextView consentText;//网络协议说明书
    Button confirmBut;//确认按钮
    TextView gainCodeBut;//获取验证码按钮
    boolean isMatched;
    boolean iscode;
    boolean ispassword;
    boolean iscodBute;
    boolean isconfirmBut;
    boolean ischeckbox;
    String password;
    String repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        returnBut = (ImageView) findViewById(R.id.return_but);
        phoneEdit = (EditText) findViewById(R.id.phone_edit);
        gainCodeBut = (TextView) findViewById(R.id.gain_code_but);
        codeEdit = (EditText) findViewById(R.id.code_edit);
        passwrdEdit = (EditText) findViewById(R.id.passwrd_edit);
        retypeEdit = (EditText) findViewById(R.id.retype_edit);
        consentCheckbox = (CheckBox) findViewById(R.id.consent_checkbox);
        consentText = (TextView) findViewById(R.id.consent_text);
        confirmBut = (Button) findViewById(R.id.confirm_but);

        returnBut.setOnClickListener(onClickListener);
        confirmBut.setOnClickListener(onClickListener);
        //gainCodeBut.setOnClickListener(onClickListener);
        phoneEdit.addTextChangedListener(phoneWatcher);
        codeEdit.addTextChangedListener(codeWatcher);
        passwrdEdit.addTextChangedListener(passwordWatcher);
        retypeEdit.addTextChangedListener(repasswordWatcher);
        consentCheckbox.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.return_but:
                    finish();
                    break;
                case R.id.confirm_but:
                    if (isconfirmBut == true) {
                        intent = new Intent(RegisterActivity.this, LonginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "注册成功！！！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.gain_code_but:
                    if (iscodBute == true) {

                    }
                    break;
            }
        }
    };

    //手机号输入框监听事件
    TextWatcher phoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //下面是国内 13、15、18开头的手机号正则表达式。
            String editText = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
            Pattern pattern = Pattern.compile(editText);
            Matcher matcher = pattern.matcher(charSequence);
            isMatched = matcher.matches();
            Log.e("ryx", "onTextChanged: 正在输入手机号" + isMatched);
            if (isMatched == true) {
                gainCodeBut.setBackgroundColor(getResources().getColor(R.color.red));
                gainCodeBut.setTextColor(getResources().getColor(R.color.white));
                iscodBute = true;
            } else {
                gainCodeBut.setBackgroundColor(getResources().getColor(R.color.white));
                gainCodeBut.setTextColor(getResources().getColor(R.color.black));
                iscodBute = false;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //验证码输入框监听事件
    TextWatcher codeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() == 6) {
                iscode = true;
                Log.i("iscode==========>", "" + iscode);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //密码输入框监听事件
    TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            password = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //再次输入密码监听事件
    TextWatcher repasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            repassword = charSequence.toString();
            if (repassword.equals(password)) {
                ispassword = true;
                Log.i("ispassword==========>", "" + ispassword);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //chedbox监听事件
    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            ischeckbox = b;
            Log.i("ischeckbox======>", "" + ischeckbox);
            if ((ischeckbox == true) && (iscode == true)
                    && (ispassword == true)) {
                Log.i("iscode+++++++>", "" + iscode);
                Log.i("ispassword++++++>", "" + ispassword);
                Log.i("ischeckbox++++++>", "" + ischeckbox);
                confirmBut.setBackgroundDrawable(getResources().getDrawable(R.drawable.register_button_re));
                confirmBut.setTextColor(getResources().getColor(R.color.white));
                isconfirmBut = true;
            } else {
                confirmBut.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_in_button));
                confirmBut.setTextColor(getResources().getColor(R.color.black));
                isconfirmBut = false;
            }
        }
    };
}
