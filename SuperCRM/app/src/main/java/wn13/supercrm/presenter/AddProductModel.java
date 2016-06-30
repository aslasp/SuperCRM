package wn13.supercrm.presenter;

import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.model.Product;

/**
 * Created by wn13 on 2016/6/29.
 */
public class AddProductModel implements IAddProductModel {
    AddProductPresenter presenter;

    public AddProductModel(AddProductPresenter p){
        presenter=p;
    }

    @Override
    public void addProduct(Product p,String url) {
        OkHttpUtils.post().url(url)
                .addParams("productname",p.getProductname())
                .addParams("productsn", p.getProductsn())
                .addParams("standardprice",String.valueOf(p.getStandardprice()))
                .addParams("salesunit", p.getSalesunit())
                .addParams("unitcost",String.valueOf(p.getSalesunit()))
                .addParams("introduction", p.getIntroduction())
                .addParams("productremarks",p.getProductremarks())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                presenter.onAddError();
            }

            @Override
            public void onResponse(String s, int i) {
                try {
                    JSONObject json = new JSONObject(s);
                    String info="添加"+(String)json.get("resultdesc");
                    presenter.onAddResp(info);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
