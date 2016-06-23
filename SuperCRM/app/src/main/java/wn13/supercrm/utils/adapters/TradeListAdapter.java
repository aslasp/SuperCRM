package wn13.supercrm.utils.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.model.Opportunity;

/**
 * Created by wangn on 2016/5/10.
 */
public class TradeListAdapter extends SimpleAdapter {
    private ArrayList<Opportunity> data;
    private ArrayList<Map<String,String>> mapList;
    private Context context;

    public TradeListAdapter(Context context, ArrayList<Opportunity> d,ArrayList<Map<String,String>> m, int resource) {
        super(context, m, resource, new String[]{"title","step","customer"}, new int[]{R.id.list_item_tradelist_title,R.id.list_item_tradelist_step,R.id.list_item_tradelist_customer});
        data=d;
        mapList=m;
        this.context=context;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(mapList.get(position).get("important").equals("y")){
            View originView=super.getView(position, convertView, parent);
            ((TextView)originView.findViewById(R.id.list_item_tradelist_title)).setTextColor(ContextCompat.getColor(context,R.color.colorImportant));
            return originView;
        }
        convertView=null;
        return super.getView(position, convertView, parent);
    }
}
