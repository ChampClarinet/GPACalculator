package com.example.clarinetmaster.gpacalculator;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clarinetmaster.gpacalculator.Model.Student;

public class Result extends AppCompatActivity {

    Student student;
    TextView labelGPA;
    TextView labelStatus;
    TextView GPA;
    TextView status;
    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        labelGPA = (TextView) findViewById(R.id.labelResult);
        labelStatus = (TextView) findViewById(R.id.labelStatus);
        GPA = (TextView) findViewById(R.id.result);
        status = (TextView) findViewById(R.id.status);
        l = (LinearLayout) findViewById(R.id.linear);
        hideWidget();
        Bundle bundle = getIntent().getExtras();
        student = new Student(bundle.getString("gp"), bundle.getString("ca"));
        validCheck();
        GPA.setText(student.getGPA());
        status.setText(getResources().getString(student.getStatus()));
        status.setTextColor(getResources().getColor(student.getColor()));
    }

    private void validCheck() {
        if(student.isValidInput()) {
            showWidget();
            return;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.error);
        dialog.setTitle(R.string.errMessage);
        dialog.setNeutralButton("Back", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Result.super.onBackPressed();
                dialog.dismiss();
            }

        });
        dialog.show();
    }

    private void showWidget(){
        Log.d("show", "Widget Shown");
        l.setVisibility(View.VISIBLE);
    }

    private void hideWidget(){
        Log.d("Hide", "Widget Hidden");
        l.setVisibility(View.GONE);
    }

}
