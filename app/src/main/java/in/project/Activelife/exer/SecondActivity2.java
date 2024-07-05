package in.project.Activelife.exer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import in.project.Activelife.R;

public class SecondActivity2 extends AppCompatActivity {

    int[] newArry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        TextView back6;
        back6 = findViewById(R.id.back6);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newArry = new int[]{
                R.id.boat_pose0, R.id.boat_pose1, R.id.boat_pose2, R.id.boat_pose3, R.id.boat_pose4, R.id.boat_pose5, R.id.boat_pose6, R.id.boat_pose7, R.id.boat_pose8, R.id.boat_pose9, R.id.boat_pose10, R.id.boat_pose11, R.id.boat_pose12, R.id.boat_pose13, R.id.boat_pose14
        };
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("myinfPrefs01", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("exercise",true);
        edit.apply();
    }

    public void Imagebuttonclicked(View view) {
        for (int i = 0; i < newArry.length; i++) {
            if (view.getId() == newArry[i]) {
                int value = i + 1;
                Log.i("FIRST", String.valueOf(value));
                Intent intent = new Intent(SecondActivity2.this, ThirdActivity2.class);
                intent.putExtra("value", String.valueOf(value));
                startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Do something with the result
            }
            onBackPressed();
        }
    }
}
