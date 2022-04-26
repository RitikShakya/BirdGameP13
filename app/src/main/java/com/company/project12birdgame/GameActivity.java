package com.company.project12birdgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird1,heart1,heart2,heart3,coin1,coin2,enemy1,enemy2,enemy3;
    private TextView livescore,startinfo;
    private ConstraintLayout constraintLayout;

    boolean touchcontrol=false,begincontrol=false;

    private  Runnable runnable,runnable2;
    private Handler handler,handler2;


    //bird location
    int birdX,birdY,enemy1X,enemy2X,enemy3X,enemy1Y,enemy2Y,enemy3Y,coin1X,coin1Y,coin2X,coin2Y;

    //screen size
    int screenwidth,screenheight;

    //lifes and points
    int life=3 ,points=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        constraintLayout = findViewById(R.id.constraintl);

        bird1 = findViewById(R.id.bird1);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        enemy1 = findViewById(R.id.redhornimage);
        enemy2 = findViewById(R.id.beeimage);
        enemy3 = findViewById(R.id.spriteimage);

        livescore = findViewById(R.id.scoretext);
        startinfo = findViewById(R.id.textViewstart);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                startinfo.setVisibility(View.INVISIBLE);



                if(!begincontrol){ // screen touch gor first time
                    begincontrol=true;

                    screenwidth = (int) constraintLayout.getWidth();
                    screenheight =(int) constraintLayout.getHeight();

                    birdX =(int) bird1.getX();
                    birdY= (int)bird1.getY();


                    handler = new Handler(); // to move the birds and enemys
                    runnable = new Runnable() {
                        @Override
                        public void run() {

                            enemyControl();

                            makebirdfly();

                            collisionControl();
                        }
                    };
                    handler.post(runnable);

                }else{ // motion events when screen touch more than one time
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){ //touch is on screen
                        touchcontrol=true;

                    }if(motionEvent.getAction()==MotionEvent.ACTION_UP){ // off touch
                        touchcontrol=false;
                    }
                }

                return true;
            }
        });






    }

    public void makebirdfly(){

        if(touchcontrol){ // if touch on
            birdY = birdY -(screenheight/50); // move up with a ratio based on the size os screen
        }else {
            birdY = birdY +(screenheight/50);
        }
        if(birdY<=0){
            birdY=0;
        }if(birdY>=(screenheight-bird1.getHeight())){
            birdY= screenheight-bird1.getHeight();
        }

        bird1.setY(birdY);


    }

    public void enemyControl(){

        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        // enemy one
        enemy1X =enemy1X - (screenwidth/130); // to make the enemy come closer towars x axis

        if(points>50&&points<=100){
            enemy1X =enemy1X - (screenwidth/110);
        }if(points>100&&points<=150){
            enemy1X =enemy1X - (screenwidth/90);
        }if(points>150){
            enemy1X =enemy1X - (screenwidth/80);
        }
        if(enemy1X<=0){
            enemy1X = screenwidth+200;  // to let the enemy come again after it goes out of screen
            enemy1Y = (int)Math.floor(Math.random() * screenheight);  // random 0-1, floor-roundoff, to move the enemy up down randomly

            if(enemy1Y<=0){
                enemy1Y=0;
            }if(enemy1Y>=(screenheight-enemy1.getHeight())){
                enemy1Y= screenheight-enemy1.getHeight();
            }

        }
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);


        // enemy2

        enemy2X =enemy2X - (screenwidth/150); // to make the enemy come closer towars x axis

        if(points>50&&points<=100){
            enemy2X =enemy2X - (screenwidth/130);
        }if(points>100&&points<=150){
            enemy2X =enemy2X - (screenwidth/110);
        }if(points>150){
            enemy2X =enemy2X - (screenwidth/100);
        }
        if(enemy2X<=0){
            enemy2X = screenwidth+150;  // to let the enemy come again after it goes out of screen
            enemy2Y = (int)Math.floor(Math.random() * screenheight);  // random 0-1, floor-roundoff, to move the enemy up down randomly

            if(enemy2Y<=0){
                enemy2Y=0;
            }if(enemy2Y>=(screenheight-enemy2.getHeight())){
                enemy2Y= screenheight-enemy2.getHeight();
            }

        }
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);

        // enemy 3
        enemy3X =enemy3X - (screenwidth/120); // to make the enemy come closer towars x axis
        if(points>50&&points<=100){
            enemy3X =enemy3X - (screenwidth/100);
        }if(points>100&&points<=150){
            enemy3X =enemy3X - (screenwidth/90);
        }if(points>150){
            enemy3X =enemy3X - (screenwidth/80);
        }
        if(enemy3X<=0){
            enemy3X = screenwidth+100;  // to let the enemy come again after it goes out of screen
            enemy3Y = (int)Math.floor(Math.random() * screenheight);  // random 0-1, floor-roundoff, to move the enemy up down randomly

            if(enemy3Y<=0){
                enemy3Y=0;
            }if(enemy3Y>=(screenheight-enemy3.getHeight())){
                enemy3Y= screenheight-enemy3.getHeight();
            }

        }
        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);

        // coin1
        coin1X =coin1X - (screenwidth/130); // to make the enemy come closer towars x axis

        if(coin1X<=0){
            coin1X = screenwidth+200;  // to let the enemy come again after it goes out of screen
            coin1Y = (int)Math.floor(Math.random() * screenheight);  // random 0-1, floor-roundoff, to move the enemy up down randomly

            if(coin1Y<=0){
                coin1Y=0;
            }if(coin1Y>=(screenheight-coin1.getHeight())){
                coin1Y= screenheight-coin1.getHeight();
            }

        }
        coin1.setX(coin1X);
        coin1.setY(coin1Y);

        //coin 2


        coin2X =coin2X - (screenwidth/170); // to make the enemy come closer towars x axis

        if(coin2X<=0){
            coin2X = screenwidth+200;  // to let the enemy come again after it goes out of screen
            coin2Y = (int)Math.floor(Math.random() * screenheight);  // random 0-1, floor-roundoff, to move the enemy up down randomly

            if(coin2Y<=0){
                coin2Y=0;
            }if(coin2Y>=(screenheight-coin2.getHeight())){
                coin2Y= screenheight-coin2.getHeight();
            }

        }
        coin2.setX(coin2X);
        coin2.setY(coin2Y);


    }

    public  void collisionControl(){
        //enemy1 collision
        int centerenemy1X =enemy1X +enemy1.getWidth()/2;
        int centerenemy1Y = enemy1Y + enemy1.getHeight()/2;

        if(centerenemy1X >= birdX && centerenemy1X <birdX+bird1.getWidth()
                &&centerenemy1Y>=birdY &&centerenemy1Y<birdY+bird1.getHeight()){
            enemy1X = screenwidth+150;
            life--;

        }

        //enemy2 collision

        int centerenemy2X =enemy2X +enemy2.getWidth()/2;
        int centerenemy2Y = enemy2Y + enemy2.getHeight()/2;

        if(centerenemy2X >= birdX && centerenemy2X <birdX+bird1.getWidth()
                &&centerenemy2Y>=birdY &&centerenemy2Y<birdY+bird1.getHeight()){
            enemy2X = screenwidth+200;
            life--;

        }

        //enemy3collision

        int centerenemy3X =enemy3X +enemy3.getWidth()/2;
        int centerenemy3Y = enemy3Y + enemy3.getHeight()/2;

        if(centerenemy3X >= birdX && centerenemy3X <birdX+bird1.getWidth()
                &&centerenemy3Y>=birdY &&centerenemy3Y<birdY+bird1.getHeight()){
            enemy3X = screenwidth+150;
            life--;

        }

        //coin1 collision
        int centercoin1X =coin1X +coin1.getWidth()/2;
        int centercoin1Y = coin1Y + coin1.getHeight()/2;

        if(centercoin1X >= birdX && centercoin1X <birdX+bird1.getWidth()
                &&centercoin1Y>=birdY &&centercoin1Y<birdY+bird1.getHeight()){
            coin1X = screenwidth+150;
            points=points+10;

            livescore.setText("" +points);
        }

        //coin 2 collsion
        int centercoin2X =coin2X +coin2.getWidth()/2;
        int centercoin2Y = coin2Y + coin2.getHeight()/2;

        if(centercoin2X >= birdX && centercoin2X <birdX+bird1.getWidth()
                &&centercoin2Y>=birdY &&centercoin2Y<birdY+bird1.getHeight()){
            coin2X = screenwidth+150;
            points=points+10;

            livescore.setText(""+points);

        }

        if(life>0 && points<200){
            if(life==2){
                heart3.setImageResource(R.drawable.greyheart);
            }if(life==1){
                heart2.setImageResource(R.drawable.greyheart);
            }

            handler.postDelayed(runnable,15);  // the run method should run in the above condition only


        }else if(points>=200) {
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            startinfo.setVisibility(View.VISIBLE);
            startinfo.setText("Congratulations !! You have completed the Game");

            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {

                    birdX = birdX + (screenwidth/300);
                    bird1.setX(birdX);

                    bird1.setY( screenheight/2 );

                    if(birdX <= screenwidth){
                        handler2.postDelayed(runnable2,20);
                    }
                    else{
                        handler2.removeCallbacks(runnable2);

                        Intent intent = new Intent(GameActivity.this,ResultActivity.class);

                        intent.putExtra("scores" ,points);
                        startActivity(intent);
                        finish();
                    }


                }
            };

            handler2.post(runnable2);






        }else if(life==0){
            handler.removeCallbacks(runnable);
            heart3.setImageResource(R.drawable.greyheart);

            Intent intent = new Intent(GameActivity.this,ResultActivity.class);

            intent.putExtra("scores" ,points);
            startActivity(intent);

            finish();

        }

    }
}