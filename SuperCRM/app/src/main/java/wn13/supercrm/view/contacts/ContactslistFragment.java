package wn13.supercrm.view.contacts;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wn13.supercrm.R;

public class ContactslistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userID";


    // TODO: Rename and change types of parameters
    private String userID;


    public ContactslistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ContactslistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactslistFragment newInstance(String userID) {
        ContactslistFragment fragment = new ContactslistFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_contactslist, container, false);
        TextView tv=(TextView)rootView.findViewById(R.id.test_hehe);
        tv.setText(userID+" 你好，这是通讯录界面");
        return rootView;


    }

}
