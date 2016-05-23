package wn13.supercrm.view.trade;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wn13.supercrm.R;

public class TradeDetailInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "info";

    private Map<String,String> info;

    public TradeDetailInfoFragment() {
        // Required empty public constructor
    }

    public static TradeDetailInfoFragment newInstance(Map<String,String> param1) {
        TradeDetailInfoFragment fragment = new TradeDetailInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            info = (Map<String,String>)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_trade_detail_info, container, false);
        ListView infoListView=(ListView)rootView.findViewById(R.id.tradeDetailInfoListView);
        setupInfoListView(infoListView);
        ListView contractListView=(ListView)rootView.findViewById(R.id.tradeDetailContractListView);
        setupContractListView(contractListView);
        if(info.get("finished").equals("1")){
            rootView.findViewById(R.id.tradeCloseBtn).setVisibility(View.GONE);
            rootView.findViewById(R.id.tradeSuccessBtn).setVisibility(View.GONE);
        }
        return rootView;
    }

    private void setupInfoListView(ListView listView){
        ArrayList<Map<String,String>> data=new ArrayList<>();
        Map<String,String> customerMap=new HashMap<>();
        customerMap.put("title","客户");
        customerMap.put("data",info.get("customer"));

        Map<String,String> statusMap=new HashMap<>();
        statusMap.put("title","交易进展");
        statusMap.put("data","合同谈判");

        Map<String,String> typeMap=new HashMap<>();
        typeMap.put("title","商机类型");
        typeMap.put("data","重要商机");

        Map<String,String> moneyMap=new HashMap<>();
        moneyMap.put("title","预计销售额");
        moneyMap.put("data","500000");

        data.add(customerMap);
        data.add(statusMap);
        data.add(typeMap);
        data.add(moneyMap);

        String[] kList={"title","data"};
        int[] idList={R.id.tradeDetailInfoTitle,R.id.tradeDetailInfoData};

        listView.setAdapter(new SimpleAdapter(getActivity(),data,R.layout.list_item_trade_detail_info,kList,idList));
    }

    private void setupContractListView(ListView listView){
        ArrayList<Map<String,String>> data=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,String> tmp=new HashMap<>();
            tmp.put("title","超级重要的合同"+i);
            tmp.put("date","9月1"+i+"日");
            data.add(tmp);
        }

        String[] kList={"title","date"};
        int[] idList={R.id.tradeDetailContractTitle,R.id.tradeDetailContractDate};
        listView.setAdapter(new SimpleAdapter(getActivity(),data,R.layout.list_item_trade_detail_contract,kList,idList));
    }
}
