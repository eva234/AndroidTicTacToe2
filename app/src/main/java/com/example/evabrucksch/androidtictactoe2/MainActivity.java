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


    private class PlayOnClick implements View.OnClickListener {

        int btnNumber = -1;

        public PlayOnClick(int number){
            btnNumber = number;
        }

        @Override
        public void onClick(View view) {
            if(field[btnNumber] == -1) {
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
            }
        }
    }
}
