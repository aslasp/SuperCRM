package wn13.supercrm.utils.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import wn13.supercrm.R;
import wn13.supercrm.model.Product;

/**
 * Created by wn13 on 2016/6/22.
 */
public class ProductAdapter extends SimpleAdapter {
    private ArrayList<Product> data;
    private ArrayList<Map<String,String>> mapList;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> d,ArrayList<Map<String,String>> m, int resource) {
        super(context, m, resource, new String[]{"title"}, new int[]{R.id.productListItemTV});
        data=d;
        mapList=m;
        this.context=context;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }



}
