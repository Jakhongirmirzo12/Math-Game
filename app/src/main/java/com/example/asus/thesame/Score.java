package com.example.asus.thesame;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Score implements Comparable<Score> {

    private String scoreDate;
    public int scoreNum;

    public Score(String date, int num) {
        scoreDate = date;
        scoreNum = num;
    }

    @Override
    public int compareTo(Score sc) {
        return sc.scoreNum>scoreNum? 1 : sc.scoreNum<scoreNum? -1 : 0;
    }

    public String getScoreText(){
        return scoreDate+" - "+scoreNum;
    }
}