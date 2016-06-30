package wn13.supercrm.view.contacts;


import android.content.Intent;
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
import wn13.supercrm.model.Staff;
import wn13.supercrm.utils.adapters.StaffListAdapter;

public class StaffListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_tab_list, container, false);
        final ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.contactsExpandable);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.contactSwipeLayout);
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
        OkHttpUtils.post().url((String) getResources().getText(R.string.staff_query_url))
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
                    ArrayList<Staff> staffList = new ArrayList<Staff>();
                    for (int j = 0; j < recordCount; j++) {
                        staffList.add(JsonTool.toBean(json.get(String.valueOf(j)).toString(), Staff.class));
                    }
                    listView.setAdapter(new StaffListAdapter(staffList, getActivity()));
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
                Staff staff = (Staff) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                Intent i=new Intent(getActivity(),StaffDetailActivity.class);
                i.putExtra("s",staff);
                startActivity(i);
                return false;
            }
        });
    }
}
