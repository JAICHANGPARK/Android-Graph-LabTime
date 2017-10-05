package org.jaichangpark.graph_20170930;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MultiLine_Activity extends AppCompatActivity {

    LineChart mLineChart;
    Button mBtnBefore, mBtnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_line_);

        setTitle("Multi Line Chart");   // activity 상단의 앱 이름을 변경한다 .
        // TODO: 10/5/17 하나의 축으로 여러개의 라인 그래프를 그리는 작업을 진행한다 .
        mLineChart = (LineChart)findViewById(R.id.lineChart);
        mBtnBefore = (Button) findViewById(R.id.btnBefore);
        mBtnNext = (Button) findViewById(R.id.btnNext);


        setData(40,60);
        mLineChart.animateX(1000); // 1초에 걸쳐 그래프를 그린다 입력되는 파라미터는 ms단위의 초이다.

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLine_Activity.this, AcceLineChartActivity.class);
                startActivity(intent);
            }
        });

        mBtnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLine_Activity.this, LinearLineChartActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 랜덤으로 데이터를 생성하여 ArrayList에 데이터를 넣는 코드이다
     * 표기할 그래프의 수만큼 ArrayList를 생성하면 된다.
     * @param count
     * @param range
     */
    private void setData(int count, int range){
        
        // TODO: 10/5/17 첫 번째 라인의 그래프를 그리기 위한 데이터 생성. 
        ArrayList<Entry> yValue01 = new ArrayList<>();
        for (int i = 0; i < count; i++){
            float val = (float) ((Math.random() * range)+250);
            yValue01.add(new Entry(i,val));
        }

        // TODO: 10/5/17 두 번째 라인의 그래프를 그리기 위한 데이터 생성.
        ArrayList<Entry> yValue02 = new ArrayList<>();
        for (int i = 0; i < count; i++){
            float val = (float) ((Math.random() * range)+150);
            yValue02.add(new Entry(i,val));
        }

        // TODO: 10/5/17 세 번째 라인의 그래프를 그리기 위한 데이터 생성.
        ArrayList<Entry> yValue03 = new ArrayList<>();
        for (int i = 0; i < count; i++){
            float val = (float) ((Math.random() * range)+50); // +값은 offset 이다.
            yValue03.add(new Entry(i,val));
        }

        // TODO: 10/5/17 LineDataSet 객체를 생성하고 이곳에 위에서 생성한 데이터를 넣는다.

        LineDataSet lineDataSet01 = new LineDataSet(yValue01, "Data Set 01");

        lineDataSet01.setColor(Color.RED);      // 선의 색상을 결정한다.
        lineDataSet01.setDrawCircles(false);    // 선 위에 표기되는 동그란 원을 제거하거나 표기한다. default는 true
        lineDataSet01.setLineWidth(3f);         // 그려지는 선의 굵기를 결정하는 메소드

        LineDataSet lineDataSet02 = new LineDataSet(yValue02, "Data Set 02");

        LineDataSet lineDataSet03 = new LineDataSet(yValue03, "Data Set 03");


        // TODO: 10/5/17 LineData 객체 인스턴스를 생성한다 넣을 파라미터는 데이터의 LineDataSet Value이다.
        LineData lineData = new LineData(lineDataSet01, lineDataSet02, lineDataSet03);


        // TODO: 10/5/17 라인테이터를 차트 객체에 SetData 메소드를 호출하여 넣는다
        mLineChart.setData(lineData);
        
    }
}
