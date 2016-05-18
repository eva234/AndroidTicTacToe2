package com.example.evabrucksch.androidtictactoe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton[] buttons = new ImageButton[9];
    int[] field = new int[9];
    //1 steht für X, 0 steht für O
    int player = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        }
    }
}
