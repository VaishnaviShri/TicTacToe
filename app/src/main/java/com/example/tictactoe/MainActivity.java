package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int player;
    int[][] array = new int[9][2];// value in second column of array : circle=0 cross=1 none=2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<9; i++)
            array[i][1]=2;
    }

    public void add(View view)
    {
        ImageView sign = (ImageView) view;
        String tag_string = sign.getTag().toString();
        int tag = Integer.parseInt(tag_string);

        if(player==0) {
            sign.setImageResource(R.mipmap.circle_foreground);
            player = 1;
            array[tag][1]=0;
        }
        else {
            sign.setImageResource(R.mipmap.cross_foreground);
            player = 0;
            array[tag][1]=1;
        }


        sign.animate().alpha(1).setDuration(1000);
        sign.setClickable(false);
        checkGameStatus(tag);




    }
    public void checkGameStatus(int tag){
        int i, j,n=3;
        boolean horizontal_match, vertical_match, diagonal_match;
        int count =0;
        horizontal_match = vertical_match = diagonal_match = false;

        int t=0;
        for (i=0; i<3; i++){
            count =0;
            for(j=0; j<3;j++) {
                if (array[t][1] == player)
                    count++;
                if(count==n)
                    horizontal_match= true;

                t++;
            }
        }

        t=0;
        count=0;
        for (i=0; i<3; i++){
                if (array[t][1] == player)
                    count++;
                if(count==n)
                    diagonal_match= true;

                t+=4;

        }

        for (j=0; j<3; j++){
            count =0;
            t=j;
            for(i=0; i<3;i++) {
                if (array[t][1] == player)
                    count++;
                if(count==n)
                    vertical_match= true;

                t+=3;
            }
        }

        if(vertical_match || horizontal_match || diagonal_match)
            declareWinner();

    }
    public void declareWinner(){
        TextView textview = findViewById(R.id.textView);
        String winner,s;
        winner = (player==0)?"circle":"cross";
        s = "Winner is " + winner;
        textview.setText(s);
        textview.setVisibility(View.VISIBLE);
        gameOver();

    }
    public void gameOver(){

    }
}
