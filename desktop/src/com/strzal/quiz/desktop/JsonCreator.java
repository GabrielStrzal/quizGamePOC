package com.strzal.quiz.desktop;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.strzal.quiz.entities.Question;
import com.strzal.quiz.json.InternalQuestions;

import java.util.ArrayList;
import java.util.List;

public class JsonCreator {

    public static void main(String[] args) {


        Question question = new Question();
        question.setQuestionID(1);
        question.setCorrectAnswer(1);
        question.setQuestionString("This is the question");
        ArrayList<String> questionsss = new ArrayList<>();
        questionsss.add("Answer 1");
        questionsss.add("Answer 2");
        questionsss.add("Answer 3");
        questionsss.add("Answer 4");
        question.setAnswers(questionsss);



        Question question2 = new Question();
        question2.setQuestionID(2);
        question2.setCorrectAnswer(1);
        question2.setQuestionString("This is the question2");
        ArrayList<String> questionsss2 = new ArrayList<>();
        questionsss2.add("Answer 1");
        questionsss2.add("Answer 2");
        questionsss2.add("Answer 3");
        questionsss2.add("Answer 4");
        question2.setAnswers(questionsss2);

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        questions.add(question2);

        InternalQuestions internalQuestions = new InternalQuestions();
        internalQuestions.questions = questions;

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.minimal);
        String text = json.toJson(internalQuestions, Object.class);
        System.out.println(json.prettyPrint(text));

//        System.out.println(json.toJson(internalQuestions));





//        //
//        Json json = new Json();
//        String text = Gdx.files.internal("questions/01_white_belt_en-ca.json").readString();
//        InternalQuestions person2 = json.fromJson(InternalQuestions.class, text);
//
//
//        System.out.println(person2.questions.toString());
    }



}
