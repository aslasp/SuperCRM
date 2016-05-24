package wn13.supercrm.view.contacts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import wn13.supercrm.R;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加客户");
        actionBar.setDisplayHomeAsUpEnabled(true);


        //为下方返回按钮加监听
        setupBackBtn();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupBackBtn(){
        findViewById(R.id.addCustomerCloseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
