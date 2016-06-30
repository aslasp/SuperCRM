package wn13.supercrm.view.contacts;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.kevin.jsontool.JsonTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Contacts;
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
        setupParentListener(listView);
        return rootView;
    }

    private void setupAdapter(final ExpandableListView listView) {
        final ArrayList<Customer> customerList=new ArrayList<>();
        final ArrayList<Contacts> contactsList = new ArrayList<>();
        OkHttpUtils.post().url((String) getResources().getText(R.string.all_customer_query_url))
                .addParams("currentpage", "0").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(getActivity(),
                        "刷新通讯录出错[客户]", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    int recordCount = (int) json.get("recordcount");//一共有几条

                    for (int j = 0; j < recordCount; j++) {
                        customerList.add(JsonTool.toBean(json.get(String.valueOf(j)).toString(), Customer.class));
                    }

                    OkHttpUtils.post().url((String) getResources().getText(R.string.customer_query_url))
                            .addParams("currentpage", "0").build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            Toast.makeText(getActivity(),
                                    "刷新通讯录出错[联系人]",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            try {
                                JSONObject json = new JSONObject(s);
                                int recordCount = (int) json.get("recordcount");//一共有几条
                                for (int j = 0; j < recordCount; j++) {
                                    contactsList.add(JsonTool.toBean(json.get(String.valueOf(j)).toString(), Contacts.class));
                                }
                                listView.setAdapter(new CustomerListAdapter(customerList,contactsList, getActivity()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });




                    //---------------------------------------
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
                Contacts contacts = (Contacts) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                Intent i=new Intent(getActivity(),ContactDetailActivity.class);
                i.putExtra("c",contacts);
                startActivity(i);
                return false;
            }
        });
    }

    private void setupParentListener(final ExpandableListView listView){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Customer customer=(Customer)listView.getExpandableListAdapter().getGroup((int)view.getTag(R.id.contactsTabBtn));
                Intent i=new Intent(getActivity(),CustomerDetailActivity.class);
                i.putExtra("customer",customer);
                startActivity(i);
                return false;
            }
        });
    }

}
