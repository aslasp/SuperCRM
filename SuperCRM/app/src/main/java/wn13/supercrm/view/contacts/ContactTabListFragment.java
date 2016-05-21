package wn13.supercrm.view.contacts;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.utils.adapters.ContactTabListAdapter;

public class ContactTabListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "type";

    private int type;

    public ContactTabListFragment() {
        // Required empty public constructor
    }

    public static ContactTabListFragment newInstance(int param1) {
        ContactTabListFragment fragment = new ContactTabListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_contact_tab_list, container, false);
        ExpandableListView listView=(ExpandableListView)rootView.findViewById(R.id.contactsExpandable);
        setupAdapter(listView);
        setupChildListener(listView);
        return rootView;
    }

    private void setupAdapter(ExpandableListView listView){
        String[] gArr={"南京大学","浙江大学","金陵饭店","先锋书店"};
        String[][] childArr={{"陈骏","高圆圆","萧亚轩"},{"Taylor Swift","陈光标"},{"谢霆锋","张柏芝","陈冠希","王菲"},{"董明珠","雷军","许巍"}};
        listView.setAdapter(new ContactTabListAdapter(gArr,childArr,getActivity()));
    }

    private void setupChildListener(ExpandableListView listView){
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        "你点击了",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
