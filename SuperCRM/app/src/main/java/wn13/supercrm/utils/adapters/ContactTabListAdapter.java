package wn13.supercrm.utils.adapters;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wangn on 2016/5/21.
 */
public class ContactTabListAdapter extends BaseExpandableListAdapter {

    private String[] groupArr;
    private String[][] childArr;

    private Activity activity;

    public ContactTabListAdapter(String[] g,String[][] c,Activity a){
        groupArr=g;
        childArr=c;
        activity=a;
    }

    @Override
    public int getGroupCount() {
        return groupArr.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArr[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArr[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArr[groupPosition][childPosition];
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

        return getGenericView(groupArr[groupPosition],16);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getGenericView(childArr[groupPosition][childPosition],14);
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
