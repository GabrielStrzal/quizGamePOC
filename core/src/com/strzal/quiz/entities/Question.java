package com.strzal.quiz.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Question {
    private int questionID;
    private String questionString;
    private List<String> answers;
    private int correctAnswer;
}
