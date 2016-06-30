package wn13.supercrm.view;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.RadioGroup;

import wn13.supercrm.R;
import wn13.supercrm.view.contacts.AddContactActivity;
import wn13.supercrm.view.contacts.AddCustomerActivity;
import wn13.supercrm.view.contacts.AddStaffActivity;
import wn13.supercrm.view.contacts.ContactslistFragment;
import wn13.supercrm.view.me.MelistFragment;
import wn13.supercrm.view.schedule.AddScheduleActivity;
import wn13.supercrm.view.schedule.ScheduleHistoryActivity;
import wn13.supercrm.view.schedule.SchedulelistFragment;
import wn13.supercrm.view.trade.AddTradeActivity;
import wn13.supercrm.view.trade.TradeHistoryActivity;
import wn13.supercrm.view.trade.TradelistFragment;

public class MainActivity extends AppCompatActivity{
    Fragment tradeFragment,contactsFragment,meFragment;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=getIntent();
        userID=intent.getStringExtra("id");
        tradeFragment=TradelistFragment.newInstance(userID);
        contactsFragment= ContactslistFragment.newInstance(userID);
        //scheduleFragment=SchedulelistFragment.newInstance(userID);
        meFragment=MelistFragment.newInstance(userID);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,tradeFragment).commit();


        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tradeTabBtn:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,tradeFragment).commit();

                    }break;
                    case R.id.contactsTabBtn:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,contactsFragment).commit();

                    }break;
//                    case R.id.scheduleTabBtn:{
//                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,scheduleFragment).commit();
//                    }break;
                    case R.id.meTabBtn:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,meFragment).commit();
                    }break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i=new Intent();
        i.putExtra("userID",userID);
        switch (item.getItemId()){
            case R.id.searchBtn:{
                i.setClass(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }break;
            case R.id.addTradeBtn:{
                i.setClass(MainActivity.this, AddTradeActivity.class);
                startActivity(i);
            }break;
            case R.id.addContactBtn:{
                i.setClass(MainActivity.this, AddContactActivity.class);
                startActivity(i);
            }break;
//            case R.id.addScheduleBtn:{
//                i.setClass(MainActivity.this, AddScheduleActivity.class);
//                startActivity(i);
//            }break;
            case R.id.addCustomerBtn:{
                i.setClass(MainActivity.this, AddCustomerActivity.class);
                startActivity(i);
            }break;
            case R.id.tradeHistoryBtn:{
                i.setClass(MainActivity.this, TradeHistoryActivity.class);
                startActivity(i);
            }break;
//            case R.id.scheduleHistoryBtn:{
//                i.setClass(MainActivity.this, ScheduleHistoryActivity.class);
//                startActivity(i);
//            }break;
            case R.id.addStaffBtn:{
                i.setClass(MainActivity.this, AddStaffActivity.class);
                startActivity(i);
            }break;
        }
        return true;
    }
}
