package com.example.tictactoe;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Random;

public class Game_screen_fragment extends Fragment {


    int mode;
    boolean game_active;
    int player, single_player_sign_choice;
    int[][] array = new int[9][2];// value in second column of array : circle=0 cross=1 none=2
    TextView textView;
    GridLayout grid;
    View view;
    Button play_again_button;

    public void instanceOf(int mode_entered, int sign_choice){
        mode = mode_entered;
        if(mode == 1){
            single_player_sign_choice = sign_choice;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0; i<9; i++)
            array[i][1]=2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_screen_fragment, container, false);
        grid = view.findViewById(R.id.gridLayout);
        action_on_click();

        textView = view.findViewById(R.id.winner);
        play_again_button = view.findViewById(R.id.play_again_button);
        return view;
    }
    public void action_on_click(){
        int childCount = grid.getChildCount();
        for (int i= 0; i < childCount; i++){
            final ImageView box = (ImageView) grid.getChildAt(i);
            box.setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void onClick(View view){
                    add(box);
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void computer_turn() {
        int computer_choice;
        computer_choice  = (single_player_sign_choice == 0) ? 1 : 0 ;

        int tag_computer;
        Random rand = new Random();
        int random = rand.nextInt(8);
        while (true) {
            if (array[random][1] == 2) {
                tag_computer = random;
                break;
            }
            random = rand.nextInt(8);

        }
        ImageView sign = (ImageView) grid.getChildAt(tag_computer);
        //ImageView sign = view.findViewById(R.id.gridLayout).findViewWithTag(tag_computer);
        if (computer_choice == 0) {
            sign.setImageResource(R.mipmap.circle_foreground);
            array[tag_computer][1] = 0;
            player = 1;
        } else {
            sign.setImageResource(R.mipmap.cross_foreground);
            array[tag_computer][1] = 1;
            player = 0;
        }

        sign.animate().alpha(1).setDuration(1000);
        checkGameStatus(tag_computer);
        sign.setClickable(false);
    }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void add(View view) {

            ImageView sign = (ImageView) view;
            String tag_string = sign.getTag().toString();
            int tag = Integer.parseInt(tag_string);

            if (mode == 1) {

                if (single_player_sign_choice == 0) {
                    sign.setImageResource(R.mipmap.circle_foreground);
                    array[tag][1] = 0;
                } else {
                    sign.setImageResource(R.mipmap.cross_foreground);
                    array[tag][1] = 1;
                }

            sign.animate().alpha(1).setDuration(1000);
            checkGameStatus(tag);
            sign.setClickable(false);

            computer_turn();
        }



        else if(mode == 2){
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

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkGameStatus(int tag){
        int i, j,n=3;
        boolean horizontal_match, vertical_match, r_diagonal_match, l_diagonal_match;
        int count =0;
        horizontal_match = vertical_match = r_diagonal_match = l_diagonal_match = false;

        int t=0;
        for (i=0; i<3; i++){
            count =0;
            for(j=0; j<3;j++) {
                if (array[t][1] == player)
                    count++;
                if(count==n)
                    horizontal_match = true;

                t++;
            }
        }

        t=0;
        count=0;
        for (i=0; i<3; i++){
            if (array[t][1] == player)
                count++;
            if(count==n)
                r_diagonal_match = true;

            t+=4;

        }

        t=0;
        count=0;
        for (i=0; i<3; i++){
            if (array[t][1] == player)
                count++;
            if(count==n)
                l_diagonal_match = true;

            t+=2;

        }

        for (j=0; j<3; j++){
            count =0;
            t=j;
            for(i=0; i<3;i++) {
                if (array[t][1] == player)
                    count++;
                if(count==n)
                    vertical_match = true;

                t+=3;
            }
        }

        if(vertical_match || horizontal_match || r_diagonal_match || l_diagonal_match)
            declareWinner();

        game_active = false;
        for(i = 0; i<9 ; i++){
            if(!(array[i][1]==2)) {
                game_active=true;
                break;
            }
        }
        if(!game_active)
            gameOver();

    }
   public void declareWinner(){
        String winner,s;
        winner = (player==0)?"circle":"cross";
        s = "Winner is " + winner;
        textView.setText(s);
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        textView.setVisibility(View.VISIBLE);
        gameOver();

    }
    public void gameOver(){
        int childCount = grid.getChildCount();

        for (int i= 0; i < childCount; i++){
            final ImageView box = (ImageView) grid.getChildAt(i);
            box.setClickable(false);
        }
        play_again_button.setVisibility(View.VISIBLE);
        play_again_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                play_again();
            }
        });


    }
    public void play_again(){
        int childCount = grid.getChildCount();
        for (int i= 0; i < childCount; i++){
            final ImageView box = (ImageView) grid.getChildAt(i);
            box.setVisibility(View.INVISIBLE);
            box.setClickable(true);
            array[i][1] = 2;
        }
        textView.setVisibility(View.INVISIBLE);
        play_again_button.setVisibility(View.INVISIBLE);
        action_on_click();


    }
}
