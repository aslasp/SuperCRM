package wn13.supercrm.view.trade;

import android.content.Intent;
import android.opengl.EGLExt;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Map;

import wn13.supercrm.R;

public class AddFollowActivity extends AppCompatActivity {

    Map<String,String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_follow);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加跟进记录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        info=(Map<String,String>)getIntent().getSerializableExtra("info");

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }
}
