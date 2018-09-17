package com.carlonguyen.s315613mappe1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class Stats extends AppCompatActivity {

    private int statsMathPoints;
    private int statsMathFails;
    private TextView tw1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        statsMathPoints = getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                .getInt("MATH_POINTS", 0);
        statsMathFails = getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                .getInt("MATH_FAILS", 0);

        tw1 = (TextView)findViewById(R.id.stats_tw);
        tw1.setText("Riktig: " + statsMathPoints + " | " + "Feil: " + statsMathFails);
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
