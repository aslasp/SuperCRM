package wn13.supercrm.view.trade;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wn13.supercrm.R;


public class TradeDetailRecordFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public TradeDetailRecordFragment() {
        // Required empty public constructor
    }


    public static TradeDetailRecordFragment newInstance(String param1) {
        TradeDetailRecordFragment fragment = new TradeDetailRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_trade_detail_record, container, false);
        ListView listView=(ListView)rootView.findViewById(R.id.tradeDetailRecordListView);
        setupListAdapter(listView);
        return rootView;
    }

    private void setupListAdapter(ListView listView){
        ArrayList<Map<String,String>> data=new ArrayList<>();
        for(int i=1;i<11;i++){
            Map<String,String> tmp=new HashMap<>();
            tmp.put("title","跟客户进行第"+i+"次会面协商");
            tmp.put("time","9月"+i+"日");
            data.add(tmp);
        }
        String[] klist={"title","time"};
        int[] idList={R.id.tradeDetailRecordText,R.id.tradeDetailRecordDate};
        listView.setAdapter(new SimpleAdapter(getActivity(),data,R.layout.list_item_trade_detail_record,klist,idList));
    }
}
