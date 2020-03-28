package com.strzal.quiz.entities;

public class Answer {

    private String answerType;
    public String text;
    private String audio;
    private String image;

    public Answer(String answerType, String text, String audio, String image) {
        this.answerType = answerType;
        this.text = text;
        this.audio = audio;
        this.image = image;
    }

    public Answer() {
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
