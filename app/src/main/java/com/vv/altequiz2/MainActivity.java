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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
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


    public static final String FIRST_URL_GET = "http://129.213.40.35:5000/api/v1/question/first/";
    public static final String URL_GET = "http://129.213.40.35:5000/api/v1/question/";
    public static final String URL_POST = "http://129.213.40.35:5000/api/v1/question/next/";

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
    Button answerButton;
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
        answerButton = findViewById(R.id.answerbtn);
        answerATextView = findViewById(R.id.atv);
        answerBTextView = findViewById(R.id.btv);
        answerCTextView = findViewById(R.id.ctv);
        answerDTextView = findViewById(R.id.dtv);
        answerETextView = findViewById(R.id.etv);
        answerFTextView = findViewById(R.id.ftv);

        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.bar);
        //tipTextView = findViewById(R.id.answertv);

        declareAnswerbtn();
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

    private class Task extends AsyncTask<Void, Void, Question> {

        String userAnswer;

        public Task(String answer) {
            super();
            userAnswer = answer;
            disableButtons();
        }

        @Override
        protected Question doInBackground(Void... params) {

            updateProgressBar(20);

            return getQuestionJSON();
        }

        @Override
        protected void onPostExecute(Question result) {
            super.onPostExecute(result);

            updateProgressBar(100);

            bButton.setVisibility(View.VISIBLE);
            aButton.setVisibility(View.VISIBLE);

            if (isOver()) {
                handleDisplayWhenOver();
                questionTextView.setText(String.format("Votre score est de %s points.",
                        calculateScore()));
            } else {
                logAltequiz("VV 335:" + result);
                question = result;
                nextId = question.getId();
                handleDisplayWhenNotOver();
            }
            logAltequiz("VV 700 quest Id:" + nextId + ", count quest:" + questionsStack.size() +
                    ", perfect:" + isAnswersAllGood + " karma:" + question.getKarma());
            logAltequiz(result.toString());
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
        private Question getQuestionJSON() {
            Question json = null;
            while (json == null) {
                if (questionsStack.isEmpty()) {
                    json = subGetFirstQuestionJSON();
                } else {
                    json = subGetQuestionJSON();
                }
            }
            if (!questionsStack.isEmpty()) {

                updateProgressBar(80);
                json = null;
                updateProgressBar(100);

                Random randomizer = new Random();
                json = QuestionsCollection.questions.get(randomizer.nextInt(QuestionsCollection.questions.size()));

                if (json == null) {
                    return getQuestionJSON();
                } else {
                    return json;
                }
            }
            return json;
        }

        private Question subGetFirstQuestionJSON() {
            logAltequiz("VV 100 getting first question.");
            updateProgressBar(40);
            Question json = null;
            updateProgressBar(60);

            Random randomizer = new Random();
            json = QuestionsCollection.questions.get(randomizer.nextInt(QuestionsCollection.questions.size()));

            if (json == null) {
                logAltequiz("VV 6661 retry GET first question JSON request call");
                return subGetFirstQuestionJSON();
            } else {
                Question q = json;
                isAnswersAllGood = true;
                questionsStack.add(q);
                return json;
            }
        }

        private Question subGetQuestionJSON() {

            updateProgressBar(40);
            Question json = null;

            updateProgressBar(60);

            Random randomizer = new Random();
            json = QuestionsCollection.questions.get(randomizer.nextInt(QuestionsCollection.questions.size()));

            if (json == null) {
                logAltequiz("VV 6662 retry GET question JSON request call for question id:"
                        + nextId);
                return subGetQuestionJSON();
            } else {
                Question q = json;
                isAnswersAllGood = isAnswersAllGood(q.getAnswer(), userAnswer);
                q.setAnswer(userAnswer);
                questionsStack.add(q);
                json = q;
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
            fromDB = handleMultipleAnswers(fromDB, fromUser);
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

    private String handleMultipleAnswers(String realAnswer, String userAnswer) {
        List<String> answers = Arrays.asList(realAnswer.split("-"));
        if (answers.contains(userAnswer)) {
            return userAnswer;
        } else {
            return realAnswer;
        }
    }

    private int calculateStars() {
        int result = this.score;
        if (result > 9) result = 10;
        else if (result < 0) result = 0;
        return result;
    }

    private int calculateScore() {
        int result = this.score;
        if (result < 0) return 0;
        return result * 10;
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
        //tipTextView.setText(question.getAnswer());
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
        //tipTextView.setVisibility(View.GONE);
        answerButton.setVisibility(View.GONE);
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

    private void displayAnswerToast(String answer) {
        Toast.makeText(getApplicationContext(),
                answer,
                Toast.LENGTH_SHORT).show();
    }

    private void updateProgressBar(int step) {
        progressBar.setProgress(step);
    }

    private void declareAnswerbtn() {
        replayButton = findViewById(R.id.answerbtn);
        replayButton.setVisibility(View.VISIBLE);
        replayButton.setOnClickListener(view -> {
            displayAnswerToast(question.getAnswer());
        });
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
        answerButton.setVisibility(View.INVISIBLE);
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
}
