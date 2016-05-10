package wn13.supercrm.view.trade;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.utils.TradeListAdapter;


public class TradelistFragment extends Fragment {

    private static final String ARG_PARAM1 = "userID";

    private String userID;
    private ArrayList<Map<String,String>> listItems=new ArrayList<>();

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
        ListView listView=(ListView) rootView.findViewById(R.id.tradeListView);
        setupListAdapter(listView);
        setupItemListener(listView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trade_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void setupListAdapter(ListView v){
        for(int i=0;i<10;i++){
            Map<String,String> tmp=new HashMap<>();
            tmp.put("title","金坷垃订单"+i);
            tmp.put("step","[谈判中]");
            tmp.put("customer","南京市第"+i+"中学");
            listItems.add(tmp);
        }
        String keyList[]={"title","step","customer"};
        int idList[]={R.id.list_item_tradelist_title,R.id.list_item_tradelist_step,R.id.list_item_tradelist_customer};
        TradeListAdapter adapter=new TradeListAdapter(getActivity(),listItems,R.layout.list_item_tradelist,keyList,idList);
        v.setAdapter(adapter);
    }

    private void setupItemListener(ListView v){
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(),TradeDetailActivity.class);
                i.putExtra("userID",userID);
                i.putExtra("tradeInfo", (Serializable) listItems.get(position));
                startActivity(i);
            }
        });
    }
}
