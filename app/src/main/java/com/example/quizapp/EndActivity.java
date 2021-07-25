package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    /**もう一度ボタンをタップされたときの挙動**/
    public void tapAgainButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //end画面に切り替え
        startActivity(intent);
    }
}