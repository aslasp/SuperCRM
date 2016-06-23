package wn13.supercrm.utils.adapters;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import wn13.supercrm.model.Staff;

/**
 * Created by wn13 on 2016/6/20.
 */
public class StaffListAdapter extends BaseExpandableListAdapter{
    ArrayList<String> groupList=new ArrayList<>();
    ArrayList<ArrayList<Staff>> staffList=new ArrayList<>();

    private Activity activity;

    public StaffListAdapter(ArrayList<Staff> list,Activity a){


        for(Staff tmp:list){
            String tmpStr=tmp.getDepartmentname();
            int index=groupList.indexOf(tmpStr);
            if(index==-1){
                groupList.add(tmpStr);
                ArrayList<Staff> newList=new ArrayList<Staff>();
                newList.add(tmp);
                staffList.add(newList);
            }else{
                staffList.get(index).add(tmp);
            }
        }

        activity=a;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return staffList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return staffList.get(groupPosition).get(childPosition);
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

        return getGenericView(groupList.get(groupPosition),16);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        return getGenericView(staffList.get(groupPosition).get(childPosition).getName(),14);
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
