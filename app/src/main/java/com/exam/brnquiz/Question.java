package com.exam.brnquiz;

public class Question {
    String question, m1, m2, m3, m4;
    int correctAnswerPos;

    public Question(String question, String m1, String m2, String m3, String m4, int correctAnswerPos) {
        this.question = question;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
        this.correctAnswerPos = correctAnswerPos;
    }

    public String getQs() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getM4() {
        return m4;
    }

    public void setM4(String m4) {
        this.m4 = m4;
    }

    public int getCorrectAnswerPos() {
        return correctAnswerPos;
    }

    public void setCorrectAnswerPos(int correctAnswerPos) {
        this.correctAnswerPos = correctAnswerPos;
    }
}

