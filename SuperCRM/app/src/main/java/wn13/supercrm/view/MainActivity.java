package wn13.supercrm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import wn13.supercrm.R;
import wn13.supercrm.view.contacts.ContactslistFragment;
import wn13.supercrm.view.me.MelistFragment;
import wn13.supercrm.view.schedule.SchedulelistFragment;
import wn13.supercrm.view.trade.TradelistFragment;

public class MainActivity extends FragmentActivity {
    TradelistFragment tradelistFragment;
    ContactslistFragment contactslistFragment;
    SchedulelistFragment schedulelistFragment;
    MelistFragment melistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        final String userID=intent.getStringExtra("id");
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
}
