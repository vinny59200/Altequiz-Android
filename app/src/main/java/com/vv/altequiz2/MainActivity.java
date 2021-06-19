package com.vv.altequiz2;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//vle

public class MainActivity extends AppCompatActivity {


    public static final String FIRST_URL_GET = "http://129.213.40.35:5000/first/";
    public static final String URL_GET = "http://129.213.40.35:5000/question/";
    public static final String URL_POST = "http://129.213.40.35:5000/send/";
    public static final String DECILE_URL_GET = "http://129.213.40.35:5000/decile/";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static ProgressBar progressBar;

    Question question;
    Set<Question> questionsTrack = new HashSet<>();
    boolean isAnswersAllGood = true;
    int nextQuestionId;

    TextView questionTextView;
    TextView tipTextView;
    TextView answerATextView;
    TextView answerBTextView;
    TextView answerCTextView;
    TextView answerDTextView;
    TextView answerETextView;
    TextView answerFTextView;
    Button replayButton;
    Button blogButton;
    Button aButton;
    Button bButton;
    Button cButton;
    Button dButton;
    Button eButton;
    Button fButton;
    ImageView imageView;

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
        imageView = (ImageView) findViewById(R.id.image);

        tipTextView = (TextView) findViewById(R.id.answertv);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        questionTextView = (TextView) findViewById(R.id.textview);

        declareReplaybtn();

        declareLinkbtn();

        declareAbtn();

        declareBbtn();

        declareCbtn();

        declareDbtn();

        declareEbtn();

        declareFbtn();

        launchTaskWithAnswer("BLANK_NOT_PROCESSED");
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

        String answerFromFront = null;

        public Task(String answer) {
            super();
            answerFromFront = answer;
            disableButtons();
        }

