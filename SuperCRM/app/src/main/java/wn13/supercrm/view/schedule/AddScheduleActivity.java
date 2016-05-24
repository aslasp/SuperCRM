package wn13.supercrm.view.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import wn13.supercrm.R;

public class AddScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加日程");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //为日期和时间标签加监听
        setupDateTextView();
        setupTimeTextView();

        //为下方返回按钮加监听
        setupBackBtn();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDateTextView(){
        findViewById(R.id.addScheduleDateTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                new DatePickerDialog(AddScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TextView dView=(TextView)findViewById(R.id.addScheduleDateTextView);
                        dView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setupTimeTextView(){
        findViewById(R.id.addScheduleTimeTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ((TextView)findViewById(R.id.addScheduleTimeTextView)).setText(hourOfDay+":"+minute);
                    }
                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
            }
        });
    }

    private void setupBackBtn(){
        findViewById(R.id.addScheduleCloseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
