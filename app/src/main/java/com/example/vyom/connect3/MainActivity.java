package com.example.vyom.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 -> yellow, 1 -> red 2 -> unplayed
    int activePlayer = 0;
    boolean gameisactive = true;

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        //System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(state[tappedCounter]==2 && gameisactive) {

            state[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if(state[winningPosition[0]] == state[winningPosition[1]] &&
                        state[winningPosition[1]] == state[winningPosition[2]] &&
                        state[winningPosition[0]] != 2){
                    //System.out.println(state[winningPosition[0]]);

                    TextView winner = findViewById(R.id.winnerMessage);
                    if(state[winningPosition[0]]==0)
                        winner.setText("Yellow has won!");
                    else
                        winner.setText("Red has won!");
                    LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    gameisactive = false;

                }else{
                    boolean gameIsOver = true;
                    for(int counterState : state){

                        if(counterState==2)
                            gameIsOver = false;

                    }
                    if(gameIsOver){

                        TextView winner = findViewById(R.id.winnerMessage);
                        winner.setText("It's a draw!");
                        LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }

                }
            }

        }
    }

    public void playAgain(View view){
        LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        gameisactive = true;

        for(int i = 0; i<state.length ; i++)
            state[i] = 2;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
