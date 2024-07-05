package in.project.Activelife.exer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import in.project.Activelife.R;

public class ThirdActivity extends AppCompatActivity {
    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLefttinmills;
    private InterstitialAd mInterstitialAd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        int intvalue = Integer.valueOf(buttonvalue);

        switch(intvalue){
            case 1:
                setContentView(R.layout.activity_bow0);
                break;
            case 2:
                setContentView(R.layout.activity_bow1);
                break;
            case 3:
                setContentView(R.layout.activity_bow2);
                break;
            case 4:
                setContentView(R.layout.activity_bow3);
                break;
            case 5:
                setContentView(R.layout.activity_bow4);
                break;
            case 6:
                setContentView(R.layout.activity_bow5);
                break;
            case 7:
                setContentView(R.layout.activity_bow6);
                break;
            case 8:
                setContentView(R.layout.activity_bow7);
                break;
            case 9:
                setContentView(R.layout.activity_bow8);
                break;
            case 10:
                setContentView(R.layout.activity_bow9);
                break;
            case 11:
                setContentView(R.layout.activity_bow10);
                break;
            case 12:
                setContentView(R.layout.activity_bow11);
                break;
            case 13:
                setContentView(R.layout.activity_bow12);
                break;
            case 14:
                setContentView(R.layout.activity_bow13);
                break;
            case 15:
                setContentView(R.layout.activity_bow14);
                break;


        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MTimeRunning)
                {
                    stoptimer();
                }

                else {
                    startimer();
                }
            }
        });
    }
    private  void stoptimer(){
        countDownTimer.cancel();
        MTimeRunning=false;
        startBtn.setText("START");
    }
    private void startimer(){
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final  int number = Integer.valueOf(num2) * 60+ Integer.valueOf(num3);
        MTimeLefttinmills = number*1000;

        countDownTimer = new CountDownTimer(MTimeLefttinmills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                MTimeLefttinmills = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {
                int newvalue = Integer.valueOf(buttonvalue)+1;
                if (newvalue <=7){
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }

                else {
                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }
            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning= true;

    }


    private void updateTimer(){
        int minutes= (int) MTimeLefttinmills/60000;
        int seconds = (int) MTimeLefttinmills%60000/1000;

        String timeLeftText="";
        if(minutes<10)
            timeLeftText = "0";
        timeLeftText = timeLeftText+minutes+":";
        if (seconds<10)
            timeLeftText+="0";
        timeLeftText +=seconds;
        mtextview.setText(timeLeftText);

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
        startActivity(intent);
        finish();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        else {
            Log.d("TAG","The interstitial wasn't loaded yet.");
        }

    }

}