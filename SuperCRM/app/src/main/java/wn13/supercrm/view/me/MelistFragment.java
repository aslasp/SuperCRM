package wn13.supercrm.view.me;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wn13.supercrm.R;

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
        return inflater.inflate(R.layout.fragment_melist, container, false);
    }

}
