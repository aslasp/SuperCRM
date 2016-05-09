package wn13.supercrm.view.trade;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import wn13.supercrm.R;


public class TradelistFragment extends Fragment {

    private static final String ARG_PARAM1 = "userID";

    private String userID;

    public TradelistFragment() {
        // Required empty public constructor
    }

    public static TradelistFragment newInstance(String userID) {
        TradelistFragment fragment = new TradelistFragment();
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
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_tradelist, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trade_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
