package com.example.evabrucksch.androidtictactoe2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View table;
    ImageButton[] buttons = new ImageButton[9];
    int[] field = new int[9];
    int player = 1; //1 = X, 2 = O
    int clicked = 0;
    boolean gameOver = false;
    TextView result;
    TextView turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = (View) findViewById(R.id.tableLayout);
        result = (TextView) findViewById(R.id.result);
        turn = (TextView) findViewById(R.id.turn);
        setTexts();

        setUpButtons();

        for(int i=0; i<9; i++) {
            field[i]=-1;
        }

        setUpOnClickListeners(table);
    }

    private void setTexts() {
        result.setTextColor(Color.BLACK);
        result.setText("Player 1 is X, Player 2 is O.");
        if(player == 1) {
            turn.setTextColor(Color.BLUE);
        } else if (player == 2) {
            turn.setTextColor(Color.RED);
        }
        turn.setText("It's the turn of Player "+player);

    }

    private void setUpButtons() {
        buttons[0] = (ImageButton) findViewById(R.id.imageButton1);
        buttons[1] = (ImageButton) findViewById(R.id.imageButton2);
        buttons[2] = (ImageButton) findViewById(R.id.imageButton3);
        buttons[3] = (ImageButton) findViewById(R.id.imageButton4);
        buttons[4] = (ImageButton) findViewById(R.id.imageButton5);
        buttons[5] = (ImageButton) findViewById(R.id.imageButton6);
        buttons[6] = (ImageButton) findViewById(R.id.imageButton7);
        buttons[7] = (ImageButton) findViewById(R.id.imageButton8);
        buttons[8] = (ImageButton) findViewById(R.id.imageButton9);
    }

    private void setUpOnClickListeners(View v) {
        for(int i=0; i<9; i++) {
            buttons[i].setOnClickListener(new PlayOnClick(i));
        }
    }

    private void theWinnerIs() {
        if(checkForWin(1)){
            result.setTextColor(Color.BLUE);
            result.setText("The winner is Player 1");
            turn.setText("");
            gameOver = true;
        } else if(checkForWin(2)) {
            result.setTextColor(Color.RED);
            result.setText("The winner is Player 2");
            turn.setText("");
            gameOver = true;
        } else if(clicked==9){
            result.setTextColor(Color.BLACK);
            result.setText("It's a tie.");
            turn.setText("");
            gameOver = true;
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
            buttons[i].setImageResource(android.R.color.transparent);
            buttons[i].setEnabled(true);
        }
        setUpOnClickListeners(table);
        setTexts();
    }


    private class PlayOnClick implements View.OnClickListener {

        int btnNumber = -1;

        public PlayOnClick(int number){
            btnNumber = number;
        }

        @Override
        public void onClick(View view) {
            if(gameOver == false && field[btnNumber] == -1) {
                if (player == 1) {
                    field[btnNumber] = 1;
                    player = 2;
                    buttons[btnNumber].setImageResource(R.drawable.x);
                }
                else if (player == 2) {
                    field[btnNumber] = 2;
                    player = 1;
                    buttons[btnNumber].setImageResource(R.drawable.o);
                }
                buttons[btnNumber].setEnabled(false);
                setTexts();
                clicked++;
                theWinnerIs();
            }
        }
    }
}
