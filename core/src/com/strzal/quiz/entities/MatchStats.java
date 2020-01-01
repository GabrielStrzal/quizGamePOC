package com.strzal.quiz.entities;

import java.util.List;

public class MatchStats {

    List<Question> questionsLeft;
    int numberOfQuestions;

    public List<Question> getQuestionsLeft() {
        return questionsLeft;
    }

    public void setQuestionsLeft(List<Question> questionsLeft) {
        this.questionsLeft = questionsLeft;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
