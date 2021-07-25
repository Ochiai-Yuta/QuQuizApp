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

        //インテントからSELECT_MESSAGEを受け取る
        Intent intent = getIntent();
        String message = intent.getStringExtra(QuizActivity.SELECT_MESSAGE);

        //フレーム内を全て非表示
        FrameLayout frame = findViewById(R.id.circleORcrossLayout);
        View tmp;
        for(int i=0; i<frame.getChildCount(); i++){
            tmp = frame.getChildAt(i);
            tmp.setVisibility(View.INVISIBLE);
        }

        //SELECT_MESSAGE==circleの場合、丸を表示
        if(messageCircle.equals(message)){
            View paint = findViewById(R.id.circlePaint);
            paint.setVisibility(View.VISIBLE);
        }

        //SELECT_MESSAGE==crossの場合、バツを表示
        if(messageCross.equals(message)){
            View paint = findViewById(R.id.crossPaint);
            paint.setVisibility(View.VISIBLE);
        }
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    /**次へボタンをタップされたときの挙動**/
    public void tapNextButton(View view){
        Intent intent = new Intent(this, EndActivity.class);
        //end画面に切り替え
        startActivity(intent);
    }
}