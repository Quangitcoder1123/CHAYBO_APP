package in.project.Activelife.exer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.project.Activelife.R;

public class ThirdActivity2 extends AppCompatActivity {
    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLefttinmills;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third2);

        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonvalue);

        switch(intvalue){
            case 1:
                setContentView(R.layout.activity_bow15);
                break;
            case 2:
                setContentView(R.layout.activity_bow16);
                break;
            case 3:
                setContentView(R.layout.activity_bow17);
                break;
            case 4:
                setContentView(R.layout.activity_bow18);
                break;
            case 5:
                setContentView(R.layout.activity_bow19);
                break;
            case 6:
                setContentView(R.layout.activity_bow20);
                break;
            case 7:
                setContentView(R.layout.activity_bow21);
                break;
            case 8:
                setContentView(R.layout.activity_bow22);
                break;
            case 9:
                setContentView(R.layout.activity_bow23);
                break;
            case 10:
                setContentView(R.layout.activity_bow24);
                break;
            case 11:
                setContentView(R.layout.activity_bow25);
                break;
            case 12:
                setContentView(R.layout.activity_bow26);
                break;
            case 13:
                setContentView(R.layout.activity_bow27);
                break;
            case 14:
                setContentView(R.layout.activity_bow28);
                break;
            case 15:
                setContentView(R.layout.activity_bow29);
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
                    Intent intent = new Intent(ThirdActivity2.this,ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }

                else {
                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity2.this,ThirdActivity2.class);
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
        super.onBackPressed();
    }

}