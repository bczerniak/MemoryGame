package com.mygame.dopasujdwa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
    HashMap<Integer, Integer> buttonImagePairs = new HashMap<>();
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<ImageButton> buttons = new ArrayList<>();

    ImageButton firstSelectedButton;
    ImageButton secondSelectedButton;

//    private boolean canClickMemoryButtons = false;
//
//    @Bindable
//    public Boolean getMemoryButtonsClickable() {
//        return  canClickMemoryButtons;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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

//        Collections.shuffle(images);

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
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                firstSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                secondSelectedButton.setBackgroundResource(R.drawable.znakzapytania);
                firstSelectedButton = null;
                secondSelectedButton = null;
            }, 500);
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