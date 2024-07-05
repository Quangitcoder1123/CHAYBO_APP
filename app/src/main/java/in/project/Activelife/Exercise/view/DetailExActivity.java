package in.project.Activelife.Exercise.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import in.project.Activelife.Exercise.RetrofitClient;
import in.project.Activelife.Exercise.model.Exercise;
import in.project.Activelife.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailExActivity extends AppCompatActivity {
    private ArrayList<Exercise> el=new ArrayList<>();
    private VideoView videoView;
    private TextView tv_name, tv_cate, tv_step,tv_detail;
    private ArrayList<Object> mStepCountList;
    private LinearLayout lnvideo;
    private Button ytb;
    private int id;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ex);
        id=this.getIntent().getIntExtra("id",-1);
        setup();
        Log.d(TAG, "onCreate: "+id);
        getData(id);

    }
    public void setup(){

        tv_name=findViewById(R.id.tv_name);
        tv_cate=findViewById(R.id.tv_catefory);
        tv_detail=findViewById(R.id.detailsDetailsTextView);
        lnvideo=findViewById(R.id.ln_video);
        ytb=findViewById(R.id.youtube);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void  getData(int id){

        Call<Exercise> call =
                RetrofitClient.getInstance().getMyApi().getbyId(id);
        call.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call,
                                   Response<Exercise> response) {
                Exercise myheroList = response.body();
                showdata(myheroList);

            }
            @Override
            public void onFailure(Call<Exercise> call,
                                  Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured",
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    private void showdata(Exercise myheroList) {
        Exercise model= myheroList;

        tv_name.setText(("Name: " + model.getExercise_name()).toString() );
        tv_cate.setText(("Category: "+model.getCategory()).toString());
        tv_detail.setText((""+model.getDetails()).toString());
        if( model.getSteps()!=null) {


            LinearLayout mSensorListView = (LinearLayout) findViewById(R.id.steps_list);
            for (int i=0;i< model.getSteps().size();i++)
            {
                TextView t=new TextView(this);
                t.setTextColor(Color.BLACK);
               t.setPadding(0,5,0,5);
               t.setTextSize(16);
                t.setText("Step "+(i+1)+" -> "+model.getSteps().get(i));
                mSensorListView.addView(t);
            }
        }
        if(model.getVideoURL()!=null)
        {
            model.getVideoURL().forEach(n->{
                LinearLayout nln =new LinearLayout(this);
                nln.setPadding(20,5,20,5);
                nln.setOrientation(LinearLayout.VERTICAL);
                VideoView a=new VideoView(this);
                nln.addView(a);
               /* MediaController mediaController = new MediaController(a.getContext());*/
                Uri uri = Uri.parse(n.toString());
                a.setVideoURI(uri);
              /*  a.setMediaController(mediaController);*/
                lnvideo.addView(nln);
                a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });
                a.start();
            });
        }
            if(model.getYoutubeURL().trim().length()>0){
                ytb.setVisibility(View.VISIBLE);
                ytb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        watchYoutubeVideo(model.getYoutubeURL().toString());
                    }
                });

            }
            else{
                ytb.setVisibility(View.GONE);

            }



        Log.d(TAG, "showdata: "+ model.toString());
        
    }
    public <ActivityNotFoundException extends Throwable> void watchYoutubeVideo(String link) throws ActivityNotFoundException {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(link));
        Log.d(TAG, "watchYoutubeVideo: "+Uri.parse(link).toString());
        startActivity(appIntent);
    }

    private class ListAdapter extends BaseAdapter {

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

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.list_rows, parent, false);
            }

            mDateStepCountText = (TextView) convertView.findViewById(R.id.sensor_name);
            mDateStepCountText.setText("Step " + (position+1) + " : " + mStepCountList.get(position));

            return convertView;
        }
    }
}