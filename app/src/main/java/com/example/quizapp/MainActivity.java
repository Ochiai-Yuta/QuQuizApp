package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //ArrayList＜QuizAttrクラス型を格納する＞　の宣言
    public static ArrayList<QuizAtrr> QUIZ_ATRR = new ArrayList<>();
    //一回目かどうかを判定するフラグ.trueで複数回と判定する。
    private boolean pluralTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activity_main.xmlを描画する。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //一回目かどうか調べる
        Intent intent = getIntent();
        pluralTime = intent.getBooleanExtra("PLURAL", false);

        //スタート画面のラベルに文字を入れる
        TextView label = this.findViewById(R.id.startLabel);
        label.setText(R.string.text);
    }

    //戻るボタンの無効化
    @Override
    public void onBackPressed() {
    }

    //スタートボタンをタップされたときの挙動
    public void tapStartButton(View view){
        //一回目のみ実行
        if(!pluralTime) {
            //JSONファイルを読み込んで、String型にキャストして、dataに入れる。
            String data;
            if ((data = readFile()) == null) {
                //dataにnullがないかチェック
                throw new IllegalArgumentException();
            }

            //JSONObject型の変数、先ほど読み込んだString型のJSON形式のデータがはいる。
            JSONObject JsonObject;

            try {
                //dataをJSONObjectとして開く
                JsonObject = new JSONObject(data);

                //JsonObjectにある配列、”quiz”を読み込んで、JsonArrayとする。
                JSONArray JsonArray = JsonObject.getJSONArray("quiz");
                //JsonArrayの配列の数をcountに入れる。
                int count = JsonArray.length();
                //エミュレータ起動させて、下の”▶ Run”のタブを開くと、出力が見られるよ。
                System.out.println(count);

                for (int n = 0; n < count; n++) {
                    //n番目の配列の要素をJobjectとする。
                    JSONObject Jobject = JsonArray.getJSONObject(n);

                    //Jobjectの要素、"id","quizText","answer"をそれぞれ取り出す。
                    int id = Jobject.getInt("id");
                    String quiz_text = Jobject.getString("quizText");
                    String answer_text = Jobject.getString("answer");

                    //ArrayListに取り出した値を格納する。
                    QUIZ_ATRR.add(new QuizAtrr(id, quiz_text, answer_text));
                }
            } catch (JSONException JE) {
                //tryでエラーが出るとアプリを強制終了する。
                JE.printStackTrace();
                finish();
                System.exit(-1);
            }
        }

        //画面の切り替え
        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
        //intentにQUIZ_LISTという名前(Key)で、QUIZ_ATRRを渡す。
        intent.putExtra("QUIZ_LIST", QUIZ_ATRR);
        startActivity(intent);
    }

    //quizの要素を格納するクラス
    public static class QuizAtrr implements Serializable {
        int id;
        String quiz_text;
        String answer_text;

        //このクラスのコンストラクタ
        QuizAtrr(int id, String quiz_text, String answer_text){
            this.id = id;
            this.quiz_text = quiz_text;
            this.answer_text = answer_text;
        }
    }

    //assetsにある、ファイルを読み込む関数
    private String readFile(){
        AssetManager as = getResources().getAssets();
        InputStream is;
        //連結にappend()を使用するため、StringBuilderクラスを使用
        StringBuilder data = new StringBuilder();

        try {
            //quiz.jsonを開く
            is = as.open("quiz.json");
            //Readerの生成
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            //BufferedReaderの生成
            BufferedReader bufferedReader = new BufferedReader(reader);

            //BufferedReaderが指す、行（最初だから1行目）をlineに格納。
            String line = bufferedReader.readLine();

            //line(BufferedReaderが指す行の内容)がnullになる(ファイルの終端になる)まで繰り返す
            while(line != null){
                //dataにlineを足す。
                data.append(line);
                //BufferedReaderを1行後ろにする。
                line = bufferedReader.readLine();
            }

            //StringBuilderをStringに変換して返す
            return data.toString();
        } catch (IOException e) {
            //tryでエラーが出るとnullを返す。
            e.printStackTrace();
            return null;
        }
    }
}