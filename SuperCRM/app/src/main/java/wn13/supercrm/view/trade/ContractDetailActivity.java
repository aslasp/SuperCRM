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
import wn13.supercrm.model.Contract;

public class ContractDetailActivity extends AppCompatActivity {

    private Contract c;

    private EditText id,oppo,customer,name,money,start,end,client,our,remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);
        c=(Contract)getIntent().getSerializableExtra("c");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(c.getContracttitle());
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

    private void fillForms() {
        id=(EditText)findViewById(R.id.contractDetailIdET);
        oppo=(EditText)findViewById(R.id.contractDetailOppoET);
        customer=(EditText)findViewById(R.id.contractDetailCustomerET);
        name=(EditText)findViewById(R.id.contractDetailNameET);
        money=(EditText)findViewById(R.id.contractDetailMoneyET);
        start=(EditText)findViewById(R.id.contractDetailStartET);
        end=(EditText)findViewById(R.id.contractDetailEndET);
        client=(EditText)findViewById(R.id.contractDetailClientET);
        our=(EditText)findViewById(R.id.contractDetailOurET);
        remark=(EditText)findViewById(R.id.contractDetailRemarkET);


        id.setText(c.getContractid());
        oppo.setText(c.getOpportunitytitle());
        customer.setText(c.getCustomername());
        name.setText(c.getContracttitle());
        money.setText(c.getTotalamount());
        start.setText(c.getStartdate());
        end.setText(c.getEnddate());
        client.setText(c.getClientcontractor());
        our.setText(c.getOurcontractor());
        remark.setText(c.getContractremarks());
    }

    private void setupEditButton() {
        ((Button) findViewById(R.id.contractEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.contract_edit_url))
                        .addParams("contractid", c.getContractid())
                        .addParams("opportunityid", c.getOpportunityid())
                        .addParams("customerid", c.getCustomerid())
                        .addParams("staffid", c.getStaffid())
                        .addParams("contracttitle", name.getText().toString())
                        .addParams("totalamount", money.getText().toString())
                        .addParams("startdate", start.getText().toString())
                        .addParams("enddate", end.getText().toString())
                        .addParams("clientcontractor", client.getText().toString())
                        .addParams("ourcontractor", our.getText().toString())
                        .addParams("contractremarks", remark.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改合同信息出错",
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
            }
        });
    }

    private void setupDelButton() {
        ((Button) findViewById(R.id.contractDelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.contract_del_url))
                        .addParams("contractid", c.getContractid())
                        .addParams("opportunityid", c.getOpportunityid())
                        .addParams("customerid", c.getCustomerid())
                        .addParams("staffid", c.getStaffid())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除合同出错(服务器不支持)",
                                Toast.LENGTH_LONG).show();
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
