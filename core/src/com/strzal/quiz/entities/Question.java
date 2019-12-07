package com.strzal.quiz.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Question {
    private int questionId;
    private String questionText;
    private HashMap<Integer, String> answers;
    private int correctAnswer;
}
