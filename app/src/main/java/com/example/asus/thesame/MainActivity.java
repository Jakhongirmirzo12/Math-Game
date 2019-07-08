 package com.example.asus.thesame;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;



public class MainActivity extends AppCompatActivity {


   Button playBtn, helpBtn, highBtn;
    private String[] levelNames = {" Easy", "Medium", "Hard"};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playBtn = (Button) findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Play.class);
                startActivity(intent1);
            }
        });
        helpBtn = (Button) findViewById(R.id.help_btn);
        helpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, HowtoPlay.class);
                startActivity(intent2);
            }
        });
        highBtn = (Button) findViewById(R.id.high_btn);
        


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a level")
                .setSingleChoiceItems(levelNames, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog ad = builder.create();
        ad.show();

    }
}



