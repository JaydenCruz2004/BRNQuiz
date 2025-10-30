package com.exam.brnquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ResultsFragment extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_TOTAL = "total";

    public static ResultsFragment newInstance(int score, int total) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_TOTAL, total);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        TextView resultTitle = view.findViewById(R.id.resultTitle);
        TextView resultScore = view.findViewById(R.id.resultScore);
        Button retryButton = view.findViewById(R.id.retryButton);

        int score = 0;
        int total = 0;
        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
            total = getArguments().getInt(ARG_TOTAL);
        }

        resultScore.setText("You scored " + score + " out of " + total);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart the quiz
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
