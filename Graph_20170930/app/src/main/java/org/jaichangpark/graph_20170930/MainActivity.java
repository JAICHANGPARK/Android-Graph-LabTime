package org.jaichangpark.graph_20170930;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PieChart mPieChart;

    Button mBtnBefore, mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnBefore = (Button) findViewById(R.id.btnBefore);
        mBtnNext = (Button) findViewById(R.id.btnNext);

        mPieChart = (PieChart)findViewById(R.id.pieChart);

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(true);
        mPieChart.setExtraOffsets(5,10,5,5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);


        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValue = new ArrayList<>();

        yValue.add(new PieEntry(34f,"PartyA"));
        yValue.add(new PieEntry(12f,"PartyB"));
        yValue.add(new PieEntry(15f,"PartyC"));
        yValue.add(new PieEntry(21f,"PartyD"));
        yValue.add(new PieEntry(8f,"PartyE"));
        yValue.add(new PieEntry(40f,"PartyF"));
        yValue.add(new PieEntry(22f,"PartyG"));

        mPieChart.animateX(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet pieDataSet = new PieDataSet(yValue, "Partys");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        mPieChart.setData(pieData);

        mBtnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(intent);
            }
        });

    }

}
