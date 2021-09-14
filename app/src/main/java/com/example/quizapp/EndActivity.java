package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        //問題数、正答数を取得
        Intent intent = getIntent();
        int total = intent.getIntExtra("TOTAL_NUMBER", 0);
        int correct = intent.getIntExtra("NUMBER_OF_CORRECT_ANSWER", 0);

        //結果を表示
        TextView v = findViewById(R.id.resultText);
        String numberText;
        numberText = correct + "/" + total;
        v.setText(numberText);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    /**もう一度ボタンをタップされたときの挙動**/
    public void tapAgainButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //2回目以降であることをMainActivityに知らせる
        intent.putExtra("PLURAL", true);
        //end画面に切り替え
        startActivity(intent);
    }
}