package com.company.project12birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView bird,enemy1,enemy2,enemy3,coin,volume;
    private Button startgame;

    private Animation animation;
    private MediaPlayer mediaPlayer;
    Boolean status =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bird = findViewById(R.id.bird);
        enemy1 = findViewById(R.id.sprite1);
        enemy2 = findViewById(R.id.sprite2);
        enemy3 = findViewById(R.id.sprite3);
        coin = findViewById(R.id.coins);
        volume = findViewById(R.id.volumecontrol);
        startgame = findViewById(R.id.startgamebtn);


        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim);
        bird.setAnimation(animation);
        enemy1.startAnimation(animation);
        enemy2.setAnimation(animation);
        enemy3.setAnimation(animation);
        coin.setAnimation(animation);


        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                volume.setImageResource(R.drawable.ic_baseline_volume_up_24);

                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        // in resume because if we open the main act after the game act on create method wont be called
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.birdschirp);
        mediaPlayer.start();
//        if(mediaPlayer.getCurrentPosition()==mediaPlayer.getDuration()){
//            mediaPlayer.seekTo(0);
//            mediaPlayer.start();
//        }

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!status){ // isplaying not used because when clicked on the volume button , it will make it off but music still plays in bg
                    volume.setImageResource(R.drawable.ic_baseline_volume_off_24);

                    mediaPlayer.setVolume(0,0);

                    status=true;
                }else{
                    status= false;
                    volume.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    mediaPlayer.setVolume(1,1);
                }

            }
        });




    }


}