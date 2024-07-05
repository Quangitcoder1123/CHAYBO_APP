package in.project.Activelife.models_1;

import android.icu.util.Calendar;

public class User {
    private String name="";
    private Integer yearb=0;
    private Integer age=0;
    private int sex=0;
    private float height=0;
    private float weight=0;
    public User(String name,Integer yearb,int sex, float height,float weight){
        this.height=height;
        this.sex=sex;
        this.weight=weight;
        this.yearb=yearb;
        this.name=name;
    }
    public User(){}

    public String getName() {
        return name;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public int getSex() {
        return sex;
    }

    public Integer getAge() {
        Calendar mCalendar = Calendar.getInstance();
        age=mCalendar.get(Calendar.YEAR)-yearb;
        return  age;
    }

    public Integer getYearb() {
        return yearb;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setYearb(Integer yearb) {
        this.yearb = yearb;
    }
    public String getBmi(){
        Float bmi;
        bmi= weight/(height*height/10000);
        return bmi.toString();
    }
    public String getfname(){
        String b=this.name.substring(name.lastIndexOf(" ")+1);
        return b;
    }
    public String calculateBMI() {

        double bmi = weight / (height * height/10000);

        if (bmi < 18.5) {
            return "Underweight";
        }
        else if (bmi < 25) {
            return "Normal";
        }
        else if (bmi < 30) {
            return "Overweight";
        }
        else {
            return "Obese";
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", yearb=" + yearb +
                ", age=" + age +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}

