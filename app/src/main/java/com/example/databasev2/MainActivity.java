package com.example.databasev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    ConstraintLayout layout;
    TextView booksTitle;
    Button buttonAdd;
    Button buttonSettings;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, AddGameActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position",position);
            startActivity(intent);
        }
    };


    private class SortByName implements Comparator<Game> {

        private boolean isAscending;

        public SortByName(boolean isAscending){
            this.isAscending = isAscending;
        }

        @Override
        public int compare(Game game, Game t1) {
            if(isAscending)
                return game.getTitle().compareTo(t1.getTitle());
            else
                return t1.getTitle().compareTo(game.getTitle());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        booksTitle = findViewById(R.id.booksTitle);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSettings = findViewById(R.id.buttonSettings);

        initAddGameActivity();
        initSettingsActivity();

        GameDataSource ds = new GameDataSource(this);
        ArrayList<Game> games;
        try {
            ds.open();
            games = ds.getGames();
            ds.close();

//            SharedPreferences sharedPref = getSharedPreferences("MyGameListPreferences", Context.MODE_PRIVATE);
//            String sortBy = sharedPref.getString("sortfield", "title");
//            String sortOrder = sharedPref.getString("sortorder", "ascending");
//            if (sortBy.equals("title")){
//                if(sortOrder.equals("ascending"))
//                    games.sort(new SortByName(true));
//            } else {
//                games.sort(new SortByName(false));
//            }

            RecyclerView gameList = findViewById(R.id.rvGames);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            gameList.setLayoutManager(layoutManager);
            GameAdapter gameAdapter = new GameAdapter(games);
            gameAdapter.setOnClickListener(onClickListener);
            gameList.setAdapter(gameAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving games",Toast.LENGTH_LONG).show();
        }
    }

    private void initAddGameActivity() {
        Button btnAddNote = findViewById(R.id.buttonAdd);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddGameActivity.class));
            }
        });
    }

    private void initSettingsActivity() {
        Button btnChangeBackground = findViewById(R.id.buttonSettings);
        btnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
        String changeColor = sharedPref.getString("color", "name");


        if (changeColor.equals("default")) {
            layout.setBackgroundColor(0xFF2B2A2A);
            booksTitle.setTextColor(0xFFFFFFFF);
            buttonAdd.setBackgroundColor(0xFFFFFFFF);
            buttonAdd.setTextColor(0x802B2A2A);
            buttonSettings.setBackgroundColor(0xFFFFFFFF);
            buttonSettings.setTextColor(0x802B2A2A);
        } else if (changeColor.equals("white")) {
            layout.setBackgroundColor(0xFFFFFFFF);
            booksTitle.setTextColor(0x802B2A2A);
            buttonAdd.setBackgroundColor(0x802B2A2A);
            buttonAdd.setTextColor(0xFFFFFFFF);
            buttonSettings.setBackgroundColor(0x802B2A2A);
            buttonSettings.setTextColor(0xFFFFFFFF);
        } else {
            layout.setBackgroundColor(0xFFFFEACB);
            booksTitle.setTextColor(0xFF4e350e);
            buttonAdd.setBackgroundColor(0x804e350e);
            buttonAdd.setTextColor(0x80FFEACB);
            buttonSettings.setBackgroundColor(0x804e350e);
            buttonSettings.setTextColor(0x80FFEACB);

        }
    }

}