package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    //マルバツの選択を格納する
    private int COUNT = 0;

    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";
    public static ArrayList<MainActivity.QuizAtrr> QUIZ_LIST = new ArrayList<>();

    private DialogFragment dialogFragment;
    private FragmentManager flagmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //MainActivityから受け取ったintentをgetする。
        Intent intent = getIntent();
        //intentに付いてきた、”QUIZ_LIST”のKeyを持つ要素をQUIZ_LISTに格納する。
        QUIZ_LIST = (ArrayList<MainActivity.QuizAtrr>) intent.getSerializableExtra("QUIZ_LIST");

        printQuiz(QUIZ_LIST, COUNT);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    /*クイズを表示*/
    public void printQuiz(ArrayList<MainActivity.QuizAtrr> list, int n){
        TextView quiz = findViewById(R.id.quizText);
        quiz.setText(list.get(n).quiz_text);
    }

    /**丸ボタンをタップされたときの挙動**/
    public void tapCircleButton(View view){
        //正誤判定
        if("circle".equals(QUIZ_LIST.get(COUNT).answer_text)) {
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new CorrectDialogFragment(++COUNT);
            dialogFragment.show(flagmentManager, "test alert dialog");
        }
        else{
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new IncorrectDialogFragment(++COUNT);
            dialogFragment.show(flagmentManager, "incorrect dialog");
        }
    }

    /**バツボタンをタップされたときの挙動**/
    public void tapCrossButton(View view){
        //正誤判定
        if("circle".equals(QUIZ_LIST.get(COUNT).answer_text)) {
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new CorrectDialogFragment(++COUNT);
            dialogFragment.show(flagmentManager, "test alert dialog");
        }
        else{
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new IncorrectDialogFragment(++COUNT);
            dialogFragment.show(flagmentManager, "incorrect dialog");
        }
    }

    /**次へボタンをタップされたときの挙動**/
    public void tapNextQuizButton(View view){
        LinearLayout judge = findViewById(R.id.judgeViewLayout);
        for(int i=0; i<judge.getChildCount(); i++){
            judge.getChildAt(i).setVisibility(View.INVISIBLE);
        }
        judge.setVisibility(View.INVISIBLE);
        printQuiz(QUIZ_LIST, ++COUNT);
    }

    public static class CorrectDialogFragment extends DialogFragment{
        static int count;
        AlertDialog dialog;
        AlertDialog.Builder alert;
        View alertView;

        CorrectDialogFragment(int count){
            this.count = count;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState){
            alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("正解！");

            if(getActivity() != null){
                alertView = getActivity().getLayoutInflater().inflate(R.layout.alert_layout, null);
            }

            FrameLayout frame = alertView.findViewById(R.id.circleORcrossLayout);
            for(int i=0; i<frame.getChildCount(); i++){
                frame.getChildAt(i).setVisibility(View.INVISIBLE);
            }

            View circle = frame.findViewById(R.id.circlePaint);
            circle.setVisibility(View.VISIBLE);

            Button next = alertView.findViewById(R.id.nextButton);
            next.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("debug", "bag1 clicked");
                    printNextQuiz(count);
                    getDialog().dismiss();
                }
            });

            alert.setView(alertView);

            dialog = alert.create();
            dialog.show();

            return dialog;
        }

        private void printNextQuiz(int count) {
            QuizActivity Activity = (QuizActivity) getActivity();
            if(Activity != null) {
                Activity.printQuiz(QUIZ_LIST, count);
            }
        }
    }

    public static class IncorrectDialogFragment extends DialogFragment{
        static int count;
        AlertDialog dialog;
        AlertDialog.Builder alert;
        View alertView;

        IncorrectDialogFragment(int count){
            this.count = count;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState){
            alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("不正解");

            if(getActivity() != null){
                alertView = getActivity().getLayoutInflater().inflate(R.layout.alert_layout, null);
            }

            FrameLayout frame = alertView.findViewById(R.id.circleORcrossLayout);
            for(int i=0; i<frame.getChildCount(); i++){
                frame.getChildAt(i).setVisibility(View.INVISIBLE);
            }

            View cross = frame.findViewById(R.id.crossPaint);
            cross.setVisibility(View.VISIBLE);

            Button next = alertView.findViewById(R.id.nextButton);
            next.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("debug", "bag1 clicked");
                    printNextQuiz(count);
                    getDialog().dismiss();
                }
            });

            alert.setView(alertView);

            dialog = alert.create();
            dialog.show();

            return dialog;
        }

        private void printNextQuiz(int count) {
            QuizActivity Activity = (QuizActivity) getActivity();
            if(Activity != null) {
                Activity.printQuiz(QUIZ_LIST, count);
            }
        }
    }

}