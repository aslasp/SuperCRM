package wn13.supercrm.view.trade;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Follow;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.utils.adapters.FollowAdapter;


public class TradeDetailRecordFragment extends Fragment {

    private static final String ARG_PARAM1 = "oppo";

    private Opportunity o;


    public TradeDetailRecordFragment() {
        // Required empty public constructor
    }


    public static TradeDetailRecordFragment newInstance(Opportunity param1) {
        TradeDetailRecordFragment fragment = new TradeDetailRecordFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            o = (Opportunity)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_trade_detail_record, container, false);
        final ListView listView=(ListView)rootView.findViewById(R.id.tradeDetailRecordListView);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.followSwipeLayout);
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
        setupListListener(listView);
        return rootView;
    }

    private void setupListAdapter(final ListView listView){

        OkHttpUtils.post().url((String) getResources().getText(R.string.follow_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(),
                        "刷新跟进列表出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    ArrayList<Follow> cList = new ArrayList<>();
                    ArrayList<Map<String,String>> mapList=new ArrayList<>();
                    for (int j = 0; j < recordCount; j++) {
                        Follow tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Follow.class);
                        if(tmp.getSourceid().equals(String.valueOf(o.getOpportunityid()))){
                            cList.add(tmp);
                            HashMap<String,String> tmpMap=new HashMap<String, String>();
                            tmpMap.put("time",tmp.getCreatetime());
                            mapList.add(tmpMap);
                        }
                    }
                    listView.setAdapter(new FollowAdapter(getActivity(),cList,mapList,R.layout.list_item_product));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setupListListener(final ListView l){
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(),FollowDetailActivity.class);
                i.putExtra("f",(Follow)l.getAdapter().getItem(position));
                startActivity(i);
            }
        });
    }

}
