package wn13.supercrm.view.trade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
import wn13.supercrm.model.Contract;
import wn13.supercrm.model.Opportunity;
import wn13.supercrm.utils.adapters.ContractAdapter;
import wn13.supercrm.utils.adapters.ProductAdapter;

public class TradeDetailInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "oppo";

    private Opportunity o;

    private EditText id,name,customer,money;

    private Spinner type,status;

    public TradeDetailInfoFragment() {
        // Required empty public constructor
    }

    public static TradeDetailInfoFragment newInstance(Opportunity param1) {
        TradeDetailInfoFragment fragment = new TradeDetailInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            o = (Opportunity) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_trade_detail_info, container, false);

        ListView contractListView=(ListView)rootView.findViewById(R.id.oppoDetailContractListView);
        setupContractListView(contractListView);
        setupContractListViewListener(contractListView);

        fillForms(rootView);

        setupEditButton(rootView);

        return rootView;
    }


    private void setupContractListView(final ListView listView) {
        OkHttpUtils.post().url((String) getResources().getText(R.string.contract_common_query))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(),
                        "刷新合同列表出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    ArrayList<Contract> cList = new ArrayList<>();
                    ArrayList<Map<String,String>> mapList=new ArrayList<>();
                    for (int j = 0; j < recordCount; j++) {
                        Contract tmp= JsonTool.toBean(json.get(String.valueOf(j)).toString(), Contract.class);
                        if(tmp.getOpportunityid().equals(String.valueOf(o.getOpportunityid()))){
                            cList.add(tmp);
                            HashMap<String,String> tmpMap=new HashMap<String, String>();
                            tmpMap.put("title",tmp.getContracttitle());
                            mapList.add(tmpMap);
                        }
                    }
                    listView.setAdapter(new ContractAdapter(getActivity(),cList,mapList,R.layout.list_item_product));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fillForms(View rootView){
        id=(EditText)rootView.findViewById(R.id.oppoDetailIdET);
        name=(EditText)rootView.findViewById(R.id.oppoDetailNameET);
        customer=(EditText)rootView.findViewById(R.id.oppoDetailCustomerET);
        money=(EditText)rootView.findViewById(R.id.oppoDetailEstiMoneyET);
        type=(Spinner)rootView.findViewById(R.id.oppoDetailTypeSP);
        status=(Spinner)rootView.findViewById(R.id.oppoDetailStatusSP);

        id.setText(String.valueOf(o.getOpportunityid()));
        name.setText(o.getOpportunitytitle());
        customer.setText(o.getCustomername());
        money.setText(String.valueOf(o.getEstimatedamount()));

        type.setSelection(o.getBusinesstype()-1);
        status.setSelection(o.getOpportunitystatus()-1);

    }

    private void setupEditButton(View rootView){

        ((Button)rootView.findViewById(R.id.oppoEditBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.oppo_edit_url))
                        .addParams("opportunityid", id.getText().toString())
                        .addParams("customerid", String.valueOf(o.getCustomerid()))
                        .addParams("staffid", String.valueOf(o.getStaffid()))
                        .addParams("opportunitytitle", name.getText().toString())
                        .addParams("estimatedamount", money.getText().toString())
                        .addParams("businesstype", String.valueOf(type.getSelectedItemPosition()+1))
                        .addParams("opportunitystatus", String.valueOf(status.getSelectedItemPosition()+1))
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getActivity(),
                                "修改交易信息出错",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Toast.makeText(getActivity(),
                                    ("修改"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void setupContractListViewListener(final ListView v){
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contract c=(Contract)v.getAdapter().getItem(position);
                Intent i=new Intent(getActivity(),ContractDetailActivity.class);
                i.putExtra("c",c);
                startActivity(i);
            }
        });
    }
}
