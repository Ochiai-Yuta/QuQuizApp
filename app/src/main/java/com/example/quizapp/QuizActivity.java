package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {
    //問題番号
    private int COUNT = 0;
    //総問題数
    private static int TOTAL_NUMBER = 0;
    //正答数
    private int NUMBER_OF_CORRECT_ANSWER = 0;
    //問題を読み込む
    public static String SELECT_MESSAGE = "com.example.quizapp.MESSAGE";
    public static ArrayList<MainActivity.QuizAtrr> QUIZ_LIST = new ArrayList<>();
    //ダイアログ
    private DialogFragment dialogFragment;
    private FragmentManager flagmentManager;

    //タイマー
    private TextView timerText;
    private final SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss.S", Locale.US);
    private int TIME_COUNT, period;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            TIME_COUNT++;
            timerText.setText(dataFormat.
                    format(TIME_COUNT *period));
            handler.postDelayed(this, period);
        }
    };

    @Override
    //「QUIZ_LISTの型が未チェック」の警告を無視
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //MainActivityから受け取ったintentをgetする。
        Intent intent = getIntent();
        //intentに付いてきた、”QUIZ_LIST”のKeyを持つ要素をQUIZ_LISTに格納する。
        QUIZ_LIST = (ArrayList<MainActivity.QuizAtrr>) intent.getSerializableExtra("QUIZ_LIST");
        TOTAL_NUMBER = QUIZ_LIST.size();

        printQuiz(QUIZ_LIST, COUNT);

        //タイマー
        TIME_COUNT = 0;
        period = 100;
        timerText = findViewById(R.id.timerText_quiz);
        timerText.setText(dataFormat.format(0));
        //タイマーの開始
        handler.post(runnable);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    /*クイズを表示*/
    public void printQuiz(ArrayList<MainActivity.QuizAtrr> list, int n){
        String numberText;
        TextView quiz = findViewById(R.id.quizText);
        quiz.setText(list.get(n).quiz_text);

        //問題番号を更新
        numberText = n+1 + "/" + QUIZ_LIST.size();
        TextView text = findViewById(R.id.quizCountText_quiz);
        text.setText(numberText);
    }

    /**丸ボタンをタップされたときの挙動**/
    public void tapCircleButton(View view){
        //タイマーの一時停止
        handler.removeCallbacks(runnable);
        //正誤判定
        if("circle".equals(QUIZ_LIST.get(COUNT).answer_text)) {
            //正解
            NUMBER_OF_CORRECT_ANSWER++;
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new CorrectDialogFragment(++COUNT, endCheck(COUNT, TOTAL_NUMBER), handler, runnable);
            dialogFragment.show(flagmentManager, "correct dialog");
        }
        else{
            //不正解
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new IncorrectDialogFragment(++COUNT, endCheck(COUNT, TOTAL_NUMBER), handler, runnable);
            dialogFragment.show(flagmentManager, "incorrect dialog");
        }
    }

    /**バツボタンをタップされたときの挙動**/
    public void tapCrossButton(View view){
        //タイマーの一時停止
        handler.removeCallbacks(runnable);
        //正誤判定
        if("cross".equals(QUIZ_LIST.get(COUNT).answer_text)) {
            //正解
            NUMBER_OF_CORRECT_ANSWER++;
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new CorrectDialogFragment(++COUNT, endCheck(COUNT, TOTAL_NUMBER), handler, runnable);
            dialogFragment.show(flagmentManager, "correct dialog");
        }
        else{
            //不正解
            //AlertDialogの表示
            flagmentManager = getSupportFragmentManager();
            dialogFragment = new IncorrectDialogFragment(++COUNT, endCheck(COUNT, TOTAL_NUMBER), handler, runnable);
            dialogFragment.show(flagmentManager, "incorrect dialog");
        }
    }

    //クイズの終了を判定(クイズが終了ならばfalseを返す)
    private boolean endCheck(int count, int total) {
        return count < total;
    }

    //EndActivityに切り替える
    private void toEndActivity(){
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("TOTAL_NUMBER", TOTAL_NUMBER);
        intent.putExtra("NUMBER_OF_CORRECT_ANSWER", NUMBER_OF_CORRECT_ANSWER);
        intent.putExtra("TIME_COUNT", TIME_COUNT);
        //end画面に切り替え
        startActivity(intent);
    }

    //正解したときに表示するAlertDialog
    public static class CorrectDialogFragment extends DialogFragment{
        AlertDialog dialog;
        AlertDialog.Builder alert;
        private View alertView;
        private final int count;      //次の問題番号
        private final boolean endFlag;    //クイズの終了を判定
        private final Handler handler;  //タイマーのハンドラ
        private final Runnable runnable;    //タイマー用の別スレッド

        //コンストラクタ
        CorrectDialogFragment(int count, boolean endFlag, Handler handler, Runnable runnable){
            this.count = count;
            this.endFlag = endFlag;
            this.handler = handler;
            this.runnable = runnable;
        }

        @Override
        @NonNull
        //「AlertDialogの親Viewが存在しない」警告を無視
        @SuppressLint("InflateParams")
        public Dialog onCreateDialog(Bundle savedInstanceState){
            alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("正解！");

            //レイアウトの取得
            if(getActivity() != null){
                alertView = getActivity().getLayoutInflater().inflate(R.layout.alert_layout, null);
            }

            //レイアウト内のマルバツ表示を非表示にする
            FrameLayout frame = alertView.findViewById(R.id.circleORcrossLayout);
            for(int i=0; i<frame.getChildCount(); i++){
                frame.getChildAt(i).setVisibility(View.INVISIBLE);
            }

            //マルを表示
            View circle = frame.findViewById(R.id.circlePaint);
            circle.setVisibility(View.VISIBLE);

            //次へボタンをタップされたときの挙動
            Button next = alertView.findViewById(R.id.nextButton);
            //クイズが続く場合
            if(endFlag) {
                next.setOnClickListener(v -> {
                    //タイマーの再開
                    handler.post(runnable);
                    printNextQuiz(count);
                    Objects.requireNonNull(getDialog()).dismiss();
                });
            }
            //クイズが終了する場合
            else{
                next.setText("結果発表");
                next.setOnClickListener(v -> {
                    QuizActivity Activity = (QuizActivity) getActivity();
                    Objects.requireNonNull(getDialog()).dismiss();
                    //EndActivityへ切り替える
                    Activity.toEndActivity();
                });
            }

            alert.setView(alertView);
            dialog = alert.create();
            dialog.show();

            return dialog;
        }

        //printQuizを呼び出す
        private void printNextQuiz(int count) {
            QuizActivity Activity = (QuizActivity) getActivity();
            if(Activity != null) {
                Activity.printQuiz(QUIZ_LIST, count);
            }
        }
    }

    //不正解したときに表示するAlertDialog
    public static class IncorrectDialogFragment extends DialogFragment{
        AlertDialog dialog;
        AlertDialog.Builder alert;
        View alertView;
        private final int count;      //次の問題番号
        private final boolean endFlag;    //クイズの終了を判定
        private final Handler handler;  //タイマーのハンドラ
        private final Runnable runnable;    //タイマー用の別スレッド

        //コンストラクタ
        IncorrectDialogFragment(int count, boolean endFlag, Handler handler, Runnable runnable){
            this.count = count;
            this.endFlag = endFlag;
            this.handler = handler;
            this.runnable = runnable;
        }

        @Override
        @NonNull
        //「AlertDialogの親Viewが存在しない」警告を無視
        @SuppressLint("InflateParams")
        public Dialog onCreateDialog(Bundle savedInstanceState){
            alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("不正解");

            //レイアウトの取得
            if(getActivity() != null){
                alertView = getActivity().getLayoutInflater().inflate(R.layout.alert_layout, null);
            }

            //レイアウト内のマルバツ表示を非表示にする
            FrameLayout frame = alertView.findViewById(R.id.circleORcrossLayout);
            for(int i=0; i<frame.getChildCount(); i++){
                frame.getChildAt(i).setVisibility(View.INVISIBLE);
            }

            //バツを表示
            View cross = frame.findViewById(R.id.crossPaint);
            cross.setVisibility(View.VISIBLE);

            //次へボタンをタップされたときの挙動
            Button next = alertView.findViewById(R.id.nextButton);
            //クイズが続く場合
            if(endFlag) {
                //タイマーの再開
                handler.post(runnable);
                next.setOnClickListener(v -> {
                    printNextQuiz(count);
                    Objects.requireNonNull(getDialog()).dismiss();
                });
            }
            //クイズが終了する場合
            else{
                next.setText("結果発表");
                next.setOnClickListener(v -> {
                    QuizActivity Activity = (QuizActivity) getActivity();
                    Objects.requireNonNull(getDialog()).dismiss();
                    //EndActivityへ切り替える
                    Activity.toEndActivity();
                });
            }

            alert.setView(alertView);
            dialog = alert.create();
            dialog.show();

            return dialog;
        }

        //printQuizを呼び出す
        private void printNextQuiz(int count) {
            QuizActivity Activity = (QuizActivity) getActivity();
            //次の問題を表示
            if(Activity != null) {
                Activity.printQuiz(QUIZ_LIST, count);
            }
        }
    }

}