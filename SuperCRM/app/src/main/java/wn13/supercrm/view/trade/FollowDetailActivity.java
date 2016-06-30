package wn13.supercrm.view.trade;

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
import wn13.supercrm.model.Follow;

public class FollowDetailActivity extends AppCompatActivity {

    private Follow f;

    private EditText id,time,content,remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_detail);
        f=(Follow)getIntent().getSerializableExtra("f");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(f.getCreatetime());
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
        id=(EditText)findViewById(R.id.followDetailIdET);
        time=(EditText)findViewById(R.id.followDetailTimeET);
        content=(EditText)findViewById(R.id.followDetailContentET);
        remark=(EditText)findViewById(R.id.followDetailRemarkET);

        id.setText(f.getFollowupid());
        time.setText(f.getCreatetime());
        content.setText(f.getContent());
        remark.setText(f.getFollowupremarks());
    }

    private void setupEditButton(){
        ((Button)findViewById(R.id.followEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.follow_edit_url))
                        .addParams("followupid", f.getFollowupid())
                        .addParams("sourcetype", f.getSourcetype())
                        .addParams("sourceid", f.getSourceid())
                        .addParams("creatorid", f.getCreatorid())
                        .addParams("content", content.getText().toString())
                        .addParams("followupremarks", remark.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改跟进记录出错",
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
        ((Button)findViewById(R.id.followDelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.follow_del_url))
                        .addParams("followupid", f.getFollowupid())
                        .addParams("sourcetype", f.getSourcetype())
                        .addParams("sourceid", f.getSourceid())
                        .addParams("creatorid", f.getCreatorid())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除跟进记录出错",
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
