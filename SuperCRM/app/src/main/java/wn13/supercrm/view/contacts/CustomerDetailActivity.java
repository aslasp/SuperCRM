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
import wn13.supercrm.model.Customer;

public class CustomerDetailActivity extends AppCompatActivity {

    private Customer customer;
    private EditText idET,staffET,nameET,typeET,telET,emailET,websiteET,addrET,zipcodeET,profileET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        customer=(Customer)getIntent().getSerializableExtra("customer");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(customer.getCustomername());
        actionBar.setDisplayHomeAsUpEnabled(true);

        fillForms();

        setupEditButton();
        setupDelButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillForms(){
        idET=(EditText)findViewById(R.id.customerDetailIdET);
        staffET=(EditText)findViewById(R.id.customerDetailStaffET);
        nameET=(EditText)findViewById(R.id.customerDetailNameET);
        typeET=(EditText)findViewById(R.id.customerDetailTypeET);
        telET=(EditText)findViewById(R.id.customerDetailTelET);
        emailET=(EditText)findViewById(R.id.customerDetailEMailET);
        websiteET=(EditText)findViewById(R.id.customerDetailWebsiteET);
        addrET=(EditText)findViewById(R.id.customerDetailAddrET);
        zipcodeET=(EditText)findViewById(R.id.customerDetailZipcodeET);
        profileET=(EditText)findViewById(R.id.customerDetailProfileET);

        idET.setText(String.valueOf(customer.getCustomerid()));
        staffET.setText(customer.getName());
        nameET.setText(customer.getCustomername());
        typeET.setText(String.valueOf(customer.getCustomertype()));
        telET.setText(customer.getTelephone());
        emailET.setText(customer.getEmail());
        websiteET.setText(customer.getWebsite());
        addrET.setText(customer.getAddress());
        zipcodeET.setText(customer.getZipcode());
        profileET.setText(customer.getProfile());
    }

    private void setupEditButton(){
        ((Button)findViewById(R.id.customerEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.customer_edit_url))
                        .addParams("customerid", idET.getText().toString())
                        .addParams("staffid", String.valueOf(customer.getStaffid()))
                        .addParams("customername", nameET.getText().toString())
                        .addParams("customertype", typeET.getText().toString())
                        .addParams("telephone", telET.getText().toString())
                        .addParams("email", emailET.getText().toString())
                        .addParams("website", websiteET.getText().toString())
                        .addParams("address", addrET.getText().toString())
                        .addParams("zipcode", zipcodeET.getText().toString())
                        .addParams("profile", profileET.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改客户信息出错",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Toast.makeText(getApplicationContext(),
                                    ("修改"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                onBackPressed();
            }

        });

    }

    private void setupDelButton(){
        ((Button)findViewById(R.id.customerDelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.customer_del_url))
                        .addParams("customerid", String.valueOf(customer.getCustomerid())).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除客户出错",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int id) {
                        try {
                            JSONObject json = new JSONObject(s);

                            Toast.makeText(getApplicationContext(),
                                    ("删除"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();


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
