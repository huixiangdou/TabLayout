package com.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.tablayout.basic.BasicActivity;
import com.tablayout.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BasicActivity {
    private String TAG = "MAINACTIVITY";
    public static final String[] sTitle = new String[]{"土豆","鸡蛋"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageUtils.initUniversalImageLoader(getApplicationContext());

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);//设置适配器
    }

    @Override
    public void onSuccess(int statusCode, ArrayList arrayList) {
        super.onSuccess(statusCode, arrayList);
    }
}
