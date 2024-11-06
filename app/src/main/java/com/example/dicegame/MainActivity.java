package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button RollDice;
    private Button Reset;

    private TextView textNumber1;
    private TextView textNumber2;
    private TextView textNumber3;
    private TextView textNumber4;
    private TextView textNumber5;

    private TextView RollScore;
    private TextView GameScore;
    private TextView RollCount;

    private int totalGameScore = 0;
    private int rollCounter = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RollDice = findViewById(R.id.RollDice);
        Reset = findViewById(R.id.Reset);

        textNumber1 = findViewById(R.id.textNumber1);
        textNumber2 = findViewById(R.id.textNumber2);
        textNumber3 = findViewById(R.id.textNumber3);
        textNumber4 = findViewById(R.id.textNumber4);
        textNumber5 = findViewById(R.id.textNumber5);

        RollScore = findViewById(R.id.RollScore);
        GameScore = findViewById(R.id.GameScore);
        RollCount = findViewById(R.id.RollCount);

        RollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        int[] diceResults = new int[5];
        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
        }

        displayDiceResults(diceResults);

        int rollScore = calculateScore(diceResults);

        updateScore(rollScore);

        updateRollCount();
    }

    private int calculateScore(int[] diceResults) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int result : diceResults) {
            counts.put(result, counts.getOrDefault(result, 0) + 1);
        }

        int score = 0;
        for (int value : counts.keySet()) {
            int count = counts.get(value);
            if (count >= 2) {
                score += value * count;
            }
        }
        RollScore.setText("Wynik tego losowania: " + score);
        return score;
    }

    private void updateScore(int newScore) {
        totalGameScore += newScore;

        GameScore.setText("Wynik gry: " + totalGameScore);
    }

    private void updateRollCount() {
        rollCounter++;

        RollCount.setText("Liczba rzutów: " + rollCounter);
    }

    private void displayDiceResults(int[] diceResults) {
        textNumber1.setText(String.valueOf(diceResults[0]));
        textNumber2.setText(String.valueOf(diceResults[1]));
        textNumber3.setText(String.valueOf(diceResults[2]));
        textNumber4.setText(String.valueOf(diceResults[3]));
        textNumber5.setText(String.valueOf(diceResults[4]));
    }

    private void resetGame() {
        textNumber1.setText("?");
        textNumber2.setText("?");
        textNumber3.setText("?");
        textNumber4.setText("?");
        textNumber5.setText("?");

        totalGameScore = 0;
        GameScore.setText("Wynik gry: 0");

        RollScore.setText("Wynik tego losowania: 0");

        rollCounter = 0;
        RollCount.setText("Liczba rzutów: 0");
    }
}