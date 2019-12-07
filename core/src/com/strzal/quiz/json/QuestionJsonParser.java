package com.strzal.quiz.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.strzal.quiz.entities.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionJsonParser {

    //Will be used for different question, example white belt, yellow belt questions.
    public List<Question> getQuestionsFromJson(String jsonPath){

        List<Question> questions = new ArrayList<>();

        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(jsonPath));

        for (JsonValue q : base.get("questions"))
        {
            Question question = new Question();
            question.setQuestionId(q.getInt("questionID"));
            question.setQuestionText(q.getString("questionString"));
            question.setCorrectAnswer(q.getInt("correctAnswer"));
            int counter = 1;
            HashMap<Integer, String> answers = new HashMap<Integer, String>();
            for (JsonValue answ : q.get("answers")) {
                answers.put(counter, answ.getString("answer"));
                counter++;
            }
            question.setAnswers(answers);
            questions.add(question);
        }
        return questions;
    }
}
