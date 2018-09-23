package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Stats extends AppCompatActivity {

    private int statsMathPoints;
    private int statsMathFails;

    private TextView stats_points;
    private TextView stats_fails;
    private Button stats_clear_btn;
    private ImageButton stats_tips;

    private static String Language;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Language = getSharedPreferences("ChosenLanguage", MODE_PRIVATE)
                .getString("ChosenLanguage", "en");
        loadLanguage();
        setContentView(R.layout.activity_stats);

        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stats_clear_btn = (Button)findViewById(R.id.btn_clear_stats);
        stats_clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStats();
            }
        });

        statsMathPoints = getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                .getInt("MATH_POINTS", 0);
        statsMathFails = getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                .getInt("MATH_FAILS", 0);

        stats_tips = (ImageButton)findViewById(R.id.stats_tips);

        /**
         * Her blir et bilde displayet på aktiviteten i "landscape"-mode utifra
         * resultatet spilleren får ved et fullført spill
         */
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if(statsMathFails > statsMathPoints){
                stats_tips.setImageDrawable(getResources().getDrawable(R.drawable.stats_img_pepe1));
                stats_tips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Stats.this, getResources().getString(R.string.stats_fail_tips), Toast.LENGTH_SHORT).show();
                    }
                });
            }else if(statsMathPoints > statsMathFails){
                stats_tips.setImageDrawable(getResources().getDrawable(R.drawable.stats_img_pepe));
                stats_tips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Stats.this, getResources().getString(R.string.stats_tips), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                stats_tips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Stats.this, getResources().getString(R.string.stats_default_text), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


        stats_points = (TextView)findViewById(R.id.stats_points);
        stats_points.setText(getResources().getString(R.string.stats_points) + " " + statsMathPoints);
        stats_fails = (TextView)findViewById(R.id.stats_fails);
        stats_fails.setText(getResources().getString(R.string.stats_fails) + " " + statsMathFails);
    }

    public void loadLanguage() {
        Context context = getApplicationContext();
        Locale locale = new Locale(Language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
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

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("MATH_POINTS", statsMathPoints);
        outState.putInt("MATH_FAILS", statsMathFails);
    }

    protected void onRestoreInstanceState(Bundle onRestoreInstanceState){
        super.onRestoreInstanceState(onRestoreInstanceState);

        statsMathPoints = onRestoreInstanceState.getInt("MATH_POINTS");
        statsMathFails = onRestoreInstanceState.getInt("MATH_FAILS");
    }

    /**
     * Denne metoden nullstiller statistikken
     */
    public void clearStats(){
        statsMathPoints = 0;
        statsMathFails = 0;
        stats_points.setText(getResources().getString(R.string.stats_points) + " " + statsMathPoints);
        stats_fails.setText(getResources().getString(R.string.stats_fails) + " " + statsMathFails);

        getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                .edit()
                .putInt("MATH_POINTS", statsMathPoints)
                .putInt("MATH_FAILS", statsMathFails)
                .apply();
    }
}
