package com.strzal.quiz.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.strzal.quiz.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionJsonParser {

    public List<Question> getQuestionsFromJson(String jsonPath){


        Json pjson = new Json();
        ArrayList<Question> questionList = pjson.fromJson(ArrayList.class, Question.class, Gdx.files.internal(jsonPath).readString());
//        InternalQuestions person2 = pjson.fromJson( InternalQuestions.class, Gdx.files.internal(jsonPath).readString());


        return questionList;
    }

}
