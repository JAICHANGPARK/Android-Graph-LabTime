package org.jaichangpark.graph_20170930;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LinearLineChartActivity extends AppCompatActivity {

    private LineChart[] mLineChart = new LineChart[4];

    Button mBtnBefore, mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_line_chart);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mLineChart[0] = (LineChart)findViewById(R.id.lineChart01);
        mLineChart[1] = (LineChart)findViewById(R.id.lineChart02);
        mLineChart[2] = (LineChart)findViewById(R.id.lineChart03);
        mLineChart[3] = (LineChart)findViewById(R.id.lineChart04);

        mBtnBefore = (Button) findViewById(R.id.btnBefore);
        mBtnNext = (Button) findViewById(R.id.btnNext);

        for (int i = 0; i < mLineChart.length; i++){
            LineData data = getData(36,100); //getData 함수 제작 카운트 36, 범위 100
            setupChart(mLineChart[i], data, mColors[i]);

        }

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LinearLineChartActivity.this, MultiLine_Activity.class);
                startActivity(intent);
            }
        });

        mBtnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LinearLineChartActivity.this, BarChartActivity.class);
                startActivity(intent);
            }
        });

    }

    private int[] mColors = new int[]{
            Color.rgb(137,230,81), Color.rgb(240,230,30), Color.rgb(90,200,251), Color.rgb(250,104,119)
    };

    private void setupChart(LineChart chart, LineData data, int color){

        ((LineDataSet)data.getDataSetByIndex(0)).setCircleColorHole(color);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);
        chart.setBackgroundColor(color);

        // TODO: 10/4/17 그래프에 생성되는 아래의 데이터 legend를 삭제하고 싶다면 아래의 코드를 입력한다.
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.setViewPortOffsets(10,0,10,0);
        chart.setData(data);
    }

    private LineData getData(int count, int range){
        ArrayList<Entry> yValue = new ArrayList<>();

        for (int i = 0; i < count; i++){
            float val = (float) ((Math.random() * range)+3);
            yValue.add(new Entry(i,val));

        }

        LineDataSet lineDataSet = new LineDataSet(yValue,"data Set");
        lineDataSet.setLineWidth(3f);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setCircleHoleRadius(2.5f);
        lineDataSet.setColor(Color.WHITE);  //그리는 라인의 색상을 여기서 설정한다
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(Color.WHITE);
        lineDataSet.setDrawValues(false);

        // TODO: 그래프 라인에 표시되는 동그라미 원의 마크를 삭제한다
        lineDataSet.setDrawCircles(false);

        // TODO: 라인 그래프의 라인을 곡선으로 부드럽게 만들어준다
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f); // 이 값이 커지면 그래프 선이 울퉁불퉁하게 깨지게 그려짐 적당한 값이 들어가야겠다

        // TODO: 10/4/17 : 그래프 라인 아래에 색상을 채우기 위한다면 아래의 코드를 작성한다
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.CYAN);
        lineDataSet.setFillAlpha(80); // 적용 색생의 투명도 즉, 알파값을 이곳에서 설정한다.

        // TODO: 10/4/17 : 원하는 Drawble을 따로 제작했다면 아래의 코드를 작성해야한다

        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.gradient);
        lineDataSet.setFillDrawable(drawable);


        LineData lineData = new LineData(lineDataSet);

        return lineData;

    }
}
