package com.carlonguyen.s315613mappe1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class NewGame extends AppCompatActivity {

    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_clear, btn_ok;
    private EditText ed1;
    private String arrayMathAs[];
    private String arrayMathQs[];
    private List<Integer> listRndMath;
    private TextView tw1;
    private TextView textQuestionTW;
    private int difficulty;
    private int mathPoints = 1;
    private int mathFails = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Kode relatert til toolbaren
        Toolbar gameToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(gameToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Henter vanskelighetsgrad
        difficulty = getSharedPreferences("DifficultyLevel", MODE_PRIVATE)
                .getInt("DifficultyLevel", 4);

        // Henter mattespørsmål og svar
        arrayMathQs = new String[25];
        arrayMathAs = new String[25];
        arrayMathQs = getResources().getStringArray(R.array.stringMathQs);
        arrayMathAs = getResources().getStringArray(R.array.stringMathAs);
        listRndMath = new ArrayList<>();

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

        // Denne knappen sjekker om svaret er riktig og displayer nytt spm
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMathQuestion();
            }
        });

        if(savedInstanceState != null) {
            difficulty = savedInstanceState.getInt("INDEX_COUNTER", difficulty);
            mathPoints = savedInstanceState.getInt("MATH_POINTS", 0);
            mathFails = savedInstanceState.getInt("MATH_FAILS", mathFails);
            listRndMath = savedInstanceState.getIntegerArrayList("ArrayList");

            textQuestionTW = (TextView)findViewById(R.id.textQuestion);
            tw1 = (TextView)findViewById(R.id.mathQuestionTextView);
            tw1.setText(arrayMathQs[(listRndMath.get(difficulty))]);
        }else{
            newGame();
        }
    }

    public void newGame(){
        if(listRndMath.size() == 0){
            // Fyller hjelpeliste med tall fra n til antall spm(fra settings) og shuffler listen
            for(int i = 0; i <= difficulty; i++){
                listRndMath.add(i);
            }
            Collections.shuffle(listRndMath);

            // Displayer spm på skjermen
            textQuestionTW = (TextView)findViewById(R.id.textQuestion);
            tw1 = (TextView)findViewById(R.id.mathQuestionTextView);
            tw1.setText(arrayMathQs[(listRndMath.get(difficulty))]);
        }else{
            // Displayer spm på skjermen
            textQuestionTW = (TextView)findViewById(R.id.textQuestion);
            tw1 = (TextView)findViewById(R.id.mathQuestionTextView);
            tw1.setText(arrayMathQs[(listRndMath.get(difficulty))]);
        }
    }

    public void newMathQuestion(){
        if(difficulty > 0) {
            if(ed1.getText().toString().equals(arrayMathAs[(listRndMath.get(difficulty))])){
                difficulty--;
                mathPoints++;
            }else{
                difficulty--;
                mathFails++;
                tw1.setText(arrayMathQs[(listRndMath.get(difficulty))]);
                Toast.makeText(this, getResources().getString(R.string.wrongAnswer), Toast.LENGTH_SHORT).show();
            }
            tw1.setText(arrayMathQs[(listRndMath.get(difficulty))]);
            ed1.getText().clear();
        }else{
            dialogEndGame();
            ed1.getText().clear();

            getSharedPreferences("MATH_STATS", MODE_PRIVATE)
                    .edit()
                    .putInt("MATH_POINTS", mathPoints)
                    .putInt("MATH_FAILS", mathFails)
                    .apply();
        }
    }

    public void dialogEndGame(){
        AlertDialog.Builder box = new AlertDialog.Builder(NewGame.this);
        box.setMessage(getResources().getString(R.string.finishText));

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        recreate();
                        Toast.makeText(NewGame.this, getResources().getString(R.string.newGameStarted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        onBackPressed();
                        break;
                }
            }
        };
        box.setPositiveButton(getResources().getString(R.string.dialogYes), dialogClickListener);
        box.setNegativeButton(getResources().getString(R.string.dialogNo), dialogClickListener);
        box.setCancelable(false);
        AlertDialog dialog = box.create();
        dialog.show();
    }

    public void dialogQuitGame(){
        AlertDialog.Builder box = new AlertDialog.Builder(NewGame.this);
        box.setMessage(getResources().getString(R.string.quitGame));

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        onBackPressed();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        box.setPositiveButton(getResources().getString(R.string.dialogYes), dialogClickListener);
        box.setNegativeButton(getResources().getString(R.string.dialogNo), dialogClickListener);
        box.setCancelable(false);
        AlertDialog dialog = box.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                dialogQuitGame();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        System.out.println("ONSAVE");
        System.out.println(difficulty);
        System.out.println(mathPoints);
        System.out.println(mathFails);
        System.out.println(listRndMath.size());
        outState.putInt("INDEX_COUNTER", difficulty);
        outState.putInt("MATH_POINTS", mathPoints);
        outState.putInt("MATH_FAILS", mathFails);
        outState.putIntegerArrayList("ArrayList", (ArrayList<Integer>) listRndMath);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        difficulty = savedInstanceState.getInt("INDEX_COUNTER", difficulty);
        mathPoints = savedInstanceState.getInt("MATH_POINTS", mathPoints);
        mathFails = savedInstanceState.getInt("MATH_FAILS", mathFails);
        listRndMath = savedInstanceState.getIntegerArrayList("ArrayList");
        System.out.println("ONRESTORE");
        System.out.println(difficulty);
        System.out.println(mathPoints);
        System.out.println(mathFails);
        System.out.println(listRndMath.size());
    }
}
