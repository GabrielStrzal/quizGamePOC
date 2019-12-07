package com.strzal.quiz.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class MatchStats {

    List<Question> questionsLeft;
    int numberOfQuestions;

}
