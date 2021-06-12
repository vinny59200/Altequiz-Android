package com.vv.altequiz2;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    TextView et;
    private static ProgressBar progressBar;
    Question question;
    TextView t;
    Button toggle;
    Button bButton;
    Button cButton;
    Button dButton;
    Button eButton;

    int nextQuestionId=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (TextView) findViewById(R.id.AutoCompleteTextView01);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        t = (TextView) findViewById(R.id.textview);
        toggle = (Button) findViewById(R.id.button);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
        bButton = (Button) findViewById(R.id.button2);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        cButton = (Button) findViewById(R.id.button3);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });


        dButton = (Button) findViewById(R.id.button4);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });


        eButton = (Button) findViewById(R.id.button5);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
        new Task().execute();
    }

    @Override
    protected void onPause() {

        super.onPause();

        System.exit(0);

    }


    private class Task extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {

            System.out.println("vv in doinbackgrouund");
            progressBar.setProgress(20);
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, getJson());

            Request request = new Request.Builder()
                    .url("https://altequiz.osc-fr1.scalingo.io/send/")
                    .post(body)
                    .build();
            System.out.println("vv in doinbackgrouund 3");
            progressBar.setProgress(80);
            String text = null;
            try (Response response = client.newCall(request).execute()) {
                System.out.println("vv in doinbackgrouund 4");
                progressBar.setProgress(100);
                text = response.body().string();
                System.out.println("vv in doinbackgrouund 5, text:" + text);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }

        private String getJson() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://altequiz.osc-fr1.scalingo.io/question/"+nextQuestionId)
                    .build();
            System.out.println("vv in doinbackgrouund 0");
            progressBar.setProgress(40);
            String text = null;
            try (Response response = client.newCall(request).execute()) {
                System.out.println("vv in doinbackgrouund 1");
                progressBar.setProgress(60);
                text = response.body().string();
                System.out.println("vv in doinbackgrouund 2, text:" + text);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            progressBar.setProgress(100);
            System.out.println("VV gson"+result);

            question = new Gson().fromJson(result, Question.class);
            System.out.println("vv array answer size:"+question.getAnswer());
            System.out.println("vv array answer size:"+question.getAnswer().split(",").length);
            et.setText(result);
            String answer = "";
            answer = question.getAnswer();
            if (answer.length() > 1) answer = question.getAnswer().substring(0, 1);
            t.setText(question.getQuestion());
            nextQuestionId=(int)question.getId();


            cButton.setVisibility(View.VISIBLE);
            dButton.setVisibility(View.VISIBLE);
            eButton.setVisibility(View.VISIBLE);
            if (Integer.valueOf(question.getChoices()) == 2) {
                cButton.setVisibility(View.INVISIBLE);
                dButton.setVisibility(View.INVISIBLE);
                eButton.setVisibility(View.INVISIBLE);
            } else if (Integer.valueOf(question.getChoices()) == 3) {
                dButton.setVisibility(View.INVISIBLE);
                eButton.setVisibility(View.INVISIBLE);
            } else if (Integer.valueOf(question.getChoices()) == 4) {
                eButton.setVisibility(View.INVISIBLE);
            }


        }
    }

    private class Question {

        private int id;
        private String question;
        private String answer;
        private int karma;
        private String choices;


        public Question(int id, String question, String answer, int karma) {
            this.id = id;
            this.question = question; //Log.e("vv", "question " + question);
            this.answer = answer; //Log.e("vv", "answer " + answer);
            this.karma = karma;


        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public double getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getKarma() {
            return karma;
        }

        public void setKarma(int karma) {
            this.karma = karma;
        }

        public String getChoices() {
            return choices;
        }

        public void setChoices(String choices) {
            this.choices = choices;
        }
    }
}