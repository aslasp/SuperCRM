package wn13.supercrm.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    TradelistFragment tradelistFragment;
    ContactslistFragment contactslistFragment;
    SchedulelistFragment schedulelistFragment;
    MelistFragment melistFragment;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=getIntent();
        userID=intent.getStringExtra("id");
        tradelistFragment=TradelistFragment.newInstance(userID);
        getFragmentManager().beginTransaction().replace(R.id.main_content,tradelistFragment).commit();


        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tradeTabBtn:{
                        tradelistFragment=TradelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,tradelistFragment).commit();
                    }break;
                    case R.id.contactsTabBtn:{
                        contactslistFragment=ContactslistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,contactslistFragment).commit();
                    }break;
                    case R.id.scheduleTabBtn:{
                        schedulelistFragment=SchedulelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,schedulelistFragment).commit();
                    }break;
                    case R.id.meTabBtn:{
                        melistFragment=MelistFragment.newInstance(userID);
                        getFragmentManager().beginTransaction().replace(R.id.main_content,melistFragment).commit();
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
