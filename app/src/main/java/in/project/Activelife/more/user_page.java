package in.project.Activelife.more;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import in.project.Activelife.R;
import in.project.Activelife.home.Home_page;
import in.project.Activelife.models_1.User;
import in.project.Activelife.sharedPreferences.UserPreferences;
import in.project.Activelife.stopwatch.stop_watch_page;

public class user_page extends AppCompatActivity {
    private UserPreferences up;
    private User us;
    private EditText name,yb,h,w;
     private RadioButton fm, m;
     private static int sex=0;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        name=findViewById(R.id.name);
        yb=findViewById(R.id.yb);
        h=findViewById(R.id.h);
        w=findViewById(R.id.w);
        fm=findViewById(R.id.female);
        m=findViewById(R.id.male);

        CompoundButton.OnCheckedChangeListener listenerRadio
                = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    if(compoundButton.getText().equals("Male"))
                    {
                        sex=1;
                    }
                    else{
                        sex=0;
                    }

                }
            }
        };
        fm.setOnCheckedChangeListener(listenerRadio);
        m.setOnCheckedChangeListener(listenerRadio);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        up=new UserPreferences(this);
        us=up.getUser();

        if(us.getName().length()>0)
        {
            name.setText(us.getName());
           yb.setText(us.getYearb().toString());
           h.setText(Float.toString(us.getHeight()));
            w.setText(Float.toString(us.getWeight()));
            if(us.getSex()==1)
            {
                fm.setSelected(true);
            }
            else
            {
                fm.setSelected(true);
            }
            sex=us.getSex();
        }

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedataus();

            }

        });



    }
    private void savedataus()
    {
        String nameip;
        int ybip;
        Float hip,wip;
        nameip= name.getText().toString().trim();
        if(nameip.length()==0)
        {
            Toast.makeText(this,"Enter the wrong name!!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            ybip= Integer.parseInt(yb.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Enter the wrong date of birth!!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            hip= Float.parseFloat(h.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Enter the wrong height!!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            wip= Float.parseFloat(w.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Enter the wrong weight!!",Toast.LENGTH_SHORT).show();
            return;
        }
        us.setWeight(wip);
        us.setName(nameip);
        us.setYearb(ybip);
        us.setSex(sex);
        us.setHeight(hip);

        up.saveUser(us);
        Intent i=new Intent(getApplicationContext(),Home_page.class);
        startActivity(i);
    }
}