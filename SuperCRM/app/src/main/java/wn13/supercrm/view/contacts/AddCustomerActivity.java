package wn13.supercrm.view.contacts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.values.Global;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加客户");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ((EditText)findViewById(R.id.customerAddStaffET)).setText(Global.userid);

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
        ((Button) findViewById(R.id.customerAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.customer_add_url))
                        .addParams("customername", ((EditText)findViewById(R.id.customerAddNameET)).getText().toString())
                        .addParams("staffid", ((EditText)findViewById(R.id.customerAddStaffET)).getText().toString())
                        .addParams("customertype", ((EditText)findViewById(R.id.customerAddTypeET)).getText().toString())
                        .addParams("telephone", ((EditText)findViewById(R.id.customerAddTelET)).getText().toString())
                        .addParams("email", ((EditText)findViewById(R.id.customerAddEMailET)).getText().toString())
                        .addParams("website", ((EditText)findViewById(R.id.customerAddWebsiteET)).getText().toString())
                        .addParams("address", ((EditText)findViewById(R.id.customerAddAddrET)).getText().toString())
                        .addParams("zipcode", ((EditText)findViewById(R.id.customerAddZipcodeET)).getText().toString())
                        .addParams("profile", ((EditText)findViewById(R.id.customerAddProfileET)).getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加客户出错", Toast.LENGTH_SHORT).show();
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