        @Override
        protected String doInBackground(Void... params) {

            updateProgressBar(20);

            String nextQuestionJSON = getQuestionJSON(false);

            return nextQuestionJSON;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            updateProgressBar(100);

            bButton.setVisibility(View.VISIBLE);
            aButton.setVisibility(View.VISIBLE);

            if (isOver()) {
                int questionIdForDecile = getQuestionIdForDecile();
                handleDisplayWhenOver();
                launchDecileTask(questionIdForDecile);
            } else {
                question = new Gson().fromJson(result, Question.class);
                nextQuestionId = (int) question.getId();
                handleDisplayWhenNotOver();
            }
            logAltequiz("VV 700 count quest:" + questionsTrack.size() + ", perfect:" + isAnswersAllGood + ", quest Id:" + nextQuestionId + " karma:" + question.getKarma());
            logAltequiz(result);
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
        private String getQuestionJSON(boolean isRetrying) {
            OkHttpClient client = initRequest();
            String questionJSON = null;
            while (questionJSON == null) {
                if (questionsTrack.isEmpty()) {
                    questionJSON = subGetFirstQuestionJSON();
                } else {
                    questionJSON = subGetQuestionJSON();
                }
            }
            RequestBody body = RequestBody.create(JSON, questionJSON);
            String url = URL_POST;
            logAltequiz("VV 3333 url:" + url + " for current question id:" + nextQuestionId);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            updateProgressBar(80);
            String nextQuestionJSON = "";
            try (Response response = client.newCall(request).execute()) {
                updateProgressBar(100);
                nextQuestionJSON = response.body().string();
            } catch (Exception e) {
                logAltequiz("VV 6663 Retry main POST sending question request call for question id:" + nextQuestionId);
            }
            if (nextQuestionJSON == null) {
                return getQuestionJSON(true);
            } else {
                return nextQuestionJSON;
            }
        }

        private String subGetFirstQuestionJSON() {
            OkHttpClient client = initRequest();
            logAltequiz("VV 100 getting first question.");
            String url = FIRST_URL_GET;
            logAltequiz("VV 3331 url:" + url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            updateProgressBar(40);
            String questionJson = null;
            try (Response response = client.newCall(request).execute()) {
                updateProgressBar(60);
                nextQuestionId = Integer.valueOf(response.body().string()).intValue();
                questionJson = subGetQuestionJSON();
                if (nextQuestionId == 0) throw new Exception();
            } catch (Exception e) {
            }
            if (questionJson == null) {
                logAltequiz("VV 6661 retry GET first question JSON request call");
                return subGetFirstQuestionJSON();
            } else {
                return questionJson;
            }
        }

        private String subGetQuestionJSON() {
            OkHttpClient client = initRequest();
            String url = URL_GET + nextQuestionId;
            logAltequiz("VV 3332 url:" + url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            updateProgressBar(40);
            String questionJson = null;
            try (Response response = client.newCall(request).execute()) {
                updateProgressBar(60);
                questionJson = response.body().string();
            } catch (Exception e) {
            }
            if (questionJson == null) {
                logAltequiz("VV 6662 retry GET question JSON request call for question id:" + nextQuestionId);
                return subGetQuestionJSON();
            } else {
                Question question = new Gson().fromJson(questionJson, Question.class);
                isAnswersAllGood = isAnswersAllGood(question.getAnswer(), answerFromFront);
                question.setAnswer(answerFromFront);
                questionsTrack.add(question);
                questionJson = new Gson().toJson(question, Question.class);
                return questionJson;
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

        private String finalQuestionId = "2";

        public DecileTask(String idQuestionFinale) {
            super();
            finalQuestionId = idQuestionFinale;
        }

        @Override
        protected String doInBackground(Void... params) {
            String decile = getDecile(finalQuestionId);
            logAltequiz("VV 800 doing decile task, decile=" + decile);
            return decile;
        }

        protected void onPostExecute(String decile) {
            try {
                questionTextView.setText("Vous etes meilleur que " + calculateScore(decile) + "% des joueurs.");
            } catch (Exception e) {
                logAltequiz("Error in rank result display");
                questionTextView.setText("Calcul du resultat KO");
            }
        }

        private int calculateScore(String decile) {
            return Integer.parseInt(decile) * 10;
        }

        private String getDecile(String id) {
            OkHttpClient client = initRequest();
            String url = DECILE_URL_GET + id;
            logAltequiz("VV 3334 url:" + url);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String result = response.body().string();
                return result.substring(0, result.length() - 2);
            } catch (IOException e) {
                logAltequiz("VV 6664 Retry GET decile request call for question id:" + id);
                return getDecile(id);
            }
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

    private boolean isOver() {
        return (!isAnswersAllGood && questionsTrack.size() > 9) || isAllDOne();
    }

    private void launchDecileTask(int questionIdForDecile) {
        new DecileTask("" + questionIdForDecile).execute();
    }

    private int getQuestionIdForDecile() {
        int questionIdForDecile = 0;
        Question finalKarmaQuestion = questionsTrack.stream().reduce((prev, next) -> next).orElse(null);
        //TODO check if the id is right
        questionIdForDecile = (int) finalKarmaQuestion.getId();
        return questionIdForDecile;
    }

    private boolean isAllDOne() {
        //TODO code smell=>change it to isMaxScoreReached
        return questionsTrack.size() == 170;
    }

    private boolean isAnswersAllGood(String fromDB, String fromFront) {
        if (isAnswersAllGood && questionsTrack.isEmpty()) {
            return true;//TODO code smell
        } else {
            return fromDB.equals(fromFront);
        }
    }

    private void launchTaskWithAnswer(String answer) {
        new Task(answer).execute();
    }


    @NotNull
    private OkHttpClient initRequest() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Connection", "close").build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    private void logAltequiz(String str) {
        System.out.println(str);
    }

//
//    ______ _           _
//    |  _  (_)         | |
//    | | | |_ ___ _ __ | | __ _ _   _
//    | | | | / __| '_ \| |/ _` | | | |
//    | |/ /| \__ \ |_) | | (_| | |_| |
//     ___/ |_|___/ .__/|_|\__,_|\__, |
//                 | |             __/ |
//                 |_|            |___/


    private void handleDisplayWhenNotOver() {
        questionTextView.setText(question.getQuestion());
        updateChoicesTextViews();
        tipTextView.setText(question.getAnswer());
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

    private void updateChoicesTextViews() {
        int cpt = 1;
        for (String choice : question.getChoices_content().split(" ### ")) {
            switch (cpt) {
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
            }
            cpt++;
        }
    }

    private void handleDisplayWhenOver() {
        hide();
        replayButton.setVisibility(View.VISIBLE);
        blogButton.setVisibility(View.VISIBLE);
        tipTextView.setVisibility(View.GONE);
        answerATextView.setVisibility(View.INVISIBLE);
        answerBTextView.setVisibility(View.INVISIBLE);
        answerCTextView.setVisibility(View.INVISIBLE);
        answerDTextView.setVisibility(View.INVISIBLE);
        answerETextView.setVisibility(View.INVISIBLE);
        answerFTextView.setVisibility(View.INVISIBLE);
        questionTextView.setText("Votre résultat est en cours de calcul");
        displayBlogToast();
    }

    private void displayBlogToast() {
        Toast.makeText(getApplicationContext(),
                "Découvrez les réponses dans le blog!",
                Toast.LENGTH_SHORT).show();
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

    private void declareLinkbtn() {
        blogButton = (Button) findViewById(R.id.blogbtn);
        blogButton.setVisibility(View.GONE);
        blogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://worldcaretriviaapp.mystrikingly.com/"));
                startActivity(browserIntent);
            }
        });
    }

    private void declareFbtn() {
        fButton = (Button) findViewById(R.id.fbtn);
        fButton.setVisibility(View.INVISIBLE);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("F");
            }
        });
    }

