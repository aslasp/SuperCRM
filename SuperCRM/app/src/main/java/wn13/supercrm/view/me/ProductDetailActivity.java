package wn13.supercrm.view.me;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import wn13.supercrm.R;
import wn13.supercrm.databinding.ActivityProductDetailBinding;
import wn13.supercrm.model.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private Product product;

    private EditText idET, nameET, snET, priceET, unitET, costET, introET, remarkET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityProductDetailBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_product_detail);

        product = (Product) getIntent().getSerializableExtra("product");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(product.getProductname());
        actionBar.setDisplayHomeAsUpEnabled(true);

        binding.setProduct(product);

        idET=(EditText) findViewById(R.id.productDetailIdET);
        nameET=(EditText) findViewById(R.id.productDetailNameET);
        snET=(EditText) findViewById(R.id.productDetailSnET);
        priceET=(EditText) findViewById(R.id.productDetailPriceET);
        unitET=(EditText) findViewById(R.id.productDetailUnitET);
        costET=(EditText) findViewById(R.id.productDetailUnitCostET);
        introET=(EditText) findViewById(R.id.productDetailIntroductionET);
        remarkET=(EditText) findViewById(R.id.productDetailRemarkET);
        setupEditButton();
        setupDelButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupEditButton() {
        ((Button) findViewById(R.id.editProductBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url((String) getResources().getText(R.string.product_edit_url))
                        .addParams("productid", idET.getText().toString())
                        .addParams("productname", nameET.getText().toString())
                        .addParams("productsn", snET.getText().toString())
                        .addParams("standardprice", priceET.getText().toString())
                        .addParams("salesunit", unitET.getText().toString())
                        .addParams("unitcost", costET.getText().toString())
                        .addParams("introduction", introET.getText().toString())
                        .addParams("productremarks", remarkET.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getApplicationContext(),
                                "修改商品信息出错",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject json = new JSONObject(s);
                            Toast.makeText(getApplicationContext(),
                                    ("修改"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void setupDelButton() {
        ((Button) findViewById(R.id.delProductBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url((String) getResources().getText(R.string.product_del_url))
                        .addParams("productid", String.valueOf(product.getProductid())).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),
                                "删除商品出错",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int id) {
                        try {
                            JSONObject json = new JSONObject(s);

                            Toast.makeText(getApplicationContext(),
                                    ("删除"+(String)json.get("resultdesc")), Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                onBackPressed();
            }
        });
    }
}
