package in.project.Activelife.countstep;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import in.project.Activelife.R;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;


public class PedometerListActivity extends AppCompatActivity {
    private ListView mSensorListView;

    private List<PointValue> mPointValues = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<>();
    private StepsDBHelper mStepsDBHelper;
    private ArrayList<DateStepsModel> mStepCountList;
    private ArrayList<String> dates= new ArrayList<String>();
    private ArrayList<Integer> steps= new ArrayList<Integer>();
    private ListAdapter mListAdapter;
    TextView back;

    public void getDataForList()
    {
        mStepsDBHelper = new StepsDBHelper(this);
        mStepCountList = mStepsDBHelper.readStepsEntries();
    }

    private void getDataForChart()
    {
        for(int i=mStepCountList.size()-1;i>=0;i--)
        {


                dates.add(mStepCountList.get(i).mDate);
                steps.add(mStepCountList.get(i).mStepCount);

            }

        for(int i=steps.size()-1;i>=0;i--) {
            mPointValues.add(new PointValue(i,steps.get(i)));
            mAxisXValues.add(new AxisValue(i).setLabel(dates.get(i)));
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_list);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout layout=findViewById(R.id.lnlayout);
        getDataForList();

        Comparator< DateStepsModel> ss = new Comparator<DateStepsModel>() {
            @Override
            public int compare(DateStepsModel o1, DateStepsModel o2) {
                return -(o1.mDate.compareTo(o2.mDate));
            }
        };

        mStepCountList.sort(ss);



        LineChartView chart = new LineChartView(getBaseContext());



        layout.addView(chart);
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL);

        getDataForChart();

        Line line = new Line(mPointValues).setColor(Color.parseColor("#132776"));
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(false);
        line.setFilled(false);
        line.setHasLabels(true);
//
        line.setHasLines(true);
        line.setHasPoints(true);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLACK);
        //axisX.setName("date");
        axisX.setTextSize(10);
        axisX.setMaxLabelChars(8);
        axisX.setValues(mAxisXValues);
        data.setAxisXBottom(axisX);
        //data.setAxisXTop(axisX);
        axisX.setHasLines(true);


        Axis axisY = new Axis();

        axisY.setName("");
        axisY.setTextColor(Color.BLACK);


        data.setAxisYLeft(axisY);





        chart.setLineChartData(data);




        mSensorListView = (ListView)findViewById(R.id.steps_list);
        mListAdapter = new ListAdapter();
        mSensorListView.setAdapter(mListAdapter);








    }
    private class ListAdapter extends BaseAdapter{

        private TextView mDateStepCountText;

        @Override
        public int getCount() {

            return mStepCountList.size();
        }

        @Override
        public Object getItem(int position) {

            return mStepCountList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                convertView = getLayoutInflater().inflate(R.layout.list_rows, parent, false);
            }

            mDateStepCountText = (TextView)convertView.findViewById(R.id.sensor_name);
            mDateStepCountText.setText(mStepCountList.get(position).mDate + " - Total Steps: " + String.valueOf(mStepCountList.get(position).mStepCount));

            return convertView;
        }
    }



}