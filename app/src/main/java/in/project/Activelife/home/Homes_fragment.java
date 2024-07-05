package in.project.Activelife.home;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import in.project.Activelife.R;
import in.project.Activelife.countstep.PedometerListActivity;
import in.project.Activelife.countstep.StepsDBHelper;
import in.project.Activelife.joining_from;
import in.project.Activelife.mainpage.MainActivity;
import in.project.Activelife.models_1.User;
import in.project.Activelife.more.user_page;
import in.project.Activelife.news.newActivity;
import in.project.Activelife.sharedPreferences.UserPreferences;
import in.project.Activelife.stopwatch.stop_watch_page;


public class Homes_fragment extends Fragment implements SensorEventListener {
    Button btn_yoga1,yoga2;
    TextView stop_watch,today,km,nbStep;
    Context context;
    Integer stepnb=0;
    private float distance= (float) 0.0004;
    private StepsDBHelper mStepsDBHelper;

    private SensorManager sm;
    private Sensor sensor;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homes_fragment, container, false);
        context = view.getContext();

        sm = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);





        nbStep=view.findViewById(R.id.id_step);
        km=view.findViewById(R.id.id_km);
        getStep();
        nbStep.setText( stepnb.toString());
        double qd =stepnb.floatValue() * 0.0004;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        km.setText((df.format(qd)).toString());

        UserPreferences up=new UserPreferences(getActivity());
        User inf = up.getUser();
        if(inf.getName().length()==0)
        {
            view.findViewById(R.id.nullinf).setVisibility(View.VISIBLE);
            view.findViewById(R.id.myinf).setVisibility(View.GONE);
            view.findViewById(R.id.inputinf).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), user_page.class);
                    startActivity(intent);
                }
            });

        }
        else
        {

            view.findViewById(R.id.myinf).setVisibility(View.VISIBLE);
            view.findViewById(R.id.nullinf).setVisibility(View.GONE);
            TextView hi,age,w,h,bmi;
            ImageView avt;
            hi=view.findViewById(R.id.sayhi);
            age=view.findViewById(R.id.age);
            w=view.findViewById(R.id.weight);
            h=view.findViewById(R.id.height);
            bmi=view.findViewById(R.id.bmi);
            avt=view.findViewById(R.id.avtt);

            hi.setText("Hi "+inf.getfname());
            age.setText("Age: "+inf.getAge());
            w.setText("Weight: "+inf.getWeight());
            h.setText("Height: "+inf.getHeight());
            bmi.setText("BMI: "+inf.getBmi());

            if(inf.getSex()==0)
            {
                avt.setImageResource(R.drawable.woman);
            }
            else
            {
                avt.setImageResource(R.drawable.man);
            }

            TextView viewById = (TextView) view.findViewById(R.id.tv_bmi);
            viewById.setText("Body condition " +inf.calculateBMI());



        }

        Button news = view.findViewById(R.id.beginner_yoga1);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), newActivity.class);
                startActivity(intent);
            }
        });

        stop_watch = view.findViewById(R.id.stop_watch);
        stop_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), stop_watch_page.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.stepview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, PedometerListActivity.class);
                startActivity(i);
            }
        });



        btn_yoga1 = view.findViewById(R.id.btn_yoga1);
        btn_yoga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), joining_from.class);
                startActivity(intent);
            }
        });

        yoga2 = view.findViewById(R.id.yoga2);
        yoga2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
    public void getStep()
    {
        mStepsDBHelper = new StepsDBHelper(getActivity());
        stepnb=mStepsDBHelper.readSteptoday();
    }
    public void updateStep(){
        getStep();
        nbStep.setText( stepnb.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensor =
                    sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        updateStep();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}