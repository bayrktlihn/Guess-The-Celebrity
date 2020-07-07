package com.example.guessthecelebrity.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionManager {

    private List<Question> questions;
    private List<Celebrity> celebrityData;
    private List<Celebrity> tempCelebrityData;


    public QuestionManager() {
        loadCelebrityData();
        Collections.shuffle(celebrityData);
        tempCelebrityData = new ArrayList<>(celebrityData);
        prepareQuestions();
    }

    public void prepareQuestions() {
        questions = new ArrayList<>();
        for (Celebrity celebrity : celebrityData) {
            Question question = new Question(celebrity, prepareOptions(celebrity));
            questions.add(question);
        }
    }

    private List<Celebrity> prepareOptions(Celebrity celebrity) {
        List<Celebrity> celebrityOption = new ArrayList<Celebrity>();

        celebrityOption.add(celebrity);

        Collections.shuffle(tempCelebrityData);

        for (int i = 0; i < tempCelebrityData.size(); i++) {
            Celebrity currentCelebrity = tempCelebrityData.get(i);

            String firstName = currentCelebrity.getFirstName();
            String lastName = currentCelebrity.getLastName();

            if (firstName.equals(celebrity.getFirstName()) && lastName.equals(celebrity.getLastName())) {
                continue;
            }

            celebrityOption.add(currentCelebrity);

            if (celebrityOption.size() >= 4)
                break;

        }

        Collections.shuffle(celebrityOption);
        return celebrityOption;
    }

    private void loadCelebrityData() {
        celebrityData = new ArrayList<>();
        celebrityData.add(new Celebrity("Acun", "Ilıcalı", "https://i.sozcu.com.tr/wp-content/uploads/2019/12/20/iecrop/images-30_1_1_1576853767-400x400.jpg"));
        celebrityData.add(new Celebrity("Şahan", "Gökbakar", "https://pbs.twimg.com/profile_images/969712730197450752/vpY7J1Sb_400x400.jpg"));
        celebrityData.add(new Celebrity("Cem", "Yılmaz", "https://pbs.twimg.com/profile_images/1122668139366502401/rQawAdUj_400x400.jpg"));
        celebrityData.add(new Celebrity("Serenay", "Sarıkaya", "https://i.sozcu.com.tr/wp-content/uploads/2020/04/25/iecrop/cem-serenay-instagram_1_1_1587817709-400x400.jpg"));
        celebrityData.add(new Celebrity("Şükran", "Ovalı", "https://i.sozcu.com.tr/wp-content/uploads/2018/11/iecrop/sukran-kapak_1_1_1543392591-400x400.jpg"));

    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public boolean checkTheOption(Celebrity selectedCelebrityOption, int questionIndex) {
        Question question = questions.get(questionIndex);
        if (selectedCelebrityOption.getFirstName().equals(question.getTruthCelebrity().getFirstName()) && selectedCelebrityOption.getLastName().equals(question.getTruthCelebrity().getLastName()))
            return true;
        return false;
    }

    public int getQuestionSize(){
        return questions.size();
    }


}
