package wn13.supercrm.view.trade;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Map;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.values.Global;

public class AddFollowActivity extends AppCompatActivity {

    private Opportunity o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_follow);

        o=(Opportunity)getIntent().getSerializableExtra("oppo");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加跟进记录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ((EditText)findViewById(R.id.followAddOppoET)).setText(o.getOpportunitytitle());
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
        ((Button) findViewById(R.id.followAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.follow_add_url))
                        .addParams("sourcetype", String.valueOf(2))
                        .addParams("sourceid", String.valueOf(o.getOpportunityid()))
                        .addParams("creatorid", Global.userid)
                        .addParams("content", ((EditText)findViewById(R.id.followAddContentET)).getText().toString())
                        .addParams("followupremarks", ((EditText)findViewById(R.id.followAddRemarkET)).getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "添加跟进记录出错", Toast.LENGTH_SHORT).show();
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
