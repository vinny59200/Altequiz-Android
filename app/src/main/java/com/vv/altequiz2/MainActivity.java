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
import java.util.Random;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    TextView answerTextView;
    private static ProgressBar progressBar;
    Question question;
    TextView questionTextView;
    Button aButton;
    Button bButton;
    Button cButton;
    Button dButton;
    Button eButton;
    Button fButton;

    int nextQuestionId = new Random().nextInt(170) + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerTextView = (TextView) findViewById(R.id.answertv);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        questionTextView = (TextView) findViewById(R.id.textview);

        aButton = (Button) findViewById(R.id.abtn);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        bButton = (Button) findViewById(R.id.bbtn);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        cButton = (Button) findViewById(R.id.cbtn);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        dButton = (Button) findViewById(R.id.dbtn);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        eButton = (Button) findViewById(R.id.ebtn);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });

        fButton = (Button) findViewById(R.id.fbtn);
        fButton.setOnClickListener(new View.OnClickListener() {
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

        public static final String URL_POST = "https://altequiz.osc-fr1.scalingo.io/send/";
        public static final String URL_GET = "https://altequiz.osc-fr1.scalingo.io/question/";

        @Override
        protected String doInBackground(Void... params) {

            bar(20);
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, getJson());
            Request request = new Request.Builder()
                    .url(URL_POST)
                    .post(body)
                    .build();
            bar(80);
            String questionJson = null;
            try (Response response = client.newCall(request).execute()) {
                bar(100);
                questionJson = response.body().string();
            } catch (IOException e) {
                Log.e("vv", "task get question json post " + e.getMessage());
            }

            return questionJson;
        }

        private void bar(int step) {
            progressBar.setProgress(step);
        }

        private String getJson() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL_GET + nextQuestionId)
                    .build();
            bar(40);
            String questionJson = null;
            try (Response response = client.newCall(request).execute()) {
                bar(60);
                questionJson = response.body().string();
            } catch (IOException e) {
                Log.e("vv", "getJson " + e.getMessage());
            }

            return questionJson;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            bar(100);
            question = new Gson().fromJson(result, Question.class);
            questionTextView.setText(question.getQuestion());
            answerTextView.setText(question.getAnswer());
            nextQuestionId = (int) question.getId();

            cButton.setVisibility(View.VISIBLE);
            dButton.setVisibility(View.VISIBLE);
            eButton.setVisibility(View.VISIBLE);
            fButton.setVisibility(View.VISIBLE);
            if (Integer.valueOf(question.getChoices()) == 2) {
                cButton.setVisibility(View.INVISIBLE);
                dButton.setVisibility(View.INVISIBLE);
                eButton.setVisibility(View.INVISIBLE);
                fButton.setVisibility(View.INVISIBLE);
            } else if (Integer.valueOf(question.getChoices()) == 3) {
                dButton.setVisibility(View.INVISIBLE);
                eButton.setVisibility(View.INVISIBLE);
                fButton.setVisibility(View.INVISIBLE);
            } else if (Integer.valueOf(question.getChoices()) == 4) {
                eButton.setVisibility(View.INVISIBLE);
                fButton.setVisibility(View.INVISIBLE);
            } else if (Integer.valueOf(question.getChoices()) == 5) {
                fButton.setVisibility(View.INVISIBLE);
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
            this.question = question;
            this.answer = answer;
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