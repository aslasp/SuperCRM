package wn13.supercrm.view.contacts;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wn13.supercrm.R;

public class ContactslistFragment extends Fragment {
    private static final String ARG_PARAM1 = "userID";

    private String userID;

    private ViewPager viewPager;

    public ContactslistFragment() {
        // Required empty public constructor
    }

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
        viewPager=(ViewPager)rootView.findViewById(R.id.contactsViewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout=(TabLayout) rootView.findViewById(R.id.contactsTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("客户通讯录"));
        tabLayout.addTab(tabLayout.newTab().setText("公司通讯录"));
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager){
        MyPagerAdapter adapter=new MyPagerAdapter(getFragmentManager());
        adapter.addFragment(ContactTabListFragment.newInstance(0),"客户通讯录");
        adapter.addFragment(ContactTabListFragment.newInstance(1),"公司通讯录");
        viewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
