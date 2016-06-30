package wn13.supercrm.view.contacts;

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

public class AddContactActivity extends AppCompatActivity {

    private ArrayList<Customer> customerList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加联系人");
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        ((Button) findViewById(R.id.contactAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.contact_add_url))
                        .addParams("contactsname", ((EditText)findViewById(R.id.contactAddNameET)).getText().toString())
                        .addParams("customerid", String.valueOf(customerList.get(((Spinner)findViewById(R.id.contactAddCustomerSP)).getSelectedItemPosition()).getCustomerid()))
                        .addParams("contactsage", ((EditText)findViewById(R.id.contactAddAgeET)).getText().toString())
                        .addParams("contactsgender", ((EditText)findViewById(R.id.contactAddGenderET)).getText().toString())
                        .addParams("contactsmobile", ((EditText)findViewById(R.id.contactAddMobileET)).getText().toString())
                        .addParams("contactstelephone", ((EditText)findViewById(R.id.contactAddTelET)).getText().toString())
                        .addParams("contactsemail", ((EditText)findViewById(R.id.contactAddEMailET)).getText().toString())
                        .addParams("contactsqq", ((EditText)findViewById(R.id.contactAddQQET)).getText().toString())
                        .addParams("contactswechat", ((EditText)findViewById(R.id.contactAddWeChatET)).getText().toString())
                        .addParams("contactswangwang", ((EditText)findViewById(R.id.contactAddWangET)).getText().toString())
                        .addParams("contactsaddress", ((EditText)findViewById(R.id.contactAddAddrET)).getText().toString())
                        .addParams("contactszipcode", ((EditText)findViewById(R.id.contactAddZipcodeET)).getText().toString())
                        .addParams("contactsremarks", ((EditText)findViewById(R.id.contactAddRemarkET)).getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加联系人出错", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AddContactActivity.this,
                        "客户寻找异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    String name[]=new String[recordCount];

                    for (int j = 0; j < recordCount; j++) {
                        Customer tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Customer.class);
                        customerList.add(tmp);
                        name[j]=tmp.getCustomername();
                    }
                    ArrayAdapter<String> adapter
                            =new ArrayAdapter<String>(AddContactActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,name);

                    ((Spinner)findViewById(R.id.contactAddCustomerSP)).setAdapter(adapter);
                    //---------------------------------------
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
