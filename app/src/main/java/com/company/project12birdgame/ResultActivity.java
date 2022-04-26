package com.company.project12birdgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    private TextView textView,yourscore,highscore;
    private Button playagain;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.textviewcongo);
        yourscore = findViewById(R.id.yourscore);
        highscore = findViewById(R.id.highscore);

        playagain = findViewById(R.id.playagain);

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,GameActivity.class));
                finish();
            }
        });

        int score = getIntent().getIntExtra("scores",0);
        yourscore.setText("Your Score :" + score);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestscore =sharedPreferences.getInt("newhighscore",0); // defining initial high score

        if(score==200){
            textView.setText("You won the game");
            highscore.setText("Highest Score" +score);
            sharedPreferences.edit().putInt("newhighscore",score).apply();
        }else if(score>=highestscore){
            textView.setText("Congratulations you set new high score but lost the game");
            highscore.setText("Highest Score" +score);
            sharedPreferences.edit().putInt("newhighscore",score).apply();
        }else{
            textView.setText("You lost the game");

            highscore.setText("Highest Score" +highestscore);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Close game");
        builder.setMessage("Do you want to close game");
        builder.setNegativeButton("quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });

        builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }


}