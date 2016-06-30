package wn13.supercrm.view.trade;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.utils.adapters.TradeListAdapter;

public class TradeHistoryActivity extends AppCompatActivity {
    private ArrayList<Map<String,String>> listItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_history);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("历史交易");
        actionBar.setDisplayHomeAsUpEnabled(true);
        final SwipeRefreshLayout swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.tradeHistoryListSwipeLayout);
        final ListView listView = (ListView)findViewById(R.id.tradeHistoryListView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setupListAdapter(listView);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        setupListAdapter(listView);
        setupItemListener(listView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListAdapter(final ListView v){
        OkHttpUtils.post().url((String) getResources().getText(R.string.trade_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(TradeHistoryActivity.this,
                        "刷新交易列表出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    ArrayList<Opportunity> tradeList = new ArrayList<>();
                    ArrayList<Map<String,String>> infoList=new ArrayList<>();
                    for (int j = 0; j < recordCount; j++) {
                        Opportunity tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Opportunity.class);
                        Map<String,String> tmpMap=new HashMap<>();
                        tmpMap.put("title",tmp.getOpportunitytitle());
                        String tradeStatusList[]={"[]","[初步洽谈]","[需求确定]","[方案报价]","[谈判合同]","[赢单]","[输单]"};
                        int stepInt=tmp.getOpportunitystatus();
                        if(stepInt>6||stepInt<0)
                            stepInt=0;
                        tmpMap.put("step",tradeStatusList[stepInt]);
                        String tradeFinished=tmp.getOpportunitystatus()>=5?"1":"0";
                        if("0".equals(tradeFinished)){
                            continue;
                        }
                        tmpMap.put("finished",tradeFinished);
                        tmpMap.put("customer",tmp.getCustomername());
                        String tradeImportant=tmp.getBusinesstype()<=1?"0":"1";
                        tmpMap.put("important",tradeImportant);
                        tradeList.add(tmp);
                        infoList.add(tmpMap);
                    }
                    v.setAdapter(new TradeListAdapter(TradeHistoryActivity.this,tradeList,infoList,R.layout.list_item_tradelist));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setupItemListener(final ListView v){
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Opportunity o=(Opportunity)v.getAdapter().getItem(position);
                Intent i=new Intent(TradeHistoryActivity.this,TradeDetailActivity.class);
                i.putExtra("oppo",o);
                startActivity(i);
            }
        });
    }
}
