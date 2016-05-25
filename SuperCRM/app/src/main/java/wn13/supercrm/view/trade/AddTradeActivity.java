package wn13.supercrm.view.trade;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import wn13.supercrm.R;

public class AddTradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加商机");
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
        findViewById(R.id.addTradeCloseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
