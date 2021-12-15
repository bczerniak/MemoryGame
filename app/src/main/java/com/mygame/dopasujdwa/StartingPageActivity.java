package com.mygame.dopasujdwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);
    }

    public void goToGame(View v){
        startActivity(new Intent(StartingPageActivity.this, MainActivity.class));
    }
}