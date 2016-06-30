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
import wn13.supercrm.model.Department;
import wn13.supercrm.model.Staff;

public class AddStaffActivity extends AppCompatActivity {

    private ArrayList<Department> deList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加员工");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupDepartmentSP();

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
        ((Button) findViewById(R.id.staffAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.staff_add_url))
                        .addParams("name", ((EditText)findViewById(R.id.staffAddNameET)).getText().toString())
                        .addParams("departmentid", String.valueOf(deList.get(((Spinner)findViewById(R.id.staffAddDepartmentSP)).getSelectedItemPosition()).getDepartmentid()))
                        .addParams("position", ((EditText)findViewById(R.id.staffAddPositionET)).getText().toString())
                        .addParams("gender", ((EditText)findViewById(R.id.staffAddGenderET)).getText().toString())
                        .addParams("mobile", ((EditText)findViewById(R.id.staffAddMobileET)).getText().toString())
                        .addParams("tel", ((EditText)findViewById(R.id.staffAddTelET)).getText().toString())
                        .addParams("weixinid", ((EditText)findViewById(R.id.staffAddWeChatET)).getText().toString())
                        .addParams("email", ((EditText)findViewById(R.id.staffAddEMailET)).getText().toString())
                        .addParams("staffremarks", ((EditText)findViewById(R.id.staffAddRemarkET)).getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加员工出错", Toast.LENGTH_SHORT).show();
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

    private void setupDepartmentSP(){
        OkHttpUtils.post().url((String) getResources().getText(R.string.department_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(AddStaffActivity.this,
                        "部门寻找异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    String name[]=new String[recordCount];

                    for (int j = 0; j < recordCount; j++) {
                        Department tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Department.class);
                        deList.add(tmp);
                        name[j]=tmp.getDepartmentname();
                    }
                    ArrayAdapter<String> adapter
                            =new ArrayAdapter<String>(AddStaffActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,name);

                    ((Spinner)findViewById(R.id.staffAddDepartmentSP)).setAdapter(adapter);
                    //---------------------------------------
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
