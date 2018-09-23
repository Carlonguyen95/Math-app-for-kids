package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Settings extends AppCompatActivity {

    private int difficulty;
    private RadioGroup radioGroup;
    private static String LANGUAGE = "en";
    private final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_settings);

        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup = (RadioGroup)findViewById(R.id.btnrg_settings);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(checkedId);
                int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);

                switch(checkedId){
                    case R.id.btn_easymode:
                        difficulty = 4;
                        savePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        break;
                    case R.id.btn_mediummode:
                        difficulty = 9;
                        savePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        break;
                    case R.id.btn_hardmode:
                        difficulty = 24;
                        savePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        break;
                }
            }
        });

        final Button changeLanguageBtn = (Button)findViewById(R.id.changeBtn_DE);
        changeLanguageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LANGUAGE = getResources().getString(R.string.languageDE);
                changeLanguage(LANGUAGE);
            }
        });

        final Button changeLanguageBtn2 = (Button)findViewById(R.id.changeBtn_NO);
        changeLanguageBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LANGUAGE = getResources().getString(R.string.languageNO);
                changeLanguage(LANGUAGE);
            }
        });

        loadPreferences();
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

    /**
     * Denne metoden lagrer antall spørsmål som spilleren har valgt til SharedPreferences
     * Denne variabelen blir brukt videre til å lage et spill
     */
    private void savePreferences(String key, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("My_Shared_Pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();

        getSharedPreferences("DifficultyLevel", MODE_PRIVATE)
                .edit()
                .putInt("DifficultyLevel", difficulty)
                .apply();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("My_Shared_Pref", MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton)radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }

    /**
     * Denne metoden lagrer språket til SharedPreferences
     */
    public void saveLanguage(String language){
        getSharedPreferences("ChosenLanguage", MODE_PRIVATE)
                .edit()
                .putString("ChosenLanguage", language)
                .apply();
    }

    /**
     * Denne metoden henter riktig språk
     */
    public void loadLanguage() {
        Context context = getApplicationContext();
        Locale locale = new Locale(LANGUAGE);
        saveLanguage(LANGUAGE);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * Denne metoden bytter språk
     */
    public void changeLanguage(String lan){
        LANGUAGE = lan;
        Context context = getApplicationContext();
        Locale locale = new Locale(LANGUAGE);
        saveLanguage(LANGUAGE);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
        recreate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("Language", LANGUAGE);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        LANGUAGE = savedInstanceState.getString("Language");
    }
}
