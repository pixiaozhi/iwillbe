package com.example.administrator.iwillbe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.iwillbe.fragment.FindFragment;
import com.example.administrator.iwillbe.fragment.Homepage;
import com.example.administrator.iwillbe.fragment.MessageFragment;
import com.example.administrator.iwillbe.fragment.MineFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    ViewPager viewPager;
    RadioGroup radioGroup;
    RadioButton mine;
    RadioButton find;
    RadioButton homepage1;
    RadioButton massage;
    ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_daohang);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        homepage1 = (RadioButton) findViewById(R.id.homepage);
        mine = (RadioButton) findViewById(R.id.mine);
        find = (RadioButton) findViewById(R.id.find);
        massage = (RadioButton) findViewById(R.id.massage);
        viewPager= (ViewPager) findViewById(R.id.viewpager);


        fragmentArrayList = new ArrayList<Fragment>();
        Homepage homepage = new Homepage();
        FindFragment findFragment = new FindFragment();
        MessageFragment messageFragment = new MessageFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentArrayList.add(homepage);
        fragmentArrayList.add(findFragment);
        fragmentArrayList.add(messageFragment);
        fragmentArrayList.add(mineFragment);

        InstantaneousPagerAdapter instantaneousPagerAdapter = new InstantaneousPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instantaneousPagerAdapter);

        viewPager.setOnPageChangeListener(onPageChangeListener);
        massage.setOnClickListener(onClickListener);
        homepage1.setOnClickListener(onClickListener);
        find.setOnClickListener(onClickListener);
        mine.setOnClickListener(onClickListener);

    }

    //    重写FragmentPagerAdapter方法
    public class InstantaneousPagerAdapter extends FragmentPagerAdapter {
        public InstantaneousPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    homepage1.setChecked(true);
                    homepage1.setTextColor(getResources().getColor(R.color.red));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                case 1:
                    find.setChecked(true);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.red));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                case 2:
                    massage.setChecked(true);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.red));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                case 3:
                    mine.setChecked(true);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.red));
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //点击首页
                case R.id.homepage:
                    viewPager.setCurrentItem(0);
                    homepage1.setTextColor(getResources().getColor(R.color.red));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                //点击发现
                case R.id.find:
                    viewPager.setCurrentItem(1);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.red));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                //点击消息
                case R.id.massage:
                    viewPager.setCurrentItem(2);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.red));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.homepage_black));
                    break;
                //点击我的
                case R.id.mine:
                    viewPager.setCurrentItem(3);
                    homepage1.setTextColor(getResources().getColor(R.color.homepage_black));
                    massage.setTextColor(getResources().getColor(R.color.homepage_black));
                    find.setTextColor(getResources().getColor(R.color.homepage_black));
                    mine.setTextColor(getResources().getColor(R.color.red));
                    break;
            }
        }
    };
}
