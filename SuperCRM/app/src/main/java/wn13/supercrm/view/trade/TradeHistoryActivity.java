package wn13.supercrm.view.trade;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wn13.supercrm.R;
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

        ListView listView = (ListView)findViewById(R.id.tradeHistoryListView);
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

    private void setupListAdapter(ListView v){
        for(int i=0;i<30;i++){
            Map<String,String> tmp=new HashMap<>();
            tmp.put("title","金坷垃订单"+i);
            tmp.put("step","[交易完成]");
            tmp.put("finished","1");
            tmp.put("customer","南京市第"+i+"中学");
            if(i==6){
                tmp.put("important","y");
            }else{
                tmp.put("important","n");
            }

            listItems.add(tmp);
        }
        String keyList[]={"title","step","customer"};
        int idList[]={R.id.list_item_tradelist_title,R.id.list_item_tradelist_step,R.id.list_item_tradelist_customer};
        TradeListAdapter adapter=new TradeListAdapter(TradeHistoryActivity.this,listItems,R.layout.list_item_tradelist,keyList,idList);
        v.setAdapter(adapter);
    }

    private void setupItemListener(ListView v){
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(TradeHistoryActivity.this,TradeDetailActivity.class);
                i.putExtra("tradeInfo", (Serializable) listItems.get(position));
                startActivity(i);
            }
        });
    }
}
