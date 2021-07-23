package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class JudgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String messageCircle = "circle";
        final String messageCross = "cross";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(QuizActivity.SELECT_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.example);
        textView.setText(message);

        //フレーム内を全て非表示
        FrameLayout frame = findViewById(R.id.circleORcrossLayout);
        View tmp;
        for(int i=0; i<frame.getChildCount(); i++){
            tmp = frame.getChildAt(i);
            tmp.setVisibility(View.INVISIBLE);
        }

        //丸を表示
        if(messageCircle.equals(message)){
            View paint = findViewById(R.id.circlePaint);
            paint.setVisibility(View.VISIBLE);
        }

        //バツを表示
        if(messageCross.equals(message)){
            View paint = findViewById(R.id.crossPaint);
            paint.setVisibility(View.VISIBLE);
        }
    }
}