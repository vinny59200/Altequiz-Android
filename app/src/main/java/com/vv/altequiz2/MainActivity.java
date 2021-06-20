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
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//  authors: vv feat vle
//
//           ___  _ _                   _
//          / _ \| | |                 (_)
//         / /_\ \ | |_ ___  __ _ _   _ _ ____
//         |  _  | | __/ _ \/ _` | | | | |_  /
//         | | | | | ||  __/ (_| | |_| | |/ /
//         \_| |_/_|\__\___|\__, |\__,_|_/___|
//                             | |
//                             |_|
//         The intelligent quiz on sustainable development.


public class MainActivity extends AppCompatActivity {


    public static final String FIRST_URL_GET = "http://129.213.40.35:5000/api/v1/first/";
    public static final String URL_GET = "http://129.213.40.35:5000/api/v1/question/";
    public static final String URL_POST = "http://129.213.40.35:5000/api/v1/send/";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static final String BLANK_NOT_PROCESSED = "BLANK_NOT_PROCESSED";
    private ProgressBar progressBar;

    Question question;
    Set<Question> questionsStack = new HashSet<>();
    boolean isAnswersAllGood = true;
    int nextId;
    int score = 5;

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

        questionTextView = findViewById(R.id.textview);
        answerATextView = findViewById(R.id.atv);
        answerBTextView = findViewById(R.id.btv);
        answerCTextView = findViewById(R.id.ctv);
        answerDTextView = findViewById(R.id.dtv);
        answerETextView = findViewById(R.id.etv);
        answerFTextView = findViewById(R.id.ftv);

        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.bar);
        tipTextView = findViewById(R.id.answertv);

        declareReplaybtn();
        declareLinkbtn();
        declareAbtn();
        declareBbtn();
        declareCbtn();
        declareDbtn();
        declareEbtn();
        declareFbtn();

        launchTaskWithAnswer(BLANK_NOT_PROCESSED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }

    //==============================================================================================
    //    ___  ___      _               _____         _
    //    |  \/  |     (_)             |_   _|       | |
    //    | .  . | __ _ _ _ __           | | __ _ ___| | __
    //    | |\/| |/ _` | | '_ \          | |/ _` / __| |/ /
    //    | |  | | (_| | | | | |         | | (_| \__ \   <
    //    \_|  |_/\__,_|_|_| |_|         \_/\__,_|___/_|\_\
    //

    private class Task extends AsyncTask<Void, Void, String> {

        String userAnswer;

        public Task(String answer) {
            super();
            userAnswer = answer;
            disableButtons();
        }

        @Override
        protected String doInBackground(Void... params) {

            updateProgressBar(20);

            return getQuestionJSON();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            updateProgressBar(100);

            bButton.setVisibility(View.VISIBLE);
            aButton.setVisibility(View.VISIBLE);

            if (isOver()) {
                handleDisplayWhenOver();
                questionTextView.setText(String.format("Votre score est de%s%%.",
                        calculateStars() * 10));
            } else {
                question = new Gson().fromJson(result, Question.class);
                nextId = question.getId();
                handleDisplayWhenNotOver();
            }
            logAltequiz("VV 700 quest Id:" + nextId + ", count quest:" + questionsStack.size() +
                    ", perfect:" + isAnswersAllGood + " karma:" + question.getKarma());
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
        private String getQuestionJSON() {
            OkHttpClient clt = initRequest();
            String json = null;
            while (json == null) {
                if (questionsStack.isEmpty()) {
                    json = subGetFirstQuestionJSON();
                } else {
                    json = subGetQuestionJSON();
                }
            }
            if (!questionsStack.isEmpty()) {
                RequestBody body = RequestBody.create(json, JSON);
                String url = URL_POST;
                logAltequiz("VV 3333 POST: " + url + " for current question id:" + nextId);
                Request req = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                updateProgressBar(80);
                json = null;
                try (Response resp = clt.newCall(req).execute()) {
                    updateProgressBar(100);
                    json = resp.body().string();
                } catch (Exception e) {
                    logAltequiz("VV 6663 Retry main POST sending question request call " +
                            "for question id:" + nextId);
                }
                if (json == null) {
                    return getQuestionJSON();
                } else {
                    return json;
                }
            }
            return json;
        }

        private String subGetFirstQuestionJSON() {
            OkHttpClient clt = initRequest();
            logAltequiz("VV 100 getting first question.");
            String url = FIRST_URL_GET;
            logAltequiz("VV 3331 GET: " + url);
            Request req = new Request.Builder()
                    .url(url)
                    .build();
            updateProgressBar(40);
            String json = null;
            try (Response resp = clt.newCall(req).execute()) {
                updateProgressBar(60);
                nextId = Integer.valueOf(resp.body().string());
                logAltequiz("VV 100 first question id=" + nextId);
                json = subGetQuestionJSON();
                if (nextId == 0) throw new Exception();
            } catch (Exception e) {
            }
            if (json == null) {
                logAltequiz("VV 6661 retry GET first question JSON request call");
                return subGetFirstQuestionJSON();
            } else {
                return json;
            }
        }

        private String subGetQuestionJSON() {
            OkHttpClient clt = initRequest();
            String url = URL_GET + nextId;
            logAltequiz("VV 3332 GET: " + url);
            Request req = new Request.Builder()
                    .url(url)
                    .build();
            updateProgressBar(40);
            String json = null;
            try (Response response = clt.newCall(req).execute()) {
                updateProgressBar(60);
                json = response.body().string();
            } catch (Exception e) {
            }
            if (json == null) {
                logAltequiz("VV 6662 retry GET question JSON request call for question id:"
                        + nextId);
                return subGetQuestionJSON();
            } else {
                Question q = new Gson().fromJson(json, Question.class);
                isAnswersAllGood = isAnswersAllGood(q.getAnswer(), userAnswer);
                q.setAnswer(userAnswer);
                if (!BLANK_NOT_PROCESSED.equals(userAnswer)) questionsStack.add(q);
                json = new Gson().toJson(q, Question.class);
                return json;
            }
        }
    }

    //==============================================================================================
    //    ___       _   _       _ _                ______     _            _
    //   / _ \     | | (_)     (_) |               | ___ \   (_)          | |
    //  / /_\ \ ___| |_ ___   ___| |_ _   _        | |_/ / __ ___   ____ _| |_ ___  ___
    //  |  _  |/ __| __| \ \ / / | __| | | |       |  __/ '__| \ \ / / _` | __/ _ \/ __|
    //  | | | | (__| |_| |\ V /| | |_| |_| |       | |  | |  | |\ V / (_| | ||  __/\__ \
    //  \_| |_/\___|\__|_| \_/ |_|\__|\__, |       \_|  |_|  |_| \_/ \__,_|\__\___||___/
    //                                 __/ |
    //                                |___/

    private boolean isOver() {
        return (!isAnswersAllGood && questionsStack.size() > 9) || questionsStack.size() > 24;
    }

    private boolean isAnswersAllGood(String fromDB, String fromUser) {
        logAltequiz("VV 226 answers: " + fromDB + " | " + fromUser
                + " (latter one from the user)");
        if (BLANK_NOT_PROCESSED.equals(fromUser)) {
            return true;
        } else {
            boolean same = fromDB.trim().equals(fromUser.trim());
            if (same) {
                this.score = this.score + 1;
            } else {
                this.score = this.score - 1;
            }
            logAltequiz("vv 378: score" + score);
            return isAnswersAllGood && same;
        }

    }

    private int calculateStars() {
        int result = 5;
        if (this.score > 9) result = 10;
        else
            result = this.score % 10;
        return result;
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
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Connection", "close").build();
                    return chain.proceed(request);
                })
                .build();
    }

    private void logAltequiz(String str) {
        System.out.println(str);
    }


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
        if (question.getChoices_count() == 2) {
            hideCDEFButtons();
        } else if (question.getChoices_count() == 3) {
            hideDEFButtons();
        } else if (question.getChoices_count() == 4) {
            hideEFButtons();
        } else if (question.getChoices_count() == 5) {
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
        replayButton = findViewById(R.id.replaybtn);
        replayButton.setVisibility(View.INVISIBLE);
        replayButton.setOnClickListener(view -> {
            finish();
            startActivity(getIntent());
        });
    }

    private void declareLinkbtn() {
        blogButton = findViewById(R.id.blogbtn);
        blogButton.setVisibility(View.GONE);
        blogButton.setOnClickListener(view -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://worldcaretriviaapp.mystrikingly.com/"));
                    startActivity(browserIntent);
                }
        );
    }

    private void declareFbtn() {
        fButton = findViewById(R.id.fbtn);
        fButton.setVisibility(View.INVISIBLE);
        fButton.setOnClickListener(view ->
                launchTaskWithAnswer("F"));
    }

    private void declareEbtn() {
        eButton = findViewById(R.id.ebtn);
        eButton.setVisibility(View.INVISIBLE);
        eButton.setOnClickListener(view ->
                launchTaskWithAnswer("E")
        );
    }

    private void declareDbtn() {
        dButton = findViewById(R.id.dbtn);
        dButton.setVisibility(View.INVISIBLE);
        dButton.setOnClickListener(view ->
                launchTaskWithAnswer("D"));
    }

    private void declareCbtn() {
        cButton = findViewById(R.id.cbtn);
        cButton.setVisibility(View.INVISIBLE);
        cButton.setOnClickListener(view ->
                launchTaskWithAnswer("C"));
    }

    private void declareBbtn() {
        bButton = findViewById(R.id.bbtn);
        bButton.setVisibility(View.INVISIBLE);
        bButton.setOnClickListener(view ->
                launchTaskWithAnswer("B")
        );
    }

    private void declareAbtn() {
        aButton = findViewById(R.id.abtn);
        aButton.setVisibility(View.INVISIBLE);
        aButton.setOnClickListener(view ->
                launchTaskWithAnswer("A")
        );
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

    //==============================================================================================
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Question question1 = (Question) o;
            return id == question1.id &&
                    choices_count == question1.choices_count &&
                    question.equals(question1.question) &&
                    choices_content.equals(question1.choices_content) &&
                    answer.equals(question1.answer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, question, choices_content, answer, choices_count);
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
