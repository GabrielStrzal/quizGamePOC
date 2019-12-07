package com.strzal.quiz.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.google.gson.Gson;
import com.strzal.quiz.entities.Question;

import java.util.List;

public class QuestionJsonParser {

    public List<Question> getQuestionsFromJson(String jsonPath){

        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal(jsonPath));

        Gson gson = new Gson();
        InternalQuestions intQuestions = gson.fromJson(base.toJson(JsonWriter.OutputType.json), InternalQuestions.class);

        return intQuestions.questions;
    }

    class InternalQuestions{
        public List<Question> questions;
    }
}
