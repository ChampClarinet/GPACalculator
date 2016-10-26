package com.example.clarinetmaster.gpacalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clarinetmaster.gpacalculator.Model.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextGP;
    EditText editTextCA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGP = (EditText) findViewById(R.id.inputGP);
        editTextGP.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        onClick(v);
                        return true;
                    }
                return false;
            }
        });

        editTextCA = (EditText) findViewById(R.id.inputCA);
        editTextCA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    if((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            keyCode == KeyEvent.KEYCODE_ENTER){
                        onClick(v);
                        return true;
                    }
                return false;
            }
        });

        Button submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        editTextGP.setText("");
        editTextCA.setText("");
        super.onResume();
    }

    private boolean isNotBlank(String s) {
        return s.length() > 0;
    }

    @Override
    public void onClick(View v) {
        String gp = editTextGP.getText().toString();
        String ca = editTextCA.getText().toString();
        if(isNotBlank(gp) && isNotBlank(ca)){
            Intent i = new Intent(this, Result.class);
            i.putExtra("gp", gp);
            i.putExtra("ca", ca);
            startActivity(i);
        }else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.error);
            dialog.setMessage(R.string.errMessage);
            dialog.setNeutralButton("OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editTextGP.setText("");
                    editTextCA.setText("");
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

}
