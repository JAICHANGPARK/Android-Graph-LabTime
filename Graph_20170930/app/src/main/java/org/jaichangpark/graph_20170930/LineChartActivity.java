package org.jaichangpark.graph_20170930;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity implements OnChartValueSelectedListener, OnChartGestureListener{

    private static final String TAG = "LineChartActivity";
    private LineChart mLineChart;

    Button mBtnBefore, mBtnNext;

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureStart X : " + me.getX() + " Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd X : " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped " );
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling velocityX : " + velocityX + "velocity Y :" + velocityY );
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale scaleX : " + scaleX + "scaleY :" + scaleY );
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate scaleX : " + dX + "scaleY :" + dY );
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        mBtnBefore = (Button) findViewById(R.id.btnBefore);
        mBtnNext = (Button) findViewById(R.id.btnNext);

        mLineChart = (LineChart)findViewById(R.id.lineChart);

        //implements OnChartGestureListener, OnChartValueSelectedListener
        mLineChart.setOnChartGestureListener(LineChartActivity.this);
        mLineChart.setOnChartValueSelectedListener(LineChartActivity.this);

        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);


        LimitLine upperLine = new LimitLine(65f, "Danger");
        upperLine.setLineWidth(4f);
        upperLine.setLineColor(Color.RED);
        upperLine.enableDashedLine(10f,10f,5f);
        upperLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLine.setTextSize(15f);

        LimitLine lowerLine = new LimitLine(30f, "Too low");
        lowerLine.setLineWidth(4f);
        lowerLine.setLineColor(Color.BLUE);
        lowerLine.enableDashedLine(10f,10f,5f);
        lowerLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLine.setTextSize(15f);

        YAxis leftYAxis = mLineChart.getAxisLeft();
        leftYAxis.removeAllLimitLines();
        leftYAxis.addLimitLine(upperLine);
        leftYAxis.addLimitLine(lowerLine);
        leftYAxis.setAxisMaximum(100f);
        leftYAxis.setAxisMinimum(20f);
        leftYAxis.setDrawLimitLinesBehindData(true);

        mLineChart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 120f));
        yValues.add(new Entry(2, 30f));
        yValues.add(new Entry(3, 50f));
        yValues.add(new Entry(4, 66f));
        yValues.add(new Entry(5, 12f));
        yValues.add(new Entry(6, 30f));

        LineDataSet lineDataSet = new LineDataSet(yValues, "DataSet01");
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setValueTextSize(11f);

        ArrayList<ILineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);

        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);

        mBtnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LineChartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LineChartActivity.this, BarChartActivity.class);
                startActivity(intent);

            }
        });

        String[] values = new String[]{"Jan","Feb","Mar","Apr","May","Jun", "Jul"};


        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter(values));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public class XAxisValueFormatter implements IAxisValueFormatter{
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
