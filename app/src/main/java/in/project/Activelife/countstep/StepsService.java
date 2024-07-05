package in.project.Activelife.countstep;

import static android.content.ContentValues.TAG;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import in.project.Activelife.R;
import in.project.Activelife.home.Home_page;
import in.project.Activelife.mainpage.MainActivity;


public class StepsService extends Service implements SensorEventListener  {

    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private int stepcount=0;
    private int step=0;
    private StepsDBHelper mStepsDBHelper;
    private SharedPreferences sharedPreferences;
    private static final String CHANNEL_ID = "my_channel_id";
    private static final int NOTIFICATION_ID = 1;



    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = this.getApplicationContext().getSharedPreferences("myinfPrefs01", Context.MODE_PRIVATE);


        mSensorManager = (SensorManager)
                this.getSystemService(Context.SENSOR_SERVICE);


      /*  if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mStepDetectorSensor =
                    mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
            mStepsDBHelper = new StepsDBHelper(this);
            step=mStepsDBHelper.readSteptoday();
        }
        else {*/
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepDetectorSensor =
                    mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
            mStepsDBHelper = new StepsDBHelper(this);
            step = mStepsDBHelper.readSteptoday();

        } else {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_LONG).show();
        }
        createNotificationChannel();

        sendnotic();
        /* }*/






    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
    }
    @Override
    public void onDestroy() {


    }

    public boolean checktoday(){
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat fomat=new SimpleDateFormat("MM/dd/yyyy");
        String todayDate =fomat.format(mCalendar);

        if(sharedPreferences.getString("today","a").equals(todayDate)){
            boolean rt=sharedPreferences.getBoolean("exercise",false);

            return rt;
        }else
        {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("today", todayDate);
            edit.putBoolean("exercise",false);
            edit.apply();
            return false;
        }

      /*  SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("STEPCOUNTER", stepcount);
        edit.apply();*/
    }
    public void sendnotic(){
        if (checktoday()==false){
            showNotification("Hi, you haven't exercised today.", " Exercise with me now!");

        }
    }

    public int getStep() {
        return step;
    }

    public void setStepcount(int stepcount) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("STEPCOUNTER", stepcount);
        edit.apply();
    }

    public int getStepcount() {
        this.stepcount= sharedPreferences.getInt("STEPCOUNTER",-1);
        return this.stepcount;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int stepcountnow = (int) event.values[0];
        int stepcount=getStepcount();

        if (stepcount == -1)
        {
            setStepcount(stepcountnow);
        }
        if(stepcountnow==0)
        {
            setStepcount(0);
        }
        stepcount=getStepcount();
        mStepsDBHelper.createStepsEntry(stepcountnow-stepcount);
        setStepcount(stepcountnow);


        // lấy ra hiện tại trừ trước đó -> lưu( stepcount - stepcountsave =stepchange)
        // rest may SensorEvent.values[0]=0 stepcountsave >0  ( stepcount - stepcountsave =stepchange<0)-> stepsave=0;
        /*  lúc cài đặt thì nếu+thì nó sẻ lấy stepcount lúc onCreate
         hoạt động stepcount - stepcountsave =stepchange
           lưu thêm vào csdl + set stepcountsave=stepcount*/

       /* mStepsDBHelper.createStepsEntry();*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String message) {
        // Tạo intent cho khi người dùng chạm vào thông báo
        Intent intent = new Intent(this, Home_page.class);
        intent.putExtra("vi",1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Tạo thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // Đặt intent khi người dùng chạm vào thông báo
                .setFullScreenIntent(pendingIntent, true) // Hiển thị thông báo rõ ràng trên đầu ứng dụng
                .setAutoCancel(true); // Tự động huỷ thông báo khi người dùng chạm vào nó

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


}