package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizActivity extends AppCompatActivity {
    //マルバツの選択を格納する
    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
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