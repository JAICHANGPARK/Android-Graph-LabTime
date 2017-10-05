package org.jaichangpark.graph_20170930;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private static final String TAG = "BarChartActivity";

    BarChart mBarChart;
    Button mBtnBefore, mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        setTitle("Bar Chart");   // activity 상단의 앱 이름을 변경한다 .

        mBtnBefore = (Button) findViewById(R.id.btnBefore);
        mBtnNext = (Button) findViewById(R.id.btnNext);

        mBarChart = (BarChart) findViewById(R.id.barChart);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.setMaxVisibleValueCount(50);
        mBarChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 20f));
        barEntries.add(new BarEntry(3, 50f));
        barEntries.add(new BarEntry(4, 10f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data Set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        mBarChart.setData(barData);


        // x축의 값을 생성하기위해 x좌표를 가져오고 설정하는 코드

        String[] values = new String[]{"Jan","Feb","Mar","Apr","May","Jun"};


        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter(values));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);


        mBtnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarChartActivity.this, LinearLineChartActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     *  x 축의 라벨을 생성해주는데 도움을 주는 포멧 클래스
     */

    public class XAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValue;

        public XAxisValueFormatter(String[] mValue) {
            this.mValue = mValue;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValue[(int)value];
        }
    }
}
