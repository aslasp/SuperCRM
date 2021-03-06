package wn13.supercrm.utils.adapters;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wn13.supercrm.R;
import wn13.supercrm.model.Contacts;
import wn13.supercrm.model.Customer;

/**
 * Created by wangn on 2016/5/21.
 */
public class CustomerListAdapter extends BaseExpandableListAdapter {

    ArrayList<Customer> groupList=new ArrayList<>();
    ArrayList<ArrayList<Contacts>> list=new ArrayList<>();
    private Activity activity;

    public CustomerListAdapter(ArrayList<Customer> c,ArrayList<Contacts> l, Activity a){
        ArrayList<Integer> gid=new ArrayList<>();
        for(int i=0;i<c.size();i++){
            list.add(new ArrayList<Contacts>());
            gid.add(c.get(i).getCustomerid());
        }
        for(Contacts tmpC:l){
            int index=gid.indexOf(tmpC.getCustomerid());
            if(index!=-1){
                list.get(index).add(tmpC);
            }
        }
        groupList=c;
        activity=a;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View v= getGenericView(groupList.get(groupPosition).getCustomername(),16);
        v.setTag(R.id.contactsTabBtn,groupPosition);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getGenericView(list.get(groupPosition).get(childPosition).getContactsname(),14);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public View getGenericView(String string,int fontSize)
    {
        LinearLayout ll=new LinearLayout(activity);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,130));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        // Layout parameters for the ExpandableListView
        TextView text = new TextView(activity);
        text.setTextSize(fontSize);
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL|Gravity.FILL_HORIZONTAL);
        // Set the text starting position
        text.setPadding(130, 0 , 0 , 0 );
        text.setText(string);
        ll.addView(text);
        return ll;
    }
}
