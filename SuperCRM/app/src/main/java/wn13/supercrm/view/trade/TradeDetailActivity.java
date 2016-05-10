package wn13.supercrm.view.trade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

import wn13.supercrm.R;

public class TradeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_detail);
        TextView tv=(TextView)findViewById(R.id.tradeDetailText);
        Intent i=getIntent();
        Map<String,String> info=(Map<String,String>)i.getSerializableExtra("tradeInfo");
        tv.setText(info.get("title"));
    }
}
