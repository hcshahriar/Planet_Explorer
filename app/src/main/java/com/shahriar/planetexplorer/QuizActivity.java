package com.shahriar.planetexplorer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.shahriar.planetexplorer.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView txtScore, txtProgress, txtQuestion, txtFeedback;
    private ProgressBar quizProgressBar;
    private Button[] optionButtons = new Button[4];
    private Button btnNext;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean isAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Bind Views
        ImageView btnBack = findViewById(R.id.btnBackQuiz);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        txtScore = findViewById(R.id.txtScore);
        txtProgress = findViewById(R.id.txtProgress);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtFeedback = findViewById(R.id.txtFeedback);
        quizProgressBar = findViewById(R.id.quizProgressBar);
        btnNext = findViewById(R.id.btnNext);

        optionButtons[0] = findViewById(R.id.btnOption0);
        optionButtons[1] = findViewById(R.id.btnOption1);
        optionButtons[2] = findViewById(R.id.btnOption2);
        optionButtons[3] = findViewById(R.id.btnOption3);

        loadQuestions();
        displayQuestion();

        for (int i = 0; i < optionButtons.length; i++) {
            final int selectedIndex = i;
            optionButtons[i].setOnClickListener(v -> checkAnswer(selectedIndex));
        }

        btnNext.setOnClickListener(v -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                displayQuestion();
            } else {
                showFinalResults();
            }
        });
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
                1,
                "Mars appears red due to iron oxide (rust) on its surface."
        ));
        questionList.add(new Question(
                "Which is the hottest planet in our solar system?",
                new String[]{"A) Mercury", "B) Mars", "C) Venus", "D) Neptune"},
                2,
                "Venus has a thick greenhouse atmosphere that traps extreme heat (464°C)."
        ));
        questionList.add(new Question(
                "Which planet has the most moons in the Solar System?",
                new String[]{"A) Earth", "B) Jupiter", "C) Uranus", "D) Saturn"},
                3,
                "Saturn holds the record with 146 confirmed moons!"
        ));
        questionList.add(new Question(
                "Which gas giant has a famous storm known as the Great Red Spot?",
                new String[]{"A) Jupiter", "B) Neptune", "C) Uranus", "D) Saturn"},
                0,
                "Jupiter's Great Red Spot is a giant storm bigger than planet Earth."
        ));
        questionList.add(new Question(
                "Which planet rotates on its side with an extreme tilt?",
                new String[]{"A) Neptune", "B) Mercury", "C) Uranus", "D) Venus"},
                2,
                "Uranus rotates almost sideways at an axial tilt of 98 degrees."
        ));

        // Shuffle questions for high replayability
        Collections.shuffle(questionList);
    }

    private void displayQuestion() {
        isAnswered = false;
        Question q = questionList.get(currentQuestionIndex);

        txtQuestion.setText(q.getQuestionText());
        txtProgress.setText(String.format("Question %d of %d", currentQuestionIndex + 1, questionList.size()));
        quizProgressBar.setMax(questionList.size());
        quizProgressBar.setProgress(currentQuestionIndex + 1);

        String[] options = q.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setBackgroundColor(Color.TRANSPARENT);
            optionButtons[i].setTextColor(Color.WHITE);
            optionButtons[i].setEnabled(true);
        }

        txtFeedback.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.GONE);
    }

    private void checkAnswer(int selectedIndex) {
        if (isAnswered) return;
        isAnswered = true;

        Question q = questionList.get(currentQuestionIndex);
        int correctIndex = q.getCorrectOptionIndex();

        // Disable all buttons to prevent double-tapping
        for (Button btn : optionButtons) {
            btn.setEnabled(false);
        }

        if (selectedIndex == correctIndex) {
            score += 20;
            txtScore.setText(String.format("Score: %d", score));
            optionButtons[selectedIndex].setBackgroundColor(Color.parseColor("#2E7D32")); // Green
            txtFeedback.setText("🎉 Correct! " + q.getExplanation());
            txtFeedback.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            optionButtons[selectedIndex].setBackgroundColor(Color.parseColor("#C62828")); // Red
            optionButtons[correctIndex].setBackgroundColor(Color.parseColor("#2E7D32")); // Highlight correct in Green
            txtFeedback.setText("❌ Incorrect. " + q.getExplanation());
            txtFeedback.setTextColor(Color.parseColor("#FF5252"));
        }

        txtFeedback.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);
    }

    private void showFinalResults() {
        String badge;
        if (score >= 80) {
            badge = "🏆 Astronomy Master";
        } else if (score >= 40) {
            badge = "🚀 Space Cadet";
        } else {
            badge = "👩‍🚀 Stargazer Trainee";
        }

        new AlertDialog.Builder(this)
                .setTitle("Quiz Completed!")
                .setMessage(String.format("Your Final Score: %d/100\nTitle Earned: %s", score, badge))
                .setPositiveButton("Play Again", (dialog, which) -> {
                    score = 0;
                    currentQuestionIndex = 0;
                    txtScore.setText("Score: 0");
                    Collections.shuffle(questionList);
                    displayQuestion();
                })
                .setNegativeButton("Exit", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }
}