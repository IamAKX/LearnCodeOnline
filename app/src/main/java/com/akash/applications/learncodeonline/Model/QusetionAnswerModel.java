package com.akash.applications.learncodeonline.Model;

/**
 * Created by Akash on 14/6/18.
 */

public class QusetionAnswerModel {
    String question,answer;

    public QusetionAnswerModel(String question, String answer, String videoID) {
        this.question = question;
        this.answer = answer;

    }

    public QusetionAnswerModel() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
