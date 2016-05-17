package wn13.supercrm.view.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import wn13.supercrm.R;


public class SchedulelistFragment extends Fragment {
    private static final String ARG_PARAM1 = "userID";

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
        setHasOptionsMenu(true);
        View rootView=inflater.inflate(R.layout.fragment_schedulelist, container, false);

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
