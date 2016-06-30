package wn13.supercrm.view.trade;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Customer;
import wn13.supercrm.utils.adapters.CustomerListAdapter;
import wn13.supercrm.values.Global;

public class AddTradeActivity extends AppCompatActivity {

    private ArrayList<Customer> customerList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加商机");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ((EditText)findViewById(R.id.oppoAddStaffET)).setText(Global.userid);

        setupCustomerSP();

        setupSubmitButton();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupSubmitButton(){
        ((Button) findViewById(R.id.oppoAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.oppo_add_url))
                        .addParams("opportunitytitle", ((EditText)findViewById(R.id.oppoAddNameET)).getText().toString())
                        .addParams("customerid", String.valueOf(customerList.get(((Spinner)findViewById(R.id.oppoAddCustomerSP)).getSelectedItemPosition()).getCustomerid()))
                        .addParams("staffid", ((EditText)findViewById(R.id.oppoAddStaffET)).getText().toString())
                        .addParams("estimatedamount", ((EditText)findViewById(R.id.oppoAddMoneyET)).getText().toString())
                        .addParams("opportunitystatus", String.valueOf(((Spinner)findViewById(R.id.oppoAddStatusSP)).getSelectedItemPosition()+1))
                        .addParams("businesstype",String.valueOf(((Spinner)findViewById(R.id.oppoAddTypeSP)).getSelectedItemPosition()+1))
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加商机出错", Toast.LENGTH_SHORT).show();
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


    private void setupCustomerSP(){
        OkHttpUtils.post().url((String) getResources().getText(R.string.all_customer_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(AddTradeActivity.this,
                        "客户寻找异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    String name[]=new String[recordCount];

                    for (int j = 0; j < recordCount; j++) {
                        Customer tmp=JsonTool.toBean(json.get(String.valueOf(j)).toString(), Customer.class);
                        customerList.add(tmp);
                        name[j]=tmp.getCustomername();
                    }
                    ArrayAdapter<String> adapter
                            =new ArrayAdapter<String>(AddTradeActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,name);

                    ((Spinner)findViewById(R.id.oppoAddCustomerSP)).setAdapter(adapter);
                    //---------------------------------------
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
