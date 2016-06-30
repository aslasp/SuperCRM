package wn13.supercrm.utils.adapters;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.model.Follow;


/**
 * Created by wn13 on 2016/6/25.
 */
public class FollowAdapter extends SimpleAdapter{

    private ArrayList<Follow> data;

    public FollowAdapter(Context context, ArrayList<Follow> d, ArrayList<Map<String,String>> m, int resource) {
        super(context, m, resource, new String[]{"time"}, new int[]{R.id.productListItemTV});
        data=d;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
}
