package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    //マルバツの選択を格納する
    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";
    public static String COUNT_MESSAGE = "com.example.quizapp.MESSAGE";
    public static String ANSWER_MESSAGE = "com.example.quizapp.MESSAGE";
    public static String SIZE_MESSAGE = "com.example.quizapp.MESSAGE";
    private static ArrayList<MainActivity.QuizAtrr> QUIZ_LIST;
    private static String ANSWER;
    private static int SIZE;
    private static int COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //MainActivityから受け取ったintentをgetする。
        Intent intent = getIntent();
        //intentに付いてきた、”QUIZ_LIST”のKeyを持つ要素をQUIZ_LISTに格納する。
        try{
            //QuizAtrr型のArrayListを受け取る
            QUIZ_LIST = (ArrayList<MainActivity.QuizAtrr>) intent.getSerializableExtra("QUIZ_LIST");
        }catch(Exception e){
            e.printStackTrace();
            finish();
            System.exit(-1);
        }

        setCount(0);
        setSize(QUIZ_LIST.size());
        setAnswer(QUIZ_LIST.get(0).answer_text);
        createScreen(COUNT+1, SIZE, QUIZ_LIST.get(COUNT).quiz_text);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    private void setCount(int count){
        COUNT = count;
    }

    private void setSize(int size){
        SIZE = size;
    }

    private void setAnswer(String answer){
        ANSWER = answer;
    }

    /**クイズテキストを画面に表示**/
    private void createScreen(int count, int size, String quiz){
        TextView quizCountText = findViewById(R.id.quizCountText_quiz);
        quizCountText.setText(count + " / " + size);

        TextView quizText = findViewById(R.id.quizText);
        quizText.setText(quiz);
    }

    /**丸ボタンをタップされたときの挙動**/
    public void tapCircleButton(View view){
        //メッセージの初期化
        SELECT_MESSAGE = "";
        //丸の選択を送る
        Intent intent = new Intent(this, JudgeActivity.class);
        intent.putExtra(SELECT_MESSAGE, "circle");
        intent.putExtra(ANSWER_MESSAGE, ANSWER);
        intent.putExtra(COUNT_MESSAGE, 0);
        intent.putExtra(SIZE_MESSAGE, SIZE);
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
        intent.putExtra(ANSWER_MESSAGE, ANSWER);
        intent.putExtra(COUNT_MESSAGE, COUNT);
        intent.putExtra(SIZE_MESSAGE, SIZE);
        //judge画面に切り替え
        startActivity(intent);
    }

}