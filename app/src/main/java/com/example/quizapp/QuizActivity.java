package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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


//    public void tapCircleButton(View view){
//        //judgeViewの子ビューをすべて非表示にする
//        FrameLayout frame = findViewById(R.id.judgeView);
//
//        ImageFilterView tmp;
//
//        for(int i=0; i<frame.getChildCount(); i++){
//            tmp = (ImageFilterView)frame.getChildAt(i);
//            tmp.setVisibility(View.INVISIBLE);
//        }
//
//        //マルを表示
//        ImageFilterView CircleImage = findViewById(R.id.circleImage);
//        CircleImage.setVisibility(View.VISIBLE);
//
//        TextView quizText = findViewById(R.id.quizText);
//        quizText.setText("まる");
//    }
//
//    public void tapCrossButton(View view){
//        //judgeViewの子ビューをすべて非表示にする
//        FrameLayout frame = findViewById(R.id.judgeView);
//
//        ImageFilterView tmp;
//
//        for(int i=0; i<frame.getChildCount(); i++){
//            tmp = (ImageFilterView)frame.getChildAt(i);
//            tmp.setVisibility(View.INVISIBLE);
//        }
//
//        //バツを表示
//        ImageFilterView CrossImage = findViewById(R.id.crossImage);
//        CrossImage.setVisibility(View.VISIBLE);
//
//        TextView quizText = findViewById(R.id.quizText);
//        quizText.setText("ばつ");
//    }
}