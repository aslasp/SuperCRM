package wn13.supercrm.utils;

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

/**
 * Created by wangn on 2016/5/10.
 */
public class TradeListAdapter extends SimpleAdapter {

    private ArrayList<Map<String,String>> data;
    private Context context;

    public TradeListAdapter(Context context, ArrayList<Map<String,String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data=data;
        this.context=context;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(data.get(position).get("important").equals("y")){
            View originView=super.getView(position, convertView, parent);
            ((TextView)originView.findViewById(R.id.list_item_tradelist_title)).setTextColor(ContextCompat.getColor(context,R.color.colorImportant));
            return originView;
        }
        convertView=null;
        return super.getView(position, convertView, parent);
    }
}
