package com.shahriar.planetexplorer.model;

public class Question {
    private final String questionText;
    private final String[] options;
    private final int correctOptionIndex;
    private final String explanation;

    public Question(String questionText, String[] options, int correctOptionIndex, String explanation) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.explanation = explanation;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public String getExplanation() { return explanation; }
}