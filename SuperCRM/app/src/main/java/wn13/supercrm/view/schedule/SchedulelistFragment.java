package wn13.supercrm.view.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wn13.supercrm.R;


public class SchedulelistFragment extends Fragment {
    private static final String ARG_PARAM1 = "userID";
    private ArrayList<Map<String,String>> listItems=new ArrayList<>();

    private String userID;

    public SchedulelistFragment() {
        // Required empty public constructor
    }

    public static SchedulelistFragment newInstance(String userID) {
        SchedulelistFragment fragment = new SchedulelistFragment();
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
        listItems.clear();
        setHasOptionsMenu(true);
        View rootView=inflater.inflate(R.layout.fragment_schedulelist, container, false);
        ListView listView=(ListView)rootView.findViewById(R.id.scheduleListView);
        setupListAdapter(listView);
        setupItemListener(listView);
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void setupListAdapter(ListView v){
        for(int i=0;i<30;i++){
            Map<String,String> tmp=new HashMap<>();
            tmp.put("title","陪客户"+i);
            tmp.put("time","7月"+i+"日");

            listItems.add(tmp);
        }
        String keyList[]={"title","time"};
        int idList[]={R.id.list_item_schedulelist_title,R.id.list_item_schedulelist_time};
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),listItems,R.layout.list_item_schedulelist,keyList,idList);
        v.setAdapter(adapter);
    }

    private void setupItemListener(ListView v){
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(),ScheduleDetailActivity.class);
                i.putExtra("userID",userID);
                i.putExtra("scheduleInfo", (Serializable) listItems.get(position));
                startActivity(i);
            }
        });
    }
}
