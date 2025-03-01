package com.learner.model.questions;

public enum QuestionType {


    MULTIPLE_CHOICE("multiple choice"),
    FITB("fill in the blank"),
    MATCHING("matching"),
    SEQUENCING("sequencing");

    private final String label;

    /**
     * 
     * @param label
     */
    QuestionType(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     */
    public String getLabel() {
        return label;
    }
    
}

