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

    int nextQuestionId = getRandomQuestionId();

    private int getRandomQuestionId() {
        return new Random().nextInt(170) + 2;
    }

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
                new Task("A").execute();
            }
        });

        bButton = (Button) findViewById(R.id.bbtn);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task("B").execute();
            }
        });

        cButton = (Button) findViewById(R.id.cbtn);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task("C").execute();
            }
        });

        dButton = (Button) findViewById(R.id.dbtn);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task("D").execute();
            }
        });

        eButton = (Button) findViewById(R.id.ebtn);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task("E").execute();
            }
        });

        fButton = (Button) findViewById(R.id.fbtn);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task("F").execute();
            }
        });

        new Task("A").execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }


    private class Task extends AsyncTask<Void, Void, String> {

        public static final String URL_POST = "http://129.213.40.35:5000/send/";
        public static final String URL_GET = "http://129.213.40.35:5000/question/";
        String answerFromFront = null;

        public Task(String answer) {
            super();
            answerFromFront = answer;
            aButton.setEnabled(false);
            bButton.setEnabled(false);
            cButton.setEnabled(false);
            dButton.setEnabled(false);
            eButton.setEnabled(false);
            fButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... params) {

            bar(20);
            OkHttpClient client = new OkHttpClient();
            String json=getJson();
            while(json==null)json=getJson();
            RequestBody body = RequestBody.create(JSON,json);
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
                try {
                    Question question = new Gson().fromJson(questionJson, Question.class);
                    question.setAnswer(answerFromFront);
                    questionJson = new Gson().toJson(question, Question.class);
                } catch (Exception e) {
                    Log.e("vv", "getJson " + e.getMessage());
                }
            } catch (Exception e) {
                Log.e("vv", "getJson " + e.getMessage());
            }

            return questionJson;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            bar(100);
            boolean error = false;
            try {
                question = new Gson().fromJson(result, Question.class);
            } catch (Exception e) {
                error = true;
                questionTextView.setText("CLOUD HS. REDEPLOY");
                answerTextView.setVisibility(View.INVISIBLE);
                nextQuestionId = getRandomQuestionId();
                aButton.setVisibility(View.INVISIBLE);
                bButton.setVisibility(View.INVISIBLE);
                cButton.setVisibility(View.INVISIBLE);
                dButton.setVisibility(View.INVISIBLE);
                eButton.setVisibility(View.INVISIBLE);
                fButton.setVisibility(View.INVISIBLE);
            }
            if (!error && question!=null &&  questionTextView!=null) {
                try {
                    questionTextView.setText(question.getQuestion());
                }catch(Exception e){
                    System.out.println("VVVVV"+e.getMessage());
                }
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
            aButton.setEnabled(true);
            bButton.setEnabled(true);
            cButton.setEnabled(true);
            dButton.setEnabled(true);
            eButton.setEnabled(true);
            fButton.setEnabled(true);
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