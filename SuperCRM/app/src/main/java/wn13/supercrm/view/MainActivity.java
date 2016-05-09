package wn13.supercrm.view;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;

import wn13.supercrm.R;
import wn13.supercrm.view.contacts.AddContactActivity;
import wn13.supercrm.view.contacts.AddCustomerActivity;
import wn13.supercrm.view.contacts.ContactslistFragment;
import wn13.supercrm.view.me.MelistFragment;
import wn13.supercrm.view.schedule.AddScheduleActivity;
import wn13.supercrm.view.schedule.SchedulelistFragment;
import wn13.supercrm.view.trade.AddTradeActivity;
import wn13.supercrm.view.trade.TradelistFragment;

public class MainActivity extends AppCompatActivity{
    Fragment fragment;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=getIntent();
        userID=intent.getStringExtra("id");
        fragment=TradelistFragment.newInstance(userID);
        getFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();


        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tradeTabBtn:{
                        fragment=TradelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();

                    }break;
                    case R.id.contactsTabBtn:{
                        fragment=ContactslistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();

                    }break;
                    case R.id.scheduleTabBtn:{
                        fragment=SchedulelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();
                    }break;
                    case R.id.meTabBtn:{
                        fragment=MelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,fragment).commit();
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
            case R.id.addScheduleBtn:{
                i.setClass(MainActivity.this, AddScheduleActivity.class);
                startActivity(i);
            }break;
            case R.id.addCustomerBtn:{
                i.setClass(MainActivity.this, AddCustomerActivity.class);
                startActivity(i);
            }break;
        }
        return true;
    }
}
