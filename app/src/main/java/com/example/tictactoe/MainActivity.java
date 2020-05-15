package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new Menu_fragment());
        /*Button play = findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadFragment(new Menu_fragment());
            }
        });*/


    }

    private void loadFragment(Fragment fragment) {

// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame1, fragment);
        fragmentTransaction.commit(); // save the changes
    }
    /*public void add(View view)
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
        checkGameStatus(tag);
        sign.setClickable(false);





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

    }*/
}
