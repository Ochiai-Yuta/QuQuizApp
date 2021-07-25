package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activity_main.xmlを描画する。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //スタート画面のラベルに文字を入れる
        TextView label = this.findViewById(R.id.startLabel);
        label.setText(R.string.text);
    }

    public void tapStartButton(View view){
        //画面の切り替え
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}