package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void onTapEvent(View view) {
        //finish();
        Intent intent = new Intent(this, CorrectActivity.class);
        intent.putExtra("Answer", "マル");
        startActivity(intent);
    }

    public void onTapEvent2(View view) {
        //finish();
        Intent intent = new Intent(this, CorrectActivity.class);
        intent.putExtra("Answer", "バツ");
        startActivity(intent);
    }
}