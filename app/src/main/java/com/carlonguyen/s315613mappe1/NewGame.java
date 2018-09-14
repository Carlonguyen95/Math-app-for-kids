package com.carlonguyen.s315613mappe1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NewGame extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear, btn_ok;
    EditText ed1;
    List<String> listMathQs;
    String randomMathQs;
    TextView tw1;
    TextView textQuestionTW;
    RadioButton rb_setting;
    int mathCounter;
    String[] mathAs;
    String mathSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Kode relatert til toolbaren
        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Henter vanskelighetsgrad
        //rb_setting = (RadioButton)findViewById(R.id.)

        // Henter mattespørsmålene fra arrays.xml og putter dem i ArrayList
        listMathQs = new ArrayList();
        listMathQs = Arrays.asList(getResources().getStringArray(R.array.stringMathQs));
        mathCounter = new Random().nextInt(listMathQs.size()-1);

        textQuestionTW = (TextView)findViewById(R.id.textQuestion);
        tw1 = (TextView)findViewById(R.id.mathQuestionTextView);
        tw1.setText(listMathQs.get(mathCounter));

        // Henter knapper til kalkulator
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        ed1 = (EditText)findViewById(R.id.edText1);
        ed1.setInputType(InputType.TYPE_NULL);
        btn_ok = (Button)findViewById(R.id.btn_ok);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "1");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "2");
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "3");
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "4");
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "5");
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "6");
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "7");
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "8");
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + "9");
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((ed1.getText().toString().equals(""))){
                    return;
                }else{
                    ed1.setText(ed1.getText() + "0");
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.getText().clear();
            }
        });

        // Denne knappen sjekker svaret som blir avgitt av spilleren er riktig eller galt ift mattespørsmålet
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMathQuestion();
            }
        });

    }

    public void newMathQuestion(){
        mathCounter = new Random().nextInt(listMathQs.size()-1);
        if(listMathQs.size() > 0) {
            tw1.setText(listMathQs.get(mathCounter));
            ed1.getText().clear();
        }else{
            ed1.getText().clear();
            textQuestionTW.setText(ed1.getText() + "Finito!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.game_navbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        if(id == R.id.toolbar_settings_icon){
            Toast.makeText(NewGame.this, "Settings clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
