package wn13.supercrm.view.contacts;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Customer;
import wn13.supercrm.utils.adapters.CustomerListAdapter;


public class CustomerListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customer_list, container, false);
        final ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.customerListExpandable);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.customerListSwipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setupAdapter(listView);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        setupAdapter(listView);
        setupChildListener(listView);
        return rootView;
    }

    private void setupAdapter(final ExpandableListView listView) {
        OkHttpUtils.post().url((String) getResources().getText(R.string.customer_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(),
                        "刷新通讯录出错",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条
                    ArrayList<Customer> customerList = new ArrayList<>();
                    for (int j = 0; j < recordCount; j++) {
                        customerList.add(JsonTool.toBean(json.get(String.valueOf(j)).toString(), Customer.class));
                    }
                    listView.setAdapter(new CustomerListAdapter(customerList, getActivity()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setupChildListener(ExpandableListView listView) {
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Customer customer = (Customer) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                Toast.makeText(
                        getActivity(),
                        "你点击了" + customer.getContactsname(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
