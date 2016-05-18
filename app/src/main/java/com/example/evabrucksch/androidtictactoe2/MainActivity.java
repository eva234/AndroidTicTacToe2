package com.example.evabrucksch.androidtictactoe2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    View table;
    ImageButton[] buttons = new ImageButton[9];
    int[] field = new int[9];
    int player = 0; //0 = X, 1 = O
    int clicked = 0;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = (View) findViewById(R.id.tableLayout);

        buttons[0] = (ImageButton) findViewById(R.id.imageButton1);
        buttons[1] = (ImageButton) findViewById(R.id.imageButton2);
        buttons[2] = (ImageButton) findViewById(R.id.imageButton3);
        buttons[3] = (ImageButton) findViewById(R.id.imageButton4);
        buttons[4] = (ImageButton) findViewById(R.id.imageButton5);
        buttons[5] = (ImageButton) findViewById(R.id.imageButton6);
        buttons[6] = (ImageButton) findViewById(R.id.imageButton7);
        buttons[7] = (ImageButton) findViewById(R.id.imageButton8);
        buttons[8] = (ImageButton) findViewById(R.id.imageButton9);

        for(int i=0; i<9; i++) {
            field[i]=-1;
        }

        setUpOnClickListeners(table);
    }

    private void setUpOnClickListeners(View v) {
        for(int i=0; i<9; i++) {
            buttons[i].setOnClickListener(new PlayOnClick(i));
        }
    }

    private void theWinnerIs() {
        if(checkForWin(0)){
            Log.i("winner", "The winner is Player 0");
            gameOver = true;
        } else if(checkForWin(1)) {
            Log.i("winner", "The winner is Player 1");
            gameOver = true;
        } else if(clicked==9){
            Log.i("winner", "Noone one this round, it's a tie.");
            gameOver = true;
        } else {
            Log.i("winner", "The game is not over yet.");
        }
    }

    private boolean checkForWin(int p) {
        if((field[0]==field[1] && field[1]==field[2] && field[2] == p) ||
            (field[3]==field[4] && field[4]==field[5] && field[5] == p) ||
            (field[6]==field[7] && field[7]==field[8] && field[8] == p) ||
            (field[0]==field[4] && field[4]==field[8] && field[8] == p) ||
            (field[2]==field[4] && field[4]==field[6] && field[6] == p) ||
            (field[0]==field[3] && field[3]==field[6] && field[6] == p) ||
            (field[1]==field[4] && field[4]==field[7] && field[7] == p) ||
            (field[2]==field[5] && field[5]==field[8] && field[8] == p)) {
                return true;
        } else {
            return false;
        }
    }

    protected void reset(View view) {
        for(int i=0; i<9; i++) {
            field[i] = -1;
            clicked = 0;
            gameOver = false;
            buttons[i].setBackgroundColor(Color.BLACK);
            buttons[i].setEnabled(true);
        }
        setUpOnClickListeners(table);
        Log.i("reset", "Reset Button clicked");
    }


    private class PlayOnClick implements View.OnClickListener {

        int btnNumber = -1;

        public PlayOnClick(int number){
            btnNumber = number;
        }

        @Override
        public void onClick(View view) {
            if(gameOver == false && field[btnNumber] == -1) {
                if (player == 0) {
                    field[btnNumber] = 0;
                    player = 1;
                    buttons[btnNumber].setBackgroundColor(Color.RED);
                }
                else if (player == 1) {
                    field[btnNumber] = 1;
                    player = 0;
                    buttons[btnNumber].setBackgroundColor(Color.BLUE);
                }
                buttons[btnNumber].setEnabled(false);
                clicked++;
                theWinnerIs();
            }
        }
    }
}
