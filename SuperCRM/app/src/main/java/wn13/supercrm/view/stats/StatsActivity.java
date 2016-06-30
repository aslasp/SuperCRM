package wn13.supercrm.view.stats;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import wn13.supercrm.R;

public class StatsActivity extends AppCompatActivity {

    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_stats);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("统计报表");
        actionBar.setDisplayHomeAsUpEnabled(true);

        webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSupportZoom(true);
        setContentView(webview);

        webview.loadUrl("file:///android_asset/stats.html");

        webview.addJavascriptInterface(this, "dataGiver");

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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @JavascriptInterface
    public void giveData(final int year, final int month) {



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final JSONObject map = new JSONObject();
                ArrayList<Double> oppoNumArr = new ArrayList<>();
                ArrayList<Double> oppoMoneyArr = new ArrayList<>();
                ArrayList<Double> conNumArr = new ArrayList<>();
                ArrayList<Double> conMoneyArr = new ArrayList<>();

                for(int i=0;i<30;i++){
                    int oppo=(int)(6*Math.random()*10);
                    int con=(int)(oppo*Math.random());
                    double oppoM=Math.random()*(8000);
                    double conM=Math.random()*(oppoM);
                    oppoNumArr.add((double)oppo);
                    conNumArr.add((double)con);
                    oppoMoneyArr.add(oppoM);
                    conMoneyArr.add(conM);
                }

                try {
                    map.put("oppoNumArr", new JSONArray(oppoNumArr));
                    map.put("conNumArr", new JSONArray(conNumArr));
                    map.put("oppoMoneyArr", new JSONArray(oppoMoneyArr));
                    map.put("conMoneyArr", new JSONArray(conMoneyArr));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                webview.loadUrl("javascript:drawChart('"+map.toString()+"')");
            }
        });
    }
}
