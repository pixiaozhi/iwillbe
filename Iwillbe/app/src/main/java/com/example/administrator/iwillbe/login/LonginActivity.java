package com.example.administrator.iwillbe.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.iwillbe.MainActivity;
import com.example.administrator.iwillbe.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/12.
 */
public class LonginActivity extends Activity {
    ImageView longinHeadImag;//登录头像显示
    EditText idRdit;//输入账号
    EditText passwrdEdit;//输入密码
    Button loginButton;//登录按钮
    TextView loginIssueBuuton;//遇到问题点击按钮
    TextView registerBtton;//注册
    TextView phoneButton;//手机登录
    TextView weibologinButton;//微博登录
    TextView qqloginBtton;//QQ登录
    boolean isMatched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        longinHeadImag = (ImageView) findViewById(R.id.longin_head_imag);
        idRdit = (EditText) findViewById(R.id.id_rdit);
        passwrdEdit = (EditText) findViewById(R.id.passwrd_edit);
        loginButton = (Button) findViewById(R.id.login_button);
        loginIssueBuuton = (TextView) findViewById(R.id.login_issue_buuton);
        registerBtton = (TextView) findViewById(R.id.register_btton);
        phoneButton = (TextView) findViewById(R.id.phone_button);
        weibologinButton = (TextView) findViewById(R.id.weibologin_button);
        qqloginBtton = (TextView) findViewById(R.id.qqlogin_btton);

        loginIssueBuuton.setOnClickListener(onClickListener);
        registerBtton.setOnClickListener(onClickListener);
        idRdit.addTextChangedListener(textWatcher);
        passwrdEdit.addTextChangedListener(passwordWatcher);
        loginButton.setOnClickListener(onClickListener);
    }

    //点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.login_button:
                    intent = new Intent(LonginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_issue_buuton:
                    intent = new Intent(LonginActivity.this, SetPassword.class);
                    startActivity(intent);
                    break;
                case R.id.register_btton:
                    intent = new Intent(LonginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    //    账号输入框判断
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Toast.makeText(LonginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Toast.makeText(LonginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            //下面是国内 13、15、18开头的手机号正则表达式。
            String editText = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
            Pattern pattern = Pattern.compile(editText);
            Matcher matcher = pattern.matcher(charSequence);
            isMatched = matcher.matches();
            Log.e("ryx", "onTextChanged: 正在输入手机号" + isMatched);

        }

        @Override
        public void afterTextChanged(Editable editable) {
//            if (isMatched == false) {
//                Toast.makeText(LonginActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
//            }
        }
    };
    //密码输入框
    TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //  Toast.makeText(LonginActivity.this, "请输入6-16位的密码", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Toast.makeText(LonginActivity.this, "请输入6-16位的密码", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void afterTextChanged(Editable editable) {
//            String password = editable.toString();
//            if (password.length() > 6 && password.length() < 16) {
//                Toast.makeText(LonginActivity.this, "输入密码正确", Toast.LENGTH_SHORT).show();
//            } else if (password.length() > 6) {
//                Toast.makeText(LonginActivity.this, "输入密码要大于6位数", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(LonginActivity.this, "输入密码要小于16位数", Toast.LENGTH_SHORT).show();
//            }
        }
    };

}