    private void declareEbtn() {
        eButton = (Button) findViewById(R.id.ebtn);
        eButton.setVisibility(View.INVISIBLE);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("E");
            }
        });
    }

    private void declareDbtn() {
        dButton = (Button) findViewById(R.id.dbtn);
        dButton.setVisibility(View.INVISIBLE);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("D");
            }
        });
    }

    private void declareCbtn() {
        cButton = (Button) findViewById(R.id.cbtn);
        cButton.setVisibility(View.INVISIBLE);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("C");
            }
        });
    }

    private void declareBbtn() {
        bButton = (Button) findViewById(R.id.bbtn);
        bButton.setVisibility(View.INVISIBLE);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("B");
            }
        });
    }

    private void declareAbtn() {
        aButton = (Button) findViewById(R.id.abtn);
        aButton.setVisibility(View.INVISIBLE);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTaskWithAnswer("A");
            }
        });
    }

    private void hideEFButtons() {
        eButton.setVisibility(View.INVISIBLE);
        answerETextView.setVisibility(View.INVISIBLE);
        fButton.setVisibility(View.INVISIBLE);
        answerFTextView.setVisibility(View.INVISIBLE);
    }

    private void hideDEFButtons() {
        dButton.setVisibility(View.INVISIBLE);
        answerDTextView.setVisibility(View.INVISIBLE);
        hideEFButtons();
    }

    private void hideCDEFButtons() {
        cButton.setVisibility(View.INVISIBLE);
        answerCTextView.setVisibility(View.INVISIBLE);
        hideDEFButtons();
    }

    private void hide() {
        imageView.setVisibility(View.GONE);
        aButton.setVisibility(View.INVISIBLE);
        answerATextView.setVisibility(View.INVISIBLE);
        bButton.setVisibility(View.INVISIBLE);
        answerBTextView.setVisibility(View.INVISIBLE);
        hideCDEFButtons();
    }

    private void displayCDEFButtons() {
        imageView.setVisibility(View.GONE);
        cButton.setVisibility(View.VISIBLE);
        answerCTextView.setVisibility(View.VISIBLE);
        dButton.setVisibility(View.VISIBLE);
        answerDTextView.setVisibility(View.VISIBLE);
        eButton.setVisibility(View.VISIBLE);
        answerETextView.setVisibility(View.VISIBLE);
        fButton.setVisibility(View.VISIBLE);
        answerFTextView.setVisibility(View.VISIBLE);
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

        public Question(int id, String question, String choices_content, String answer, int karma, int choices_count) {
            this.id = id;
            this.question = question;
            this.choices_content = choices_content;
            this.answer = answer;
            this.karma = karma;
            this.choices_count = choices_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoices_content() {
            return choices_content;
        }

        public void setChoices_content(String choices_content) {
            this.choices_content = choices_content;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getKarma() {
            return karma;
        }

        public void setKarma(int karma) {
            this.karma = karma;
        }

        public int getChoices_count() {
            return choices_count;
        }

        public void setChoices_count(int choices_count) {
            this.choices_count = choices_count;
        }

        @Override
        public String toString() {
            return "Question{" +
                    "id=" + id +
                    ", question='" + question + '\'' +
                    ", choices_content='" + choices_content + '\'' +
                    ", answer='" + answer + '\'' +
                    ", karma=" + karma +
                    ", choices_count=" + choices_count +
                    '}';
        }
    }
}
