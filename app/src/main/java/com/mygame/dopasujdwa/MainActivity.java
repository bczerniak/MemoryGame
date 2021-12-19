package com.mygame.dopasujdwa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, Integer> buttonImagePairs = new HashMap<>();
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<ImageButton> buttons = new ArrayList<>();

    long startTimer;

    ImageButton firstSelectedButton;
    ImageButton secondSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeImages();
        initializeButtons();
        startGame();
    }

    public void startGame() {
        startTimer = System.currentTimeMillis();

        Collections.shuffle(images);

        buttonImagePairs.clear();
        pairImagesWithButtons();
    }

    public void initializeImages() {
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
    }

    public void initializeButtons() {
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
    }

    public void pairImagesWithButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            buttonImagePairs.put(button.getId(), images.get(i));
        }
    }

    public void onMemoryButtonClick (View view) {

        ImageButton selectedButton = (ImageButton) view;    //castowanie/unboxing
        int buttonId = selectedButton.getId();

        if (buttonImagePairs.containsKey(buttonId) == false) {
            return;
        }

        int buttonImageId = buttonImagePairs.get(buttonId);

        if (firstSelectedButton == null) {
            firstSelectedButton = selectedButton;
            firstSelectedButton.setBackgroundResource(buttonImageId);
            return;
        }

        if (secondSelectedButton == null) {
            secondSelectedButton = selectedButton;
            secondSelectedButton.setBackgroundResource(buttonImageId);
        }

        if (firstSelectedButton.getBackground().getConstantState() != secondSelectedButton.getBackground().getConstantState()) {    //poczytać o getConstantState()
            setClickable(false);
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                firstSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                secondSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                firstSelectedButton = null;
                secondSelectedButton = null;
                setClickable(true);
            }, 700);
        } else {
            buttonImagePairs.remove(firstSelectedButton.getId());
            buttonImagePairs.remove(secondSelectedButton.getId());
            firstSelectedButton = null;
            secondSelectedButton = null;
        }

        if (buttonImagePairs.isEmpty()) {
            setClickable(false);

            Toast toast = Toast.makeText(this, "Gratulacje, zwyciężyłeś!!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0,0);
            toast.show();

            TextView showTime = findViewById(R.id.showTime);
            showTime.setText("Twój czas wyniósł:  " + (System.currentTimeMillis() - startTimer)/1000 + " sekund");

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                findViewById(R.id.playAgainButton).setVisibility(View.VISIBLE);
                findViewById(R.id.showTime).setVisibility(View.VISIBLE);
            }, 2000);
        }
    }

    public void playAgain (View view) {
        setInitialButtonsBackground();
        startGame();
        setClickable(true);
        findViewById(R.id.playAgainButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.showTime).setVisibility(View.INVISIBLE);
    }

    public void setClickable (boolean setClickable) {
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setClickable(setClickable);
        }
    }

    private void setInitialButtonsBackground() {
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setBackgroundResource(R.drawable.znakzapytania);
        }
    }
}