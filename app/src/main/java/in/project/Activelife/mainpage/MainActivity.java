package in.project.Activelife.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import in.project.Activelife.countstep.StepsService;
import in.project.Activelife.slider.slider;
import in.project.Activelife.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =1000;
    Animation up,down;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mStepsIntent = new Intent(getApplicationContext(), StepsService.class);
        startService(mStepsIntent);

        ImageView imageView = findViewById(R.id.appsplash);
        up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        imageView.setAnimation(up);


        TextView textView = findViewById(R.id.appname);
        down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        textView.setAnimation(down);


        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Intent homeIntent = new Intent(MainActivity.this, slider.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }


}