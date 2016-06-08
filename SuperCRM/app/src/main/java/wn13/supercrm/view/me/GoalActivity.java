package wn13.supercrm.view.me;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import wn13.supercrm.R;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("业务目标");
        actionBar.setDisplayHomeAsUpEnabled(true);


        //设置图表
        setupChart();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:onBackPressed();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupChart(){
        PieChart pieChart=(PieChart) findViewById(R.id.goalPieChart);
        ArrayList<Entry> entry=new ArrayList<>();
        entry.add(new Entry((float)(60.0),0));
        entry.add(new Entry((float)(940.0),1));
        PieDataSet pieDataSet=new PieDataSet(entry,"完成率");
        ArrayList<String> xVals=new ArrayList<>();
        xVals.add("已完成");
        xVals.add("未完成");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieChart.setData(new PieData(xVals,pieDataSet));
        pieChart.setDescription("当月计划完成情况");
    }
}
