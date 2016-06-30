package wn13.supercrm.utils.adapters;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.model.Contract;

/**
 * Created by wn13 on 2016/6/25.
 */
public class ContractAdapter extends SimpleAdapter {

    private ArrayList<Contract> data;

    public ContractAdapter(Context context, ArrayList<Contract> d,ArrayList<Map<String,String>> m, int resource) {
        super(context, m, resource, new String[]{"title"}, new int[]{R.id.productListItemTV});
        data=d;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

}
