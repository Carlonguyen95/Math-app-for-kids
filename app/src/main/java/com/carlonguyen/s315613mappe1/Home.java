package com.carlonguyen.s315613mappe1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageButton gameStartBtn = (ImageButton)findViewById(R.id.gameStartBtn);
        gameStartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openNewGame();
            }
        });

        final ImageButton gameSettingsBtn = (ImageButton)findViewById(R.id.gameSettingsBtn);
        gameSettingsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSettingActivity();
            }
        });
    }

    public void openNewGame(){
        Toast.makeText(Home.this, "New game started!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }

    public void openSettingActivity(){
        Toast.makeText(Home.this, "Settings clicked!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
