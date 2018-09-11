package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button changeLanguageBtn = (Button)findViewById(R.id.changeBtn_DE);
        changeLanguageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String language = "de";
                Context context = getApplicationContext();
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Resources res = context.getResources();
                Configuration config = new Configuration(res.getConfiguration());
                config.locale = locale;
                res.updateConfiguration(config, res.getDisplayMetrics());
                recreate();
            }
        });

        final Button changeLanguageBtn2 = (Button)findViewById(R.id.changeBtn_NO);
        changeLanguageBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String language = "en";
                Context context = getApplicationContext();
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Resources res = context.getResources();
                Configuration config = new Configuration(res.getConfiguration());
                config.locale = locale;
                res.updateConfiguration(config, res.getDisplayMetrics());
                recreate();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
