package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NewGame extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear, btn_ok;
    private EditText ed1;
    private List<String> listMathQs;
    private List<String> listMathAs;
    private TextView tw1;
    private TextView textQuestionTW;
    private int mathCounter;
    private int difficulty = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Kode relatert til toolbaren
        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Henter mattespørsmålene og svar fra arrays.xml og putter dem i ArrayList
        listMathQs = new ArrayList();
        listMathAs = new ArrayList();

        listMathQs.addAll(Arrays.asList(getResources().getStringArray(R.array.stringMathQs)));
        listMathAs.addAll(Arrays.asList(getResources().getStringArray(R.array.stringMathAs)));

        //listMathAs = Arrays.asList(getResources().getStringArray(R.array.stringMathAs));
        //listMathQs = Arrays.asList(getResources().getStringArray(R.array.stringMathQs));

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

        // Henter vanskelighetsgrad
        difficulty = getSharedPreferences("DifficultyLevel", MODE_PRIVATE)
                    .getInt("DifficultyLevel", 4);
        mathCounter = new Random().nextInt(difficulty);
    }

    public void newMathQuestion(){
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Strl på listen er: " + listMathQs.size());
        int mathCounter1 = new Random().nextInt(difficulty);

        if(listMathQs.size() > 0) {
            if(ed1.getText().toString().equals(listMathAs.get(mathCounter))){
                tw1.setText(listMathQs.get(mathCounter1));
                ed1.getText().clear();

                System.out.println("spm: " + listMathQs.get(mathCounter));
                System.out.println("svar: " + listMathAs.get(mathCounter));
                System.out.println("mathcounter: " + mathCounter);
                System.out.println("mathcounter1: " + mathCounter1);

                listMathQs.remove(mathCounter);
                mathCounter = mathCounter1;

            }else{
                return;
            }
        }else{
            ed1.getText().clear();
            textQuestionTW.setText(ed1.getText() + "Finito!");
        }
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
