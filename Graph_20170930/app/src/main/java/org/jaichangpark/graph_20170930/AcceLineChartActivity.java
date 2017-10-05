package org.jaichangpark.graph_20170930;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class AcceLineChartActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "AcceLineChartActivity";

    SensorManager mSensorManager;
    Sensor mSensor;

    private LineChart mLineChart;
    private boolean plotData = true;
    private Thread mThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acce_line_chart);

        setTitle("Accelo Real Time Graph");

        mLineChart = (LineChart)findViewById(R.id.accLineChart);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if (mSensor != null){
            mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);

        }

        mLineChart.getDescription().setEnabled(true);
        mLineChart.getDescription().setText("Real Time Acceleration");

        mLineChart.setTouchEnabled(false); //그래프 상의 터치를 막는다.
        mLineChart.setDragEnabled(false);
        mLineChart.setScaleEnabled(false);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setPinchZoom(false); // 그래프 상에 두 손가락으로 줌하는 것을 막음.
        mLineChart.setBackgroundColor(Color.WHITE);


        LineData lineData = new LineData();
        lineData.setValueTextColor(Color.WHITE);
        mLineChart.setData(lineData);

        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.WHITE);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setEnabled(true);

        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(10f);
        yAxis.setAxisMinimum(0);
        yAxis.setDrawGridLines(true);


        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);

        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawGridLines(false);

        mLineChart.setDrawBorders(false);

        startPlot();
    }

    private void startPlot(){
        if(mThread != null){
            mThread.interrupt();
        }
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    plotData = true;

                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        mThread.start();
    }

    private void addEntry(SensorEvent sensorEvent){


        LineData data = mLineChart.getData();


        if (data != null){
            ILineDataSet iLineDataSet = data.getDataSetByIndex(0);

            if (iLineDataSet == null){

                iLineDataSet = createSet();
                data.addDataSet(iLineDataSet);

            }
            // lineData의 addEntry 메소드를 불러옴.
            data.addEntry(new Entry(iLineDataSet.getEntryCount(), sensorEvent.values[0] + 5), 0);
            data.notifyDataChanged();

            mLineChart.notifyDataSetChanged();
            mLineChart.setVisibleXRangeMaximum(50);
            mLineChart.moveViewToX(data.getEntryCount()); // enrty데이터가 증가하면 이동시키면서 그래프를 그리게 만드는 코드.


        }
    }

    private LineDataSet createSet(){

        LineDataSet linDataSet = new LineDataSet(null, "Dynamic Data");

        linDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        linDataSet.setLineWidth(3f);
        linDataSet.setColor(Color.RED);

        linDataSet.setHighlightEnabled(false);
        linDataSet.setDrawValues(false);
        linDataSet.setDrawCircles(false);

        linDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        linDataSet.setCubicIntensity(0.1f);

        return linDataSet;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (plotData){
            addEntry(sensorEvent);
            plotData = false;

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {

        mSensorManager.unregisterListener(this);
        mThread.interrupt();
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mThread != null){
            mThread.interrupt();
        }

        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }
}
