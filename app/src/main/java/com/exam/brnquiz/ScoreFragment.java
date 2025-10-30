package com.exam.brnquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ScoreFragment extends Fragment {

    private TextView scoreNumber;
    private int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        scoreNumber = view.findViewById(R.id.score_number);
        updateScoreDisplay();
        return view;
    }

    public void incrementScore() {
        score++;
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        if (scoreNumber != null) {
            scoreNumber.setText(String.valueOf(score));
        }
    }
}
