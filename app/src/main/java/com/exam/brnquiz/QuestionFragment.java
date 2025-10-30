package com.exam.brnquiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    private TextView questionText;
    private ListView answersList;
    private Button submitButton;

    private OnAnswerListener answerListener;
    private Question question;

    ArrayList<Question> qs;

    private BRNViewModel myViewModel;


    // Question data
    private String[] questions = {
            "What is the capital of Colombia?",
            "What is the capital of France?",
            "What is 6 + 7?"
    };

    private String[][] options = {
            {"Medellin", "Madrid", "Paris", "Bogota"},
            {"Paris", "London", "Berlin", "Madrid"},
            {"10", "11", "12", "13"}
    };

    private int[] correctAnswers = {3, 0, 3};
    private int currentQuestion = 0;
    private int selectedPosition = -1;

    private ArrayAdapter<String> adapter;

    //  Attach listener to the Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerListener) {
            answerListener = (OnAnswerListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnAnswerListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        qs = new ArrayList<>();
        qs.add(new Question("What is the capital of Colombia?", "Medelin", "Madrid", "Paris"
                , "Bogota", 3));
        qs.add(new Question("What is the capital of France?", "Paris", "London", "Berlin"
                , "Madrid", 0));
        qs.add(new Question("What is 6 + 7", "10", "11", "12", "13", 3));


        questionText = view.findViewById(R.id.questionText);
        answersList = view.findViewById(R.id.answersList);
        submitButton = view.findViewById(R.id.submitButton);


        //trying to implement view model/observer

        myViewModel = new ViewModelProvider(this).get(BRNViewModel.class);

        myViewModel.getQs().observe(getViewLifecycleOwner(), question -> {
            qs.clear();
            //
            // qs.addAll();
            adapter.notifyDataSetChanged();
        });


        loadQuestion();

        answersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                // reset all backgrounds
                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(0x00000000); // transparent
                }

                // highlight the selected one (light blue)
                view.setBackgroundColor(0xFFBBDEFB);

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == -1) {
                    Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedPosition == correctAnswers[currentQuestion]) {
                    Toast.makeText(requireContext(), "Yay! +1 Point", Toast.LENGTH_SHORT).show();

                    if (answerListener != null) {
                        answerListener.onCorrectAnswer();
                    }

                } else {
                    Toast.makeText(requireContext(), "Wrong! Use your BRN(AI)", Toast.LENGTH_SHORT).show();
                }

                currentQuestion++;
                if (currentQuestion < questions.length) {
                    selectedPosition = -1;
                    loadQuestion();
                } else {
                    Toast.makeText(requireContext(), "Quiz finished!", Toast.LENGTH_LONG).show();
                    submitButton.setEnabled(false);
                    int total = questions.length;
                    int finalScore = ((QuizActivity) requireActivity()).getFinalScore(); // optional if you track score

                    ResultsFragment resultsFragment = ResultsFragment.newInstance(finalScore, total);

                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.quizFrag, resultsFragment)
                            .commit();
                }
            }
        });

        return view;
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1, // plain text list
                options[currentQuestion]
        );
        answersList.setAdapter(adapter);
    }

    public interface OnAnswerListener {
        void onCorrectAnswer();
    }
}
