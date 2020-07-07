package com.example.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessthecelebrity.model.Celebrity;
import com.example.guessthecelebrity.model.Question;
import com.example.guessthecelebrity.model.QuestionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button aOptionButton;
    private Button bOptionButton;
    private Button cOptionButton;
    private Button dOptionButton;
    private ImageView imageView;

    private int currentQuestionIndex = 0;
    private QuestionManager questionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aOptionButton = findViewById(R.id.aOptionButton);
        bOptionButton = findViewById(R.id.bOptionButton);
        cOptionButton = findViewById(R.id.cOptionButton);
        dOptionButton = findViewById(R.id.dOptionButton);
        imageView = findViewById(R.id.imageView);

        questionManager = new QuestionManager();

        loadQuestion();


    }

    private void loadQuestion() {

        if(currentQuestionIndex >= questionManager.getQuestionSize()){
            Toast.makeText(this,"Soru Kalmadı Oyun Yeniden Başlatılıyor.", Toast.LENGTH_LONG).show();
            questionManager = new QuestionManager();
            currentQuestionIndex = 0;
            loadQuestion();
            return;
        }

        Question question = questionManager.getQuestion(currentQuestionIndex);

        aOptionButton.setText(question.getCelebrityOptions().get(0).toString());
        bOptionButton.setText(question.getCelebrityOptions().get(1).toString());
        cOptionButton.setText(question.getCelebrityOptions().get(2).toString());
        dOptionButton.setText(question.getCelebrityOptions().get(3).toString());

        new ImageDownload().execute(question.getTruthCelebrity().getImageUrl());
    }

    public void optionClick(View view) {
        Celebrity answerCelebrity = null;
        switch (view.getId()){
            case R.id.aOptionButton:
                answerCelebrity = questionManager.getQuestion(currentQuestionIndex).getCelebrityOptions().get(0);
                break;
            case R.id.bOptionButton:

                answerCelebrity = questionManager.getQuestion(currentQuestionIndex).getCelebrityOptions().get(1);
                break;
            case R.id.cOptionButton:

                answerCelebrity = questionManager.getQuestion(currentQuestionIndex).getCelebrityOptions().get(2);
                break;
            case R.id.dOptionButton:
                answerCelebrity = questionManager.getQuestion(currentQuestionIndex).getCelebrityOptions().get(3);
                break;
        }



        if(questionManager.checkTheOption(answerCelebrity,currentQuestionIndex)){
            currentQuestionIndex++;
            loadQuestion();
        } else {
            Toast.makeText(this, "Yanlış cevapladınız lütfen tekrar dene.",Toast.LENGTH_LONG).show();
        }

    }


    private class ImageDownload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlString = strings[0];
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            Bitmap result = null;

            try {
                url = new URL(urlString);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                result = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}