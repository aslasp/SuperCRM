package wn13.supercrm.view.trade;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.view.ViewGroup;
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
import wn13.supercrm.utils.adapters.StaffListAdapter;
import wn13.supercrm.utils.adapters.TradeListAdapter;


public class TradelistFragment extends Fragment {

    private static final String ARG_PARAM1 = "userID";

    private String userID;

    public TradelistFragment() {
        // Required empty public constructor
    }

    public static TradelistFragment newInstance(String userID) {
        TradelistFragment fragment = new TradelistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_tradelist, container, false);
        setHasOptionsMenu(true);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.tradeListSwipeLayout);
        final ListView listView = (ListView) rootView.findViewById(R.id.tradeListView);
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
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trade_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void setupListAdapter(final ListView v){
        OkHttpUtils.post().url((String) getResources().getText(R.string.trade_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(),
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
                        Opportunity tmp=JsonTool.toBean(json.get(String.valueOf(j)).toString(), Opportunity.class);
                        Map<String,String> tmpMap=new HashMap<>();
                        tmpMap.put("title",tmp.getOpportunitytitle());
                        String tradeStatusList[]={"[]","[初步洽谈]","[需求确定]","[方案报价]","[谈判合同]","[赢单]","[输单]"};
                        int stepInt=tmp.getOpportunitystatus();
                        if(stepInt>6||stepInt<0)
                            stepInt=0;
                        tmpMap.put("step",tradeStatusList[stepInt]);
                        String tradeFinished=tmp.getOpportunitystatus()>=5?"1":"0";
                        if("1".equals(tradeFinished)){
                            continue;
                        }
                        tmpMap.put("finished",tradeFinished);
                        tmpMap.put("customer",tmp.getCustomername());
                        String tradeImportant=tmp.getBusinesstype()<=1?"0":"1";
                        tmpMap.put("important",tradeImportant);
                        tradeList.add(tmp);
                        infoList.add(tmpMap);
                    }
                    v.setAdapter(new TradeListAdapter(getActivity(),tradeList,infoList,R.layout.list_item_tradelist));
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
                Toast.makeText(getActivity(),
                        o.getOpportunitytitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
