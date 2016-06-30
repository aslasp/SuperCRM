package wn13.supercrm.view.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wn13.supercrm.R;
import wn13.supercrm.view.stats.GoalActivity;
import wn13.supercrm.view.stats.StatsActivity;

public class MelistFragment extends Fragment {

    private static final String ARG_PARAM1 = "userID";

    private String userID;

    public MelistFragment() {
        // Required empty public constructor
    }

    public static MelistFragment newInstance(String userID) {
        MelistFragment fragment = new MelistFragment();
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
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_melist, container, false);
        ((Button)rootView.findViewById(R.id.profileBtn)).setText(userID);

        setupGoalBtn((Button)rootView.findViewById(R.id.goalBtn));
        setupStatsBtn((Button)rootView.findViewById(R.id.statsBtn));
        setupReportBtn((Button)rootView.findViewById(R.id.reportBtn));
        return rootView;
    }

    private void setupGoalBtn(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GoalActivity.class));
            }
        });
    }

    private void setupStatsBtn(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),StatsActivity.class));
            }
        });
    }

    private void setupReportBtn(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ReportActivity.class));
            }
        });
    }

}
