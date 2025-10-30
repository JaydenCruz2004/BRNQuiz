package com.exam.brnquiz;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnAnswerListener {
    private int score = 0; //track score here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        // Load the fragments into their containers
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.quizFrag, new QuestionFragment());
        ft.replace(R.id.scoreFrag, new ScoreFragment());
        ft.commit();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainQuiz), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // score
    @Override
    public void onCorrectAnswer() {
        score++;
        Toast.makeText(this, "Correct answer received!", Toast.LENGTH_SHORT).show();

        ScoreFragment scoreFragment = (ScoreFragment) getSupportFragmentManager()
                .findFragmentById(R.id.scoreFrag);

        if (scoreFragment != null) {
            scoreFragment.incrementScore();
        }
    }
    public int getFinalScore() {
        return score;
    }
}
