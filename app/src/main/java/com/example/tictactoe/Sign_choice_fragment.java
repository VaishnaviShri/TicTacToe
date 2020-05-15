package com.example.tictactoe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Sign_choice_fragment extends Fragment {
    Button cross, circle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_choice_fragment, container, false);
        cross = view.findViewById(R.id.cross);
        circle = view.findViewById(R.id.circle);
        cross.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Game_screen_fragment game_screen_fragment = new Game_screen_fragment();
                game_screen_fragment.instanceOf(1,1);
                loadFragment(game_screen_fragment);
            }
        });

        circle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Game_screen_fragment game_screen_fragment = new Game_screen_fragment();
                game_screen_fragment.instanceOf(1,0);
                loadFragment(game_screen_fragment);
            }
        });
        return view;
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
}
