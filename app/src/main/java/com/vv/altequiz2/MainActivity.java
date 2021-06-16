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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//vle
public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static ProgressBar progressBar;


    Question question;
    List<Question> questionsTrack = new ArrayList<>();
    int nextQuestionId = getRandomQuestionId();

    TextView questionTextView;
    TextView answerTextView;
    TextView answerATextView;
    TextView answerBTextView;
    TextView answerCTextView;
    TextView answerDTextView;
    TextView answerETextView;
    TextView answerFTextView;
    Button replayButton;
    Button aButton;
    Button bButton;
    Button cButton;
    Button dButton;
    Button eButton;
    Button fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerATextView = (TextView) findViewById(R.id.atv);
        answerBTextView = (TextView) findViewById(R.id.btv);
        answerCTextView = (TextView) findViewById(R.id.ctv);
        answerDTextView = (TextView) findViewById(R.id.dtv);
        answerETextView = (TextView) findViewById(R.id.etv);
        answerFTextView = (TextView) findViewById(R.id.ftv);

        answerTextView = (TextView) findViewById(R.id.answertv);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        questionTextView = (TextView) findViewById(R.id.textview);

        declareReplaybtn();

        declareAbtn();

        declareBbtn();

        declareCbtn();

        declareDbtn();

        declareEbtn();

        declareFbtn();

        launchTaskWithAnswer("A");//TODO smell code
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }


    //    ___  ___      _               _____         _
    //    |  \/  |     (_)             |_   _|       | |
    //    | .  . | __ _ _ _ __           | | __ _ ___| | __
    //    | |\/| |/ _` | | '_ \          | |/ _` / __| |/ /
    //    | |  | | (_| | | | | |         | | (_| \__ \   <
    //    \_|  |_/\__,_|_|_| |_|         \_/\__,_|___/_|\_\
    //

    private class Task extends AsyncTask<Void, Void, String> {

        public static final String URL_POST = "http://129.213.40.35:5000/send/";
        public static final String URL_GET = "http://129.213.40.35:5000/question/";
        String answerFromFront = null;

        public Task(String answer) {
            super();
            answerFromFront = answer;
            disableButtons();
        }

        @Override
        protected String doInBackground(Void... params) {

            updateProgressBar(20);

            OkHttpClient client = new OkHttpClient();

            Request request = getQuestionPOSTRequest();

            updateProgressBar(80);

            String nextQuestionJSON = getNextQuestionFromPOSTRequest(client, request);

            return nextQuestionJSON;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            updateProgressBar(100);

            if (questionsTrack.size() == 5) {
                int max = Integer.MIN_VALUE;
                int questionIdForDecile = 0;
                for (Question quest : questionsTrack) {
                    if (quest.getKarma() > max) {
                        max = quest.getKarma();
                        questionIdForDecile = (int) quest.getId();
                    }
                }
                hideButtons();
                replayButton.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);
                answerATextView.setVisibility(View.INVISIBLE);
                answerBTextView.setVisibility(View.INVISIBLE);
                answerCTextView.setVisibility(View.INVISIBLE);
                answerDTextView.setVisibility(View.INVISIBLE);
                answerETextView.setVisibility(View.INVISIBLE);
                answerFTextView.setVisibility(View.INVISIBLE);
                questionTextView.setText("Votre classement est en cours de calcul");
                new DecileTask("" + questionIdForDecile).execute();
            } else {
                boolean error = isQuestionUpdateFailed(result);//TODO smell code

                if (!error && isQuestionTextViewValid()) {
                    updateQuestionTextView();
                    answerTextView.setText(question.getAnswer());
                    nextQuestionId = (int) question.getId();

                    displayCDEFButtons();
                    if (Integer.valueOf(question.getChoices_count()) == 2) {
                        hideCDEFButtons();
                    } else if (Integer.valueOf(question.getChoices_count()) == 3) {
                        hideDEFButtons();
                    } else if (Integer.valueOf(question.getChoices_count()) == 4) {
                        hideEFButtons();
                    } else if (Integer.valueOf(question.getChoices_count()) == 5) {
                        fButton.setVisibility(View.INVISIBLE);
                    }
                }
            }
            enableButtons();
        }


        //        _____         _           ______     _            _
        //       |_   _|       | |          | ___ \   (_)          | |
        //         | | __ _ ___| | __       | |_/ / __ ___   ____ _| |_ ___  ___
        //         | |/ _` / __| |/ /       |  __/ '__| \ \ / / _` | __/ _ \/ __|
        //         | | (_| \__ \   <        | |  | |  | |\ V / (_| | ||  __/\__ \
        //         \_/\__,_|___/_|\_\       \_|  |_|  |_| \_/ \__,_|\__\___||___/
        //
        //

        @Nullable
        private String getNextQuestionFromPOSTRequest(OkHttpClient client, Request request) {
            String nextQuestionJSON = "";
            try (Response response = client.newCall(request).execute()) {
                updateProgressBar(100);
                nextQuestionJSON = response.body().string();
            } catch (IOException e) {
                log(e, "error while getting question JSON post request");
            }
            return nextQuestionJSON;
        }

        @NotNull
        private Request getQuestionPOSTRequest() {
            String questionJSON = getQuestionJSON();
            RequestBody body = RequestBody.create(JSON, questionJSON);
            Request request = new Request.Builder()
                    .url(URL_POST)
                    .post(body)
                    .build();
            return request;
        }

        @NotNull
        private String getQuestionJSON() {
            String questionJSON = subGetQuestionJSON();
            while (questionJSON == null) questionJSON = subGetQuestionJSON();//TODO smell code
            return questionJSON;
        }

        private String subGetQuestionJSON() {
            OkHttpClient client = new OkHttpClient();
            System.out.println("VV234"+URL_GET + nextQuestionId);
            Request request = new Request.Builder()
                    .url(URL_GET + nextQuestionId)
                    .build();
            updateProgressBar(40);
            String questionJson = null;
            try (Response response = client.newCall(request).execute()) {
                updateProgressBar(60);
                questionJson = response.body().string();
                questionJson = updateQuestionJSONWithAnswerFromFront(questionJson);
            } catch (Exception e) {
                e.printStackTrace();
                log(e, "error in the sub process of getting JSON question");
            }

            return questionJson;
        }

        private String updateQuestionJSONWithAnswerFromFront(String questionJson) {
            try {
                Question question = new Gson().fromJson(questionJson, Question.class);
                question.setAnswer(answerFromFront);
                questionsTrack.add(question);
                questionJson = new Gson().toJson(question, Question.class);
            } catch (Exception e) {
                log(e, "error while modifying question JSON with answer from the front");
            }
            return questionJson;
        }

        private boolean isQuestionTextViewValid() {
            return question != null && questionTextView != null;
        }

        private void updateQuestionTextView() {
            try {
                String choices="";
                int cpt=1;
                for(String choice: question.getChoices_content().split(" ### ")){
                //for(String choice: question.getChoices_content().split("###")){
                    //choices=new String(choices+"\n"+choice.trim());
                    switch (cpt){
                        case 1:
                            answerATextView.setText(choice.trim().substring(2));
                            break;
                        case 2:
                            answerBTextView.setText(choice.trim().substring(2));
                            break;
                        case 3:
                            answerCTextView.setText(choice.trim().substring(2));
                            break;
                        case 4:
                            answerDTextView.setText(choice.trim().substring(2));
                            break;
                        case 5:
                            answerETextView.setText(choice.trim().substring(2));
                            break;
                        case 6:
                            answerFTextView.setText(choice.trim().substring(2));
                            break;
                        default:
                            answerATextView.setText(choice.trim().substring(2));
                    }
                    cpt++;
                }

                questionTextView.setText(question.getQuestion()+choices);
                //questionTextView.setText(question.getQuestion()+choices);
            } catch (Exception e) {
                log(e, "error while setting the question text view");
            }
        }
    }

    //   ______          _ _              _____         _
    //   |  _  \        (_) |            |_   _|       | |
    //   | | | |___  ___ _| | ___          | | __ _ ___| | __
    //   | | | / _ \/ __| | |/ _ \         | |/ _` / __| |/ /
    //   | |/ /  __/ (__| | |  __/         | | (_| \__ \   <
    //   |___/ \___|\___|_|_|\___|         \_/\__,_|___/_|\_\
    //

    private class DecileTask extends AsyncTask<Void, Void, String> {

        public static final String DECILE_URL_GET = "http://129.213.40.35:5000/decile/";
        private String finalQuestionId = "2";

        public DecileTask(String idQuestionFinale) {
            super();
            finalQuestionId = idQuestionFinale;
        }

        @Override
        protected String doInBackground(Void... params) {
            System.out.println("doing decile task");
            return getDecile(finalQuestionId);
        }

        protected void onPostExecute(String decile) {
            System.out.println("post ex dec task");
            questionTextView.setText("Vous etes apres " + Integer.parseInt(decile)*10 + "% des joueurs");
        }

        private String getDecile(String id) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(DECILE_URL_GET + id)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String result = response.body().string();
                return result.substring(0, result.length() - 2);
            } catch (IOException e) {
                log(e, "decileKO");
            }
            return "KO";
        }
    }
    //    ___       _   _       _ _                ______     _            _
    //   / _ \     | | (_)     (_) |               | ___ \   (_)          | |
    //  / /_\ \ ___| |_ ___   ___| |_ _   _        | |_/ / __ ___   ____ _| |_ ___  ___
    //  |  _  |/ __| __| \ \ / / | __| | | |       |  __/ '__| \ \ / / _` | __/ _ \/ __|
    //  | | | | (__| |_| |\ V /| | |_| |_| |       | |  | |  | |\ V / (_| | ||  __/\__ \
    //  \_| |_/\___|\__|_| \_/ |_|\__|\__, |       \_|  |_|  |_| \_/ \__,_|\__\___||___/
    //                                 __/ |
    //                                |___/

    private void launchTaskWithAnswer(String answer) {
        new Task(answer).execute();
    }

    private boolean isQuestionUpdateFailed(String result) {
        boolean error = false;
        try {
            question = new Gson().fromJson(result, Question.class);
        } catch (Exception e) {
            error = true;
            nextQuestionId = getRandomQuestionId();
            questionTextView.setText("CLOUD HS. REDEPLOY");
            answerTextView.setVisibility(View.INVISIBLE);
            hideButtons();
        }
        return error;
    }

    private int getRandomQuestionId() {
        return new Random().nextInt(170) + 2;
    }

    private void updateProgressBar(int step) {
        progressBar.setProgress(step);
    }

    private void declareReplaybtn() {
        replayButton = (Button) findViewById(R.id.replaybtn);
        replayButton.setVisibility(View.INVISIBLE);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void declareFbtn() {
        fButton = (Button) findViewById(R.id.fbtn);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("F");
            }
        });
    }

    private void declareEbtn() {
        eButton = (Button) findViewById(R.id.ebtn);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("E");
            }
        });
    }

    private void declareDbtn() {
        dButton = (Button) findViewById(R.id.dbtn);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("D");
            }
        });
    }

    private void declareCbtn() {
        cButton = (Button) findViewById(R.id.cbtn);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("C");
            }
        });
    }

    private void declareBbtn() {
        bButton = (Button) findViewById(R.id.bbtn);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("B");
            }
        });
    }

    private void declareAbtn() {
        aButton = (Button) findViewById(R.id.abtn);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("A");
            }
        });
    }

    private void hideEFButtons() {
        eButton.setVisibility(View.INVISIBLE);
        fButton.setVisibility(View.INVISIBLE);
    }

    private void hideDEFButtons() {
        dButton.setVisibility(View.INVISIBLE);
        hideEFButtons();
    }

    private void hideCDEFButtons() {
        cButton.setVisibility(View.INVISIBLE);
        hideDEFButtons();
    }

    private void hideButtons() {
        aButton.setVisibility(View.INVISIBLE);
        bButton.setVisibility(View.INVISIBLE);
        hideCDEFButtons();
    }

    private void displayButtons() {
        aButton.setVisibility(View.VISIBLE);
        bButton.setVisibility(View.VISIBLE);
        displayCDEFButtons();
    }

    private void displayCDEFButtons() {
        cButton.setVisibility(View.VISIBLE);
        dButton.setVisibility(View.VISIBLE);
        eButton.setVisibility(View.VISIBLE);
        fButton.setVisibility(View.VISIBLE);
    }

    private void enableButtons() {
        aButton.setEnabled(true);
        bButton.setEnabled(true);
        cButton.setEnabled(true);
        dButton.setEnabled(true);
        eButton.setEnabled(true);
        fButton.setEnabled(true);
    }

    private void disableButtons() {
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        eButton.setEnabled(false);
        fButton.setEnabled(false);
    }

    private void log(Exception e, String str) {
        Log.e("altequiz", str + e.getMessage());
    }

    //    _____                              _____ _
    //   |_   _|                            /  __ \ |
    //     | | _ __  _ __   ___ _ __        | /  \/ | __ _ ___ ___
    //     | || '_ \| '_ \ / _ \ '__|       | |   | |/ _` / __/ __|
    //    _| || | | | | | |  __/ |          | \__/\ | (_| \__ \__ \
    //    \___/_| |_|_| |_|\___|_|           \____/_|\__,_|___/___/
    //

    private class Question {

        private int id;
        private String question;
        private String choices_content;
        private String answer;
        private int karma;
        private int choices_count;

        public Question(int id, String question, String answer, int karma, int choices_count, String choices_content) {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.karma = karma;
            this.choices_content = choices_content;
            this.choices_count = choices_count;
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

        public String getChoices_content() {
            return choices_content;
        }

        public void setChoices_content(String choices_content) {
            this.choices_content = choices_content;
        }

        public int getChoices_count() {
            return choices_count;
        }

        public void setChoices_count(int choices_count) {
            this.choices_count = choices_count;
        }
    }
}
