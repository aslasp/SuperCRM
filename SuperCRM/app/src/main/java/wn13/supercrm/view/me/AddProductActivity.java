package wn13.supercrm.view.me;

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
import wn13.supercrm.model.Product;
import wn13.supercrm.presenter.AddProductPresenter;
import wn13.supercrm.presenter.IAddProductView;

public class AddProductActivity extends AppCompatActivity implements IAddProductView{

    AddProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("添加商品");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupSubmitButton();
        presenter=new AddProductPresenter(this);
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

    private void setupSubmitButton(){
        ((Button) findViewById(R.id.ProductAddBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addProduct((String) getResources().getText(R.string.product_add_url));
                onBackPressed();
            }
        });
    }

    @Override
    public Product getProductInfo() {
        Product p=new Product();
        p.setProductname(((EditText)findViewById(R.id.productAddNameET)).getText().toString());
        p.setProductsn(((EditText)findViewById(R.id.productAddSnET)).getText().toString());
        p.setStandardprice(Double.parseDouble(((EditText)findViewById(R.id.productAddPriceET)).getText().toString()));
        p.setSalesunit(((EditText)findViewById(R.id.productAddUnitET)).getText().toString());
        p.setUnitcost(Double.parseDouble(((EditText)findViewById(R.id.productAddUnitCostET)).getText().toString()));
        p.setIntroduction(((EditText)findViewById(R.id.productAddIntroductionET)).getText().toString());
        p.setProductremarks(((EditText)findViewById(R.id.productAddRemarkET)).getText().toString());
        return p;
    }

    @Override
    public void onAddError() {
        Toast.makeText(getApplicationContext(),
                "添加商品出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddResp(String info) {
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }
}
