package com.example.clarinetmaster.gpacalculator.Model;

import android.util.Log;

import com.example.clarinetmaster.gpacalculator.R;

public class Student {

    private int CA;
    private double GP;
    private double GPA = -1;
    private int status;
    private int color = R.color.bad;

    public Student(String GP, String CA){
        this.GP = Double.parseDouble(GP);
        this.CA = Integer.parseInt(CA);
        calculateGPA();
        setStatus();
    }

    public boolean isValidInput(){
        Log.d("GPA", Double.toString(GPA));
        return GPA >= 0 && GPA <= 4;
    }

    public int getColor() {
        return color;
    }

    public String getGPA() {
        String result =  Double.toString(GPA);
        return result.substring(0, result.indexOf('.') + 3);
    }

    private void calculateGPA(){
        this.GPA = GP/CA;
    }

    public int getStatus(){
        return this.status;
    }

    private void setStatus(){
        if(GPA < 1.5) status = R.string.retired;
        else if(GPA < 1.8) status = R.string.lowPro;
        else if(GPA < 2) status = R.string.highPro;
        else if(GPA < 3) {
            setColor(R.color.normal);
            status = R.string.normal;
        }
        else {
            setColor(R.color.good);
            status = R.string.gifted;
        }
    }

    private void setColor(int color) {
        this.color = color;
    }

}
