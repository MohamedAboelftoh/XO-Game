package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText player1;
    EditText player2;
    Button btnGoToGame ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.player1) ;
        player2 = findViewById(R.id.player2) ;
        btnGoToGame = findViewById(R.id.go_game) ;
        btnGoToGame.setOnClickListener(view -> StartGame());
    }
    private void StartGame()
    {
        String player1name = player1.getText().toString();
        String player2name = player2.getText().toString();
        Intent intent = new Intent(this, XoActivity.class);
        intent.putExtra("player1name",player1name);
        intent.putExtra("player2name",player2name);
        startActivity(intent);
    }
}