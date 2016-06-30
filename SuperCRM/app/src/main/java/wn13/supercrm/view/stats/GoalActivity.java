package wn13.supercrm.view.stats;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Contract;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.utils.adapters.TradeListAdapter;
import wn13.supercrm.values.Global;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("业务目标");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupButton();




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupChart(float earned,float toEarn){
        PieChart pieChart=(PieChart) findViewById(R.id.goalPieChart);
        ArrayList<Entry> entry=new ArrayList<>();
        entry.add(new Entry(earned,0));
        entry.add(new Entry(toEarn,1));
        PieDataSet pieDataSet=new PieDataSet(entry,"完成率");
        ArrayList<String> xVals=new ArrayList<>();
        xVals.add("已完成");
        xVals.add("未完成");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieChart.setData(new PieData(xVals,pieDataSet));
        pieChart.setDescription("当月计划完成情况");
    }

    private void setupButton(){
        ((Button)findViewById(R.id.goalStartBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year=((EditText)findViewById(R.id.goalYear)).getText().toString();
                String month=((EditText)findViewById(R.id.goalMonth)).getText().toString();
                String money=((EditText)findViewById(R.id.goalMoney)).getText().toString();
                drawChart(year,month,money);
            }
        });
    }

    private void drawChart(String y, String m, final String money){
        final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr="2016-06-01 00:00:00";
        String endStr="2016-07-01 00:00:00";

        if("5".equals(m)){
            startStr="2016-05-01 00:00:00";
            endStr="2016-06-01 00:00:00";
        }

        else if("7".equals(m)){
            startStr="2016-07-01 00:00:00";
            endStr="2016-08-01 00:00:00";
        }
        final String finalStartStr = startStr;
        final String finalEndStr = endStr;
        OkHttpUtils.post().url((String) getResources().getText(R.string.contract_common_query))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(GoalActivity.this,
                        "获取目标数据出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    float earned=0.0f;
                    Date d1=sdf.parse(finalStartStr);
                    Date d2=sdf.parse(finalEndStr);

                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    for (int j = 0; j < recordCount; j++) {
                        Contract tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Contract.class);
                        Pattern p=Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");

                        if(!p.matcher(tmp.getStartdate()).matches()){
                            continue;
                        }
                        Date date=sdf.parse(tmp.getStartdate());


                        if((Global.userid.equals(String.valueOf(tmp.getStaffid())))&&(date.getTime()>=d1.getTime())&&(date.getTime()<d2.getTime())){
                            earned+=Float.parseFloat(tmp.getTotalamount());
                        }

                    }

                    setupChart(earned,Float.parseFloat(money)-earned);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
