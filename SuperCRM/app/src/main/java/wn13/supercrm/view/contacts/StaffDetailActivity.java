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
import wn13.supercrm.model.Staff;

public class StaffDetailActivity extends AppCompatActivity {

    private Staff staff;
    private EditText id,name,department,position,gender,mobile,tel,wechat,email,remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);

        staff=(Staff)getIntent().getSerializableExtra("s");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(staff.getName());
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
        id=(EditText)findViewById(R.id.staffDetailIdET);
        name=(EditText)findViewById(R.id.staffDetailNameET);
        department=(EditText)findViewById(R.id.staffDetailDepartmentET);
        position=(EditText)findViewById(R.id.staffDetailPositionET);
        gender=(EditText)findViewById(R.id.staffDetailGenderET);
        mobile=(EditText)findViewById(R.id.staffDetailMobileET);
        tel=(EditText)findViewById(R.id.staffDetailTelET);
        wechat=(EditText)findViewById(R.id.staffDetailWeChatET);
        email=(EditText)findViewById(R.id.staffDetailEMailET);
        remark=(EditText)findViewById(R.id.staffDetailRemarkET);


        id.setText(String.valueOf(staff.getStaffid()));
        name.setText(staff.getName());
        department.setText(staff.getDepartmentname());
        position.setText(staff.getPosition());
        gender.setText(staff.getGender());
        mobile.setText(staff.getMobile());
        tel.setText(staff.getTel());
        wechat.setText(staff.getWeixinid());
        email.setText(staff.getEmail());
        remark.setText(staff.getStaffremarks());
    }

    private void setupEditButton(){
        ((Button)findViewById(R.id.staffEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.staff_edit_url))
                        .addParams("staffid", String.valueOf(staff.getStaffid()))
                        .addParams("departmentid", String.valueOf(staff.getDepartmentid()))
                        .addParams("name", name.getText().toString())
                        .addParams("position", position.getText().toString())
                        .addParams("tel", tel.getText().toString())
                        .addParams("email", email.getText().toString())
                        .addParams("mobile", mobile.getText().toString())
                        .addParams("gender", gender.getText().toString())
                        .addParams("weixinid", wechat.getText().toString())
                        .addParams("staffremarks", remark.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改员工信息出错",
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
        ((Button)findViewById(R.id.staffDelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.staff_del_url))
                        .addParams("staffid", String.valueOf(staff.getStaffid()))
                        .addParams("departmentid", String.valueOf(staff.getDepartmentid()))
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除员工出错",
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
