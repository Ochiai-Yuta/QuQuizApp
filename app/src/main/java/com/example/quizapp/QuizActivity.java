package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    //マルバツの選択を格納する
    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //startTimer();

    }

    /**丸ボタンをタップされたときの挙動**/
    public void tapCircleButton(View view){
        //メッセージの初期化
        SELECT_MESSAGE = "";
        //丸の選択を送る
        Intent intent = new Intent(this, JudgeActivity.class);
        intent.putExtra(SELECT_MESSAGE, "circle");
        //judge画面に切り替え
        startActivity(intent);
    }

    /**バツボタンをタップされたときの挙動**/
    public void tapCrossButton(View view){
        //メッセージの初期化
        SELECT_MESSAGE = "";
        //丸の選択を送る
        Intent intent = new Intent(this, JudgeActivity.class);
        intent.putExtra(SELECT_MESSAGE, "cross");
        //judge画面に切り替え
        startActivity(intent);
    }

}