package in.project.Activelife.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import in.project.Activelife.models_1.User;

public class UserPreferences {
    private Context context;
    private SharedPreferences sharedPreferences;
    public UserPreferences(Context context){
        this.context=context;
        sharedPreferences=this.context.getSharedPreferences("myinfPrefs01",Context.MODE_PRIVATE);

    }
    public void saveUser(User us)
    {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("name", us.getName());
        edit.putInt("yearb", us.getYearb());
        edit.putInt("sex", us.getSex());
        edit.putFloat("height", us.getHeight());
        edit.putFloat("weight",us.getWeight());
        edit.apply();
    }

    public User getUser()
    {
        User us=new User();
        us.setName(sharedPreferences.getString("name", ""));
        us.setYearb(sharedPreferences.getInt("yearb",0));
        us.setSex(sharedPreferences.getInt("sex", 0));
        us.setHeight(sharedPreferences.getFloat("height", 0f));
        us.setWeight(sharedPreferences.getFloat("weight",0f));
        return us;
    }



}
