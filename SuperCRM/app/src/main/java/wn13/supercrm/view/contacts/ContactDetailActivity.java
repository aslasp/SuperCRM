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
import wn13.supercrm.model.Contacts;
import wn13.supercrm.model.Customer;

public class ContactDetailActivity extends AppCompatActivity {

    private Contacts contact;

    private EditText id,customer,name,age,gender,mobile,tel,qq,wechat,wang,email,addr,zipcode,remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contact=(Contacts) getIntent().getSerializableExtra("c");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(contact.getContactsname());
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
        id=(EditText)findViewById(R.id.contactDetailIdET);
        customer=(EditText)findViewById(R.id.contactDetailCustomerNameET);
        name=(EditText)findViewById(R.id.contactDetailNameET);
        age=(EditText)findViewById(R.id.contactDetailAgeET);
        gender=(EditText)findViewById(R.id.contactDetailGenderET);
        mobile=(EditText)findViewById(R.id.contactDetailMobileET);
        tel=(EditText)findViewById(R.id.contactDetailTelET);
        qq=(EditText)findViewById(R.id.contactDetailQQET);
        wechat=(EditText)findViewById(R.id.contactDetailWeChatET);
        wang=(EditText)findViewById(R.id.contactDetailWangET);
        email=(EditText)findViewById(R.id.contactDetailEMailET);
        addr=(EditText)findViewById(R.id.contactDetailAddrET);
        zipcode=(EditText)findViewById(R.id.contactDetailZipcodeET);
        remark=(EditText)findViewById(R.id.contactDetailRemarkET);

        id.setText(String.valueOf(contact.getContactsid()));
        customer.setText(contact.getCustomername());
        name.setText(contact.getContactsname());
        age.setText(String.valueOf(contact.getContactsage()));
        gender.setText(String.valueOf(contact.getContactsgender()));
        mobile.setText(contact.getContactsmobile());
        tel.setText(contact.getContactstelephone());
        qq.setText(contact.getContactsqq());
        wechat.setText(contact.getContactswechat());
        wang.setText(contact.getContactswangwang());
        email.setText(contact.getContactsemail());
        addr.setText(contact.getContactsaddress());
        zipcode.setText(contact.getContactszipcode());
        remark.setText(contact.getContactsremarks());
    }

    private void setupEditButton(){
        ((Button)findViewById(R.id.contactEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.contact_edit_url))
                        .addParams("contactsid", id.getText().toString())
                        .addParams("customerid", String.valueOf(contact.getCustomerid()))
                        .addParams("contactsname", name.getText().toString())
                        .addParams("contactsage", age.getText().toString())
                        .addParams("contactsgender", gender.getText().toString())
                        .addParams("contactsmobile", mobile.getText().toString())
                        .addParams("contactstelephone", tel.getText().toString())
                        .addParams("contactsemail", email.getText().toString())
                        .addParams("contactsqq", qq.getText().toString())
                        .addParams("contactswechat", wechat.getText().toString())
                        .addParams("contactswangwang", wang.getText().toString())
                        .addParams("contactsaddress", addr.getText().toString())
                        .addParams("contactszipcode", zipcode.getText().toString())
                        .addParams("contactsremarks", remark.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改联系人信息出错",
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
        ((Button)findViewById(R.id.contactDelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.contact_del_url))
                        .addParams("contactsid", String.valueOf(contact.getContactsid()))
                        .addParams("customerid", String.valueOf(contact.getCustomerid()))
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除联系人出错",
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
