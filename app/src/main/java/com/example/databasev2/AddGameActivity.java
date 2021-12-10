package com.example.databasev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddGameActivity extends AppCompatActivity {

    LinearLayout layout;
    LinearLayout gameLayout;
    TextView newGameTitle;
    EditText titleInput;
    EditText priceInput;
    EditText descriptionInput;
    Button buttonSave;
    Button buttonReturnFromAdd;



    private Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        initTextChangeEvents();
        initButtonSave();
        initMainActivity();
        initUpdateColors();


        currentGame = new Game();

    }

    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        if (position != -1) {
            GameDataSource ds = new GameDataSource(this);
            try {
               ds.open();
               currentGame = ds.getGame(position+1);
               ds.close();
                EditText titleInput = findViewById(R.id.titleInput);
                titleInput.setText(currentGame.getTitle());
                EditText priceInput = findViewById(R.id.priceInput);
                priceInput.setText(currentGame.getPrice());
                EditText descriptionInput = findViewById(R.id.descriptionInput);
                descriptionInput.setText(currentGame.getDescription());
            } catch (Exception e){
                Toast.makeText(this,"Error accessing game", Toast.LENGTH_LONG).show();
            }
        }

        initUpdateColors();
    }

    private void initTextChangeEvents(){
        EditText titleEdit = findViewById(R.id.titleInput);
        titleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentGame.setTitle(titleEdit.getText().toString());
                //currentContact.setContactID(-1);
            }
        });
        EditText priceEdit = findViewById(R.id.priceInput);
        priceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentGame.setPrice(priceEdit.getText().toString());
                //currentContact.setContactID(-1);
            }
        });
        EditText descriptionEdit = findViewById(R.id.descriptionInput);
        descriptionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing for this section
            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentGame.setDescription(descriptionEdit.getText().toString());
                //currentContact.setContactID(-1);
            }
        });
    }
    private void initButtonSave(){
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                GameDataSource ds = new GameDataSource(AddGameActivity.this);
                try {
                    ds.open();
                    if(currentGame.getGameID()== -1){
                        wasSuccessful = ds.insertGame(currentGame);
                        if (wasSuccessful){
                            int newID = ds.getLastGameID();
                            currentGame.setGameID(newID);
                        }
                    } else{
                        wasSuccessful = ds.updateGame(currentGame);
                    } ds.close();

                } catch (Exception e){
                    wasSuccessful = false;
                    Toast.makeText(getBaseContext(), "Error! Game not saved.", Toast.LENGTH_LONG).show();


                }
                if (wasSuccessful) {
                    Toast.makeText(getBaseContext(), "Game Saved!", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void initMainActivity(){
        Button backToMain = findViewById(R.id.buttonReturnFromAdd);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddGameActivity.this, MainActivity.class));

            }
        });
    }
    private void initUpdateColors(){

        layout = findViewById(R.id.layout);
        gameLayout = findViewById(R.id.gameLayout);
        newGameTitle = findViewById(R.id.newGameTitle);
        titleInput = findViewById(R.id.titleInput);
        priceInput = findViewById(R.id.priceInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        buttonSave = findViewById(R.id.buttonSave);
        buttonReturnFromAdd = findViewById(R.id.buttonReturnFromAdd);

        SharedPreferences sharedPref = getSharedPreferences("MySettingPreferences", Context.MODE_PRIVATE);
        String changeColor = sharedPref.getString("color", "name");

        if (changeColor.equals("default")) {
            layout.setBackgroundColor(0xFF2B2A2A);
            newGameTitle.setTextColor(0xFFFFFFFF);
            buttonSave.setBackgroundColor(0xFFFFFFFF);
            buttonSave.setTextColor(0x802B2A2A);
            buttonReturnFromAdd.setBackgroundColor(0xFFFFFFFF);
            buttonReturnFromAdd.setTextColor(0x802B2A2A);
        } else if (changeColor.equals("white")) {
            layout.setBackgroundColor(0xFFFFFFFF);
            newGameTitle.setTextColor(0x802B2A2A);
            buttonSave.setBackgroundColor(0x802B2A2A);
            buttonSave.setTextColor(0xFFFFFFFF);
            buttonReturnFromAdd.setBackgroundColor(0x802B2A2A);
            buttonReturnFromAdd.setTextColor(0xFFFFFFFF);
        } else {
            layout.setBackgroundColor(0xFFFFEACB);
            newGameTitle.setTextColor(0xFF4e350e);
            buttonSave.setBackgroundColor(0x804e350e);
            buttonSave.setTextColor(0x80FFEACB);
            buttonReturnFromAdd.setBackgroundColor(0x804e350e);
            buttonReturnFromAdd.setTextColor(0x80FFEACB);

        }
    }
}