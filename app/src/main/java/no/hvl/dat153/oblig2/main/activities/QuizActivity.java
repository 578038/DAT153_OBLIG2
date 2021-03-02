package no.hvl.dat153.oblig2.main.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import no.hvl.dat153.oblig2.R;
import no.hvl.dat153.oblig2.main.data.Student;
import no.hvl.dat153.oblig2.main.ui.StudentViewModel;

public class QuizActivity extends AppCompatActivity {

    private TextView infoText;
    private TextView scoreText;
    private TextView counterText;
    private ImageView guessImage;
    private EditText guessText;
    private Button buttonGuess;

    private StudentViewModel mViewModel;
    public static ArrayList<Student> pList;//Kopi av databasen slik at vi har oversikt over hvilken som er svart og ikke svart
    private int index;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        observerSetup();
    }


    private void observerSetup() {
        mViewModel.getAllStudents().observe(this,
                new Observer<List<Student>>() {
                    @Override
                    public void onChanged(@Nullable final List<Student> Students) {
                        guessImage = (ImageView) findViewById(R.id.imageGuess);
                        buttonGuess = (Button) findViewById(R.id.guessButton);
                        guessText = (EditText) findViewById(R.id.guessText);
                        infoText = (TextView) findViewById(R.id.textView3);
                        scoreText = (TextView) findViewById(R.id.textViewScore);
                        counterText = (TextView) findViewById(R.id.textViewCounter);

                        pList = new ArrayList<Student>(Students);
                        Collections.shuffle(pList); //stokker listen slik at den blir tilfeldig

                        index = 0;
                        scoreText.setText("Score: " + score + "/" + pList.size());
                        counterText.setText("Persons left: " + (pList.size() - index));
                        nextPerson();


                        // Legger til en listener på edit text slik at bruker kan trykke enter på tastatur for å gjette
                        guessText.setOnKeyListener(new View.OnKeyListener() {
                            @Override
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                                    makeGuess();
                                    return true;
                                }
                                return false;
                            }
                        });

                        //kan også trykke på knapp
                        buttonGuess.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                makeGuess();
                            }
                        });
                    }
                });


    }



    public void checkCorrect() {

        Student p = pList.get(index);
        index++;
        if (p.getName().equals(guessText.getEditableText().toString())) {
            score++;
            Toast.makeText(QuizActivity.this, "Du gjettet riktig! Navn: " + p.getName() + " Din Score: " + score, Toast.LENGTH_SHORT).show();
            scoreText.setText("Score: " + score + "/" + pList.size());
            counterText.setText("Persons left: " + (pList.size() - index));
        } else if (p.getName().toLowerCase().equals(guessText.getEditableText().toString().toLowerCase())) {
            score++;
            Toast.makeText(QuizActivity.this, "Du gjettet riktig! Navn: " + p.getName() + " Din Score: " + score, Toast.LENGTH_SHORT).show();
            scoreText.setText("Score: " + score + "/" + pList.size());
            counterText.setText("Persons left: " + (pList.size() - index));
        } else if (p.getName().toUpperCase().equals(guessText.getEditableText().toString().toUpperCase())) {
            score++;
            Toast.makeText(QuizActivity.this, "Du gjettet riktig! Navn: " + p.getName() + " Din Score: " + score, Toast.LENGTH_SHORT).show();
            scoreText.setText("Score: " + score + "/" + pList.size());
            counterText.setText("Persons left: " + (pList.size() - index));
        } else {
            Toast.makeText(QuizActivity.this, "Du gjettet FEIL, Riktig navn var: " + p.getName(), Toast.LENGTH_SHORT).show();
            counterText.setText("Persons left: " + (pList.size() - index));
        }
        guessText.setText("");
        nextPerson();

    }


    public void nextPerson() {
        if (pList.size() > index) {
            Student pers = pList.get(index);
            guessImage.setImageBitmap(pers.getImg());
        } else {
            guessImage.setImageBitmap(null);
            buttonGuess.setEnabled(false);
            guessText.setOnKeyListener(null);
            infoText.setText("SPILL FERDIG, TRYKK RESET KNAPP FOR Å STARTE PÅ NY");
        }
    }


    public void resetQuiz(View View) {
        recreate();
    }


    public void goMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    public void makeGuess(){
        if (guessText.getText().toString().equals("")) {
            Toast.makeText(QuizActivity.this, "Skriv inn navn før du gjetter!", Toast.LENGTH_SHORT).show();
        } else {
            checkCorrect();
            //beholder tastatur oppe og fokus på guessText slik at bruker ikke trenger å clikke flere ganger
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(guessText, InputMethodManager.SHOW_IMPLICIT);
        }
    }


}