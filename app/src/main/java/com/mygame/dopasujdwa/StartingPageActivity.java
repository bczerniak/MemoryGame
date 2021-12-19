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
        animation();
    }

    public void goToGame(View view){
        startActivity(new Intent(StartingPageActivity.this, MainActivity.class));
    }

    public void animation() {
        TextView title = findViewById(R.id.textView);
        title.animate().translationZ(0).rotationX(-1080).setDuration(9000);
    }
}