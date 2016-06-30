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
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.model.Opportunity;

public class TradeDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Opportunity o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_detail);
        //获得商机
        o=(Opportunity)getIntent().getSerializableExtra("oppo");
        //设置ActionBar标题
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(o.getOpportunitytitle());
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
        if(1<=o.getOpportunitystatus()&&o.getOpportunitystatus()<=4) {
            getMenuInflater().inflate(R.menu.menu_trade_detail, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
            case R.id.addFollowBtn:{
                Intent i=new Intent(this,AddFollowActivity.class);
                i.putExtra("oppo",o);
                startActivity(i);
            }break;

            case R.id.addContractBtn:{
                Intent i=new Intent(this,AddContractActivity.class);
                i.putExtra("oppo",o);
                startActivity(i);
            }break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager){
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        Fragment infoFragment=TradeDetailInfoFragment.newInstance(o);
        Fragment recordFragment=TradeDetailRecordFragment.newInstance(o);
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
