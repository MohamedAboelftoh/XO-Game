package com.example.xogame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class XoActivity extends AppCompatActivity {
    String player1name ;
    String player2name ;
    TextView scorePlayer1;
    TextView scorePlayer2;
    TextView player1 ;
    TextView player2 ;
   ConstraintLayout root ;
    int playerXScore = 0 ;
    int playerOScore = 0 ;
    String[] arr = {"","","",
                     "","","",
                     "","",""};
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo);
        root = findViewById(R.id.topconstrain) ;
        scorePlayer1 = findViewById(R.id.scorePlayeer1) ;
        scorePlayer2 = findViewById(R.id.ScorePlayer2) ;
        player1 = findViewById(R.id.Textplayr1);
        player2 = findViewById(R.id.Textplayr2);
        player1name = getIntent().getStringExtra("player1name");
        player2name = getIntent().getStringExtra("player2name");
        player1.setText(player1name+"(X)");
        player2.setText(player2name+"(O)");
    }
    int count = 0 ;
    @SuppressLint("SetTextI18n")
    public void onPlayerClick(View view)
    {
        if(!((Button) view).getText().toString().isEmpty())
            return;
        if(count %2 == 0) {
            ((Button) view).setText("x");
            ChangeBordState(((Button) view),"x");
        }
        else {
            ((Button) view).setText("o");
            ChangeBordState(((Button) view),"o");
        }
        count++ ;
        if (CheckWinner("x"))
        {
            playerXScore +=1;
            scorePlayer1.setText(playerXScore +"");
            resetBoard();
            Toast.makeText(this,player1name+" Win",Toast.LENGTH_LONG).show();
        } else if (CheckWinner("o")) {
            playerOScore +=1;
            scorePlayer2.setText(playerOScore +"");
            resetBoard();
            Toast.makeText(this,player2name+" Win",Toast.LENGTH_LONG).show();
        } else if (count == 9) {
            resetBoard();
            Toast.makeText(this,"No Winer",Toast.LENGTH_LONG).show();
        }
    }
    void resetBoard()
    {
        count = 0 ;
        arr = new String[]{"", "", "", "", "", "", "", "", ""};
        clearButtons();
    }
    void clearButtons()
    {
        for (int i = 0 ; i < 9 ; i++) {
         View view  = root.findViewWithTag(i+"");
            ((Button) view).setText("");
        }
    }
    boolean CheckWinner(String value)
    {
        for (int i = 0 ; i <9 ; i+=3)
        {
            if(arr[i].equals(value) && arr[i+1].equals(value) && arr[i+2].equals(value))
                return true ;
        }
        for (int i = 0 ; i <3 ; i++)
        {
            if(arr[i].equals(value) && arr[i+3].equals(value) && arr[i+6].equals(value))
                return true ;
        }
        if(arr[0].equals(value) && arr[4].equals(value) && arr[8].equals(value))
            return true ;
        if(arr[2].equals(value) && arr[4].equals(value) && arr[6].equals(value))
            return true ;
        return false ;
    }
    public void ChangeBordState(Button b , String value )
    {
        int index = getButtonIndex(b);
        arr[index] = value ;
    }
    public int getButtonIndex(Button b){
        String tag = ((String) b.getTag());
        return Integer.parseInt(tag) ;
    }
}
