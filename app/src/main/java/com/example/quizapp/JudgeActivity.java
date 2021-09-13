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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        //インテントからSELECT_MESSAGEを受け取る
        Intent intent = getIntent();
        String select = intent.getStringExtra(QuizActivity.SELECT_MESSAGE);
        String answer = intent.getStringExtra(QuizActivity.ANSWER_MESSAGE);
        int count = intent.getIntExtra(QuizActivity.COUNT_MESSAGE, 99);
        int size = intent.getIntExtra(QuizActivity.SIZE_MESSAGE, 99);

        TextView quizCountText = findViewById(R.id.quizCountText_judge);
        quizCountText.setText(count + " / " + size + " / " + select + " / "  + answer);

        //フレーム内を全て非表示
        FrameLayout frame = findViewById(R.id.circleORcrossLayout);
        View tmp;
        for(int i=0; i<frame.getChildCount(); i++){
            tmp = frame.getChildAt(i);
            tmp.setVisibility(View.INVISIBLE);
        }


        View paint;
        if(select.equals(answer)){
            //SELECT_MESSAGE==circleの場合、丸を表示
            paint = findViewById(R.id.circlePaint);
        }
        else{
            //SELECT_MESSAGE==crossの場合、バツを表示
            paint = findViewById(R.id.crossPaint);
        }
        paint.setVisibility(View.VISIBLE);

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