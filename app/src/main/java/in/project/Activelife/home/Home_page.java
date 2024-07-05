package in.project.Activelife.home;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.cocosw.bottomsheet.BottomSheet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import in.project.Activelife.R;
import in.project.Activelife.countstep.StepsService;
import in.project.Activelife.joining_from;


public class Home_page extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Home_page";

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }


        if (!isconnected()) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connection Alert")
                .setMessage("please check you internet connection")
                .setPositiveButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .show();
    } else {

    }

        if (this.getIntent().getIntExtra("vi",0)==1){
            showWorkOut();
        }

}

    private boolean isconnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



    public void openAndroidBottomMenu(View view) {

        new BottomSheet.Builder(this).title("Share").sheet(R.menu.android_bottom_menu_item).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {

                    case R.id.menu_call:
                        Intent intent = new Intent(getApplicationContext(), joining_from.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_share:

                        Intent shareintent = new Intent();
                        shareintent.setAction(Intent.ACTION_SEND);
                        shareintent.putExtra(Intent.EXTRA_TEXT, "https://nguoidungw5.wixsite.com/active-life");
                        shareintent.setType("text/plain");
                        startActivity(Intent.createChooser(shareintent, " share Application"));


                        break;
                }
            }
        }).show();

    }





    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logo)
                .setTitle(R.string.app_name)
                .setMessage("Are you sure you want to Exit the Application")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }






    Homes_fragment Homes_fragment = new Homes_fragment();
    mealFragment mealFragment = new mealFragment();
    workoutFragment workoutFragment = new workoutFragment();
    moreFragment moreFragment = new moreFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, Homes_fragment).commit();
                return true;

            case R.id.navigation_Meal:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, mealFragment).commit();
                return true;

            case R.id.navigation_Workout:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, workoutFragment).commit();
                return true;

            case R.id.navigation_More:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, moreFragment).commit();
                return true;



        }

        return false;
    }
    public void showWorkOut(){
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, workoutFragment).commit();

    }
}
