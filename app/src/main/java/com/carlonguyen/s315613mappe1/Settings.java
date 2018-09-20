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

public class Settings extends AppCompatActivity {

    private RadioGroup radioGroup;
    private int difficulty;
    private String language;
    private Locale locale;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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

        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button changeLanguageBtn = (Button)findViewById(R.id.changeBtn_DE);
        changeLanguageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                language = "de";
                Context context = getApplicationContext();
                locale = new Locale(language);
                saveLocale(language);
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
                language = "en";
                Context context = getApplicationContext();
                locale = new Locale(language);
                saveLocale(language);
                Locale.setDefault(locale);
                Resources res = context.getResources();
                Configuration config = new Configuration(res.getConfiguration());
                config.locale = locale;
                res.updateConfiguration(config, res.getDisplayMetrics());
                recreate();
            }
        });

        loadLocale();
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

    public void saveLocale(String language){
        getSharedPreferences("ChosenLanguage", MODE_PRIVATE)
                .edit()
                .putString("ChosenLanguage", language)
                .apply();
    }

    public void loadLocale(){
        language = getSharedPreferences("ChosenLanguage", MODE_PRIVATE)
                .getString("ChosenLanguage", "");
        changeLocale(language);
        System.out.println("Load locale: " + language);
    }

    public void changeLocale(String lan){
        if(lan.equalsIgnoreCase("")){
            return;
        }else {
            language = lan;
            Context context = getApplicationContext();
            locale = new Locale(language);
            Locale.setDefault(locale);
            Resources res = context.getResources();
            Configuration config = new Configuration(res.getConfiguration());
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("savedRadioIndex", difficulty);
        outState.putString("ChosenLanguage", language);
        System.out.println("onSave: " + language);
    }

    protected void onRestoreInstanceState(Bundle savedInstaceState){
        super.onRestoreInstanceState(savedInstaceState);
        difficulty = savedInstaceState.getInt("savedRadioIndex");
        language = savedInstaceState.getString("ChosenLanguage");
        System.out.println("onRestore: " + language);
    }
}
