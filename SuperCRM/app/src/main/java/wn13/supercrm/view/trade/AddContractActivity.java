package wn13.supercrm.view.trade;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.values.Global;

public class AddContractActivity extends AppCompatActivity {

    private Opportunity o;

    private String startDate;
    private String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);

        o=(Opportunity)getIntent().getSerializableExtra("oppo");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加合同");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ((EditText)findViewById(R.id.contractAddOppoET)).setText(o.getOpportunitytitle());
        ((EditText)findViewById(R.id.contractAddCustomerET)).setText(o.getCustomername());

        setupStartDate();
        setupEndDate();
        setupSubmitButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupStartDate(){
        findViewById(R.id.contractAddStartET).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                new DatePickerDialog(AddContractActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String m=(monthOfYear+1)<10?("0"+(monthOfYear+1)):String.valueOf(monthOfYear+1);
                        String d=dayOfMonth<10?("0"+dayOfMonth):String.valueOf(dayOfMonth);
                        ((TextView)findViewById(R.id.contractAddStartET)).setText(year+"-"+m+"-"+d+" 00:00:00");
                    }
                }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void setupEndDate(){
        findViewById(R.id.contractAddEndET).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                new DatePickerDialog(AddContractActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String m=(monthOfYear+1)<10?("0"+(monthOfYear+1)):String.valueOf(monthOfYear+1);
                        String d=dayOfMonth<10?("0"+dayOfMonth):String.valueOf(dayOfMonth);
                        ((TextView)findViewById(R.id.contractAddEndET)).setText(year+"-"+m+"-"+d+" 00:00:00");
                    }
                }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setupSubmitButton(){
        ((Button) findViewById(R.id.contractAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.contract_add_url))
                        .addParams("opportunityid", String.valueOf(o.getOpportunityid()))
                        .addParams("staffid", Global.userid)
                        .addParams("customerid", String.valueOf(o.getCustomerid()))
                        .addParams("contracttitle", ((EditText)findViewById(R.id.contractAddNameET)).getText().toString())
                        .addParams("totalamount", ((EditText)findViewById(R.id.contractAddMoneyET)).getText().toString())
                        .addParams("startdate", ((TextView)findViewById(R.id.contractAddStartET)).getText().toString())
                        .addParams("enddate", ((TextView)findViewById(R.id.contractAddEndET)).getText().toString())
                        .addParams("clientcontractor", ((EditText)findViewById(R.id.contractAddClientET)).getText().toString())
                        .addParams("ourcontractor", ((EditText)findViewById(R.id.contractAddOurET)).getText().toString())
                        .addParams("contractremarks", ((EditText)findViewById(R.id.contractAddRemarkET)).getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加合同出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Toast.makeText(getApplicationContext(),
                                    ("添加"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                onBackPressed();
            }
        });
    }
}
