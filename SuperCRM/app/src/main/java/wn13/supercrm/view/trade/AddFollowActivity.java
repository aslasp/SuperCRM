package wn13.supercrm.view.trade;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Map;

import wn13.supercrm.R;

public class AddFollowActivity extends AppCompatActivity {

    private Map<String,String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_follow);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加跟进记录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        info=(Map<String,String>)getIntent().getSerializableExtra("info");
        TextView tradeNameView=(TextView)findViewById(R.id.addFollowTradeTitleTextView);
        TextView tradeCustomerView=(TextView)findViewById(R.id.addFollowCustomerTextView);
        tradeNameView.setText(info.get("title"));
        tradeCustomerView.setText(info.get("customer"));
        // 为日期TextView增加监听
        setupDateView();
        //返回键
        setupBackBtn();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupDateView(){
        findViewById(R.id.addFollowDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                new DatePickerDialog(AddFollowActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TextView dView=(TextView)findViewById(R.id.addFollowDate);
                        dView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setupBackBtn(){
        findViewById(R.id.addFollowCloseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
