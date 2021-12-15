package com.mygame.dopasujdwa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, Integer> buttonImagePairs = new HashMap<>();
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<ImageButton> buttons = new ArrayList<>();

    ImageButton firstSelectedButton;
    ImageButton secondSelectedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeImages();
    }

    public void initializeImages() {
        buttonImagePairs.clear();
        buttons.clear();
        images.clear();

        images.add(R.drawable.cat);
        images.add(R.drawable.cat);
        images.add(R.drawable.dog);
        images.add(R.drawable.dog);
        images.add(R.drawable.flamingo);
        images.add(R.drawable.flamingo);
        images.add(R.drawable.sun);
        images.add(R.drawable.sun);
        images.add(R.drawable.tree);
        images.add(R.drawable.tree);
        images.add(R.drawable.wolf);
        images.add(R.drawable.wolf);

        buttons.add(findViewById(R.id.button0));
        buttons.add(findViewById(R.id.button1));
        buttons.add(findViewById(R.id.button3));
        buttons.add(findViewById(R.id.button2));
        buttons.add(findViewById(R.id.button4));
        buttons.add(findViewById(R.id.button5));
        buttons.add(findViewById(R.id.button6));
        buttons.add(findViewById(R.id.button7));
        buttons.add(findViewById(R.id.button8));
        buttons.add(findViewById(R.id.button9));
        buttons.add(findViewById(R.id.button10));
        buttons.add(findViewById(R.id.button11));

        Collections.shuffle(images);

        for (int i = 0; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            buttonImagePairs.put(button.getId(), images.get(i));
        }
    }

    public void onMemoryButtonClick (View view) {
        int buttonId = view.getId();
        int buttonImage = buttonImagePairs.get(buttonId);
        ImageButton selectedButton = findViewById(buttonId);

        if (firstSelectedButton == null) {
            firstSelectedButton = selectedButton;
            firstSelectedButton.setBackgroundResource(buttonImage);
            return;
        }
        if (secondSelectedButton == null) {
            secondSelectedButton = selectedButton;
            secondSelectedButton.setBackgroundResource(buttonImage);
        }
        if (firstSelectedButton.getBackground().getConstantState() != secondSelectedButton.getBackground().getConstantState()) {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                firstSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                secondSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                firstSelectedButton = null;
                secondSelectedButton = null;
            }, 2000);
        } else {
            buttonImagePairs.remove(firstSelectedButton.getId());
            buttonImagePairs.remove(secondSelectedButton.getId());
            firstSelectedButton = null;
            secondSelectedButton = null;
        }
        //sprawdzic czy buttonImagePairs są puste, jeśli tak to zwycięstwo
    }

    public void playAgain (View view) {
        initializeImages();
    }
}