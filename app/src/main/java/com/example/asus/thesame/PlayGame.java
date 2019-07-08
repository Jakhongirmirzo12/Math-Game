package com.example.asus.thesame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlayGame extends MainActivity implements OnClickListener {

    private int level =0, answer=0, operator=0, operand1=0, operand2=0;
    private int ADD_OPERATOR=0, SUBTRACT_OPERATOR=1, MULTIPLY_OPERATOR=2, DIVIDE_OPERATOR=3;
    private String[] operators = {"+", "-", "x", "/"};

    private int[][] levelMin = {
            {1, 11, 21},
            {1, 5, 10},
            {2, 5, 10},
            {2, 3, 5}};
    private int[][] levelMax = {
            {10, 25, 50},
            {10, 20, 30},
            {5, 10, 15},
            {10, 50, 100}};
    private Random random;
    private TextView question, answerTxt, scoreTxt;
    private ImageView response;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn, clearBtn;
//    String answerContent = null;
//    int exScore;
//    int enteredAnswer;

//    public PlayGame() {
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.question);
        answerTxt = (TextView) findViewById(answer);
        response = (ImageView) findViewById(R.id.response);
        scoreTxt = (TextView) findViewById(R.id.score);

        response.setVisibility(View.INVISIBLE);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        clearBtn = (Button) findViewById(R.id.clear);
        enterBtn = (Button) findViewById(R.id.enter);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        enterBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int passedLevel = extras.getInt("level", -1);
            if (passedLevel >= 0) level = passedLevel;
        }

        random = new Random();

        chooseQuestion();
    }

    private void chooseQuestion() {
        answerTxt.setText("=?");
        operator = random.nextInt(operators.length);
        operand1 = getOperand();

        operand2 = getOperand();


        if (operator == '-') {
            while (operand2 > operand1) {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        } else if (operator == '/') {
            while ((((double) operand1 / (double) operand2) % 1 > 0) || (operand1 == operand2)) {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }
        switch(operator)
        {
            case 0:
                answer = operand1+operand2;
                break;
            case 1:
                answer = operand1-operand2;
                break;
            case 2:
                answer = operand1*operand2;
                break;
            case 3:
                answer = operand1/operand2;
                break;
            default:
                break;
        }
        question.setText(operand1+" "+operators[operator]+" "+operand2);
    }

    private int getOperand() {
        return random.nextInt(levelMax[operator][level] - levelMin[operator][level] + 1) + levelMin[operator][level];

    }


    @Override
    public void onClick(View view) {

        String answerContent = null;
        if (view.getId() == R.id.enter) {
            answerContent = answerTxt.getText().toString();

        } else if (view.getId() == R.id.clear) {
            answerTxt.setText("=?");

        } else {
            response.setVisibility(View.INVISIBLE);
        }

        int enteredNum = Integer.parseInt(view.getTag().toString());
        int enteredAnswer = Integer.parseInt(answerContent.substring(2));
        if (!answerContent.endsWith("?")) {

        }
        if (enteredAnswer == answer) {

            scoreTxt.setText("Score:" + (exScore + 1));
            response.setImageResource(R.drawable.tick);
            response.setVisibility(View.VISIBLE);
        } else

        {
            scoreTxt.setText("Score: 0");
            response.setImageResource(R.drawable.cross);
            response.setVisibility(View.VISIBLE);
        }
        chooseQuestion();
    }

    int exScore = getScore();

    private int getScore() {
        String scoreStr = scoreTxt.getText().toString();
        return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ") + 1));
    }

}


