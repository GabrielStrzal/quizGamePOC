package com.strzal.quiz.controller;

import com.strzal.quiz.entities.MatchStats;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.json.QuestionJsonParser;
import lombok.Getter;

import java.util.Collections;

public class LevelController {

    MatchStats matchStats;
    QuestionJsonParser parser = new QuestionJsonParser();
    @Getter
    int numberOfCorrectAnswers = 0;

    public LevelController(String questionJson){
        matchStats = new MatchStats();
        matchStats.setQuestionsLeft(parser.getQuestionsFromJson(questionJson));
        Collections.shuffle(matchStats.getQuestionsLeft());
        matchStats.setNumberOfQuestions(matchStats.getQuestionsLeft().size());
    }

    public boolean hasMoreQuestionsLeft(){
        return matchStats.getQuestionsLeft().size() > 0;
    }

    public Question getNextQuestion(){
        return matchStats.getQuestionsLeft().remove(0);
    }

    public void increaseCorrectAnswers(){
        numberOfCorrectAnswers++;
    }

    public int getNumberOfTotalQuestions(){
        return matchStats.getNumberOfQuestions();
    }


}
