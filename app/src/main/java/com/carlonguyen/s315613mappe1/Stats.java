package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Stats extends AppCompatActivity {

    private int statsMathPoints;
    private int statsMathFails;
    private TextView stats_points;
    private TextView stats_fails;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

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

        stats_points = (TextView)findViewById(R.id.stats_points);
        stats_points.setText("Riktig: " + statsMathPoints);
        stats_fails = (TextView)findViewById(R.id.stats_fails);
        stats_fails.setText("Feil: " + statsMathFails);
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
}
