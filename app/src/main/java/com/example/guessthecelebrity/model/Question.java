package com.example.guessthecelebrity.model;

import java.util.List;

public class Question {
    private Celebrity truthCelebrity;
    private List<Celebrity> celebrityOptions;

    public Celebrity getTruthCelebrity() {
        return truthCelebrity;
    }

    public void setTruthCelebrity(Celebrity truthCelebrity) {
        this.truthCelebrity = truthCelebrity;
    }

    public List<Celebrity> getCelebrityOptions() {
        return celebrityOptions;
    }

    public void setCelebrityOptions(List<Celebrity> celebrityOptions) {
        this.celebrityOptions = celebrityOptions;
    }

    public Question(Celebrity truthCelebrity, List<Celebrity> celebrityOptions) {
        this.truthCelebrity = truthCelebrity;
        this.celebrityOptions = celebrityOptions;
    }
}
