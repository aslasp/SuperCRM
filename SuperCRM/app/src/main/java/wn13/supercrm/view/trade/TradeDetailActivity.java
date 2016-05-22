package wn13.supercrm.view.trade;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wn13.supercrm.R;

public class TradeDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;

    Map<String,String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_detail);
        //获得交易信息
        Intent i=getIntent();
        info=(Map<String,String>)i.getSerializableExtra("tradeInfo");
        //设置ActionBar标题
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(info.get("title"));
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置ViewPager
        viewPager=(ViewPager)findViewById(R.id.tradeDetailViewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tradeDetailTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("交易信息"));
        tabLayout.addTab(tabLayout.newTab().setText("跟进记录"));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trade_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
            case R.id.addFollowBtn:{
                Intent i=new Intent(this,AddFollowActivity.class);
                i.putExtra("info",(Serializable)info);
                startActivity(i);
            }break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager){
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        Fragment infoFragment=TradeDetailInfoFragment.newInstance(info);
        Fragment recordFragment=TradeDetailRecordFragment.newInstance("");
        adapter.addFragment(infoFragment,"交易信息");
        adapter.addFragment(recordFragment,"跟进记录");
        viewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
