package wn13.supercrm.view.me;

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
import wn13.supercrm.model.Product;
import wn13.supercrm.utils.adapters.ProductAdapter;
import wn13.supercrm.utils.adapters.TradeListAdapter;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("商品信息");
        actionBar.setDisplayHomeAsUpEnabled(true);

        final SwipeRefreshLayout swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.productListSwipeLayout);
        final ListView listView = (ListView)findViewById(R.id.productListView);
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
        OkHttpUtils.post().url((String) getResources().getText(R.string.product_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(ReportActivity.this,
                        "刷新商品列表出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    ArrayList<Product> productList = new ArrayList<>();
                    ArrayList<Map<String,String>> mapList=new ArrayList<>();
                    for (int j = 0; j < recordCount; j++) {
                        Product tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Product.class);
                        productList.add(tmp);
                        HashMap<String,String> tmpMap=new HashMap<String, String>();
                        tmpMap.put("title",tmp.getProductname());
                        mapList.add(tmpMap);
                    }
                    v.setAdapter(new ProductAdapter(ReportActivity.this,productList,mapList,R.layout.list_item_product));
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
                Product p=(Product) v.getAdapter().getItem(position);
                Intent intent=new Intent();
                intent.putExtra("product",(Serializable)p);
                startActivity(new Intent());
            }
        });
    }
}
