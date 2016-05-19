package wn13.supercrm.view.contacts;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Map;

import wn13.supercrm.R;

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
        return rootView;
    }
}
