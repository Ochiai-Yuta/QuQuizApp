package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

//xmlを使わないでQuizActivityと同じものを作ってみる。
public class CorrectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent toGetIntent = getIntent();
        String val = toGetIntent.getStringExtra("Answer");

        Toast myToast = Toast.makeText(
                getApplicationContext(),
                "あなたは"+val+"を押しました",
                Toast.LENGTH_SHORT
        );
        myToast.show();

        // リニアレイアウトのインスタンス生成
        LinearLayout layout = new LinearLayout(this);

        //垂直方向にレイアウト内のパーツを並べる設定とする
        layout.setOrientation(LinearLayout.VERTICAL);

        //レイアウトの縦横サイズをMATCH_PARENTにする
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        // レイアウト中央寄せ
        layout.setGravity(Gravity.CENTER);

        // 背景色
        layout.setBackgroundColor(Color.argb(0xff, 0xaa, 0xcc, 0xff));

        //setContentViewにlayoutを設定
        setContentView(layout);

        // ボタンの設定
        Button button = new Button(this);
        String str = "戻る";
        button.setText(str);

        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(buttonLayoutParams);
        layout.addView(button);

        button.setOnClickListener( v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        });
    }

}