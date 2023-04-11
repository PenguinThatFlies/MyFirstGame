package com.example.phoneinfo;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
public class SplashScreen extends AppCompatActivity {
    private ImageButton play_bt, stop_bt;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_speach);

        View image = findViewById(R.id.imageView);
        View logo = findViewById(R.id.textView);
        View slogan = findViewById(R.id.textView2);
        View play = findViewById(R.id.openAction);
        View play_btn = findViewById(R.id.playbtn);
        View pasue_btn = findViewById(R.id.stopbtn);


        //Animations
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        Animation bottomAnimBtn = AnimationUtils.loadAnimation(this, R.anim.play_now);
        Animation playAnimBtn = AnimationUtils.loadAnimation(this, R.anim.play_pause);

        //Set animation to elements
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        play.setAnimation(bottomAnimBtn);
        play_btn.setAnimation(playAnimBtn);


        //new Handler().postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        //        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //        finish();
        //    }
        //}, 2800);

        play_bt = (ImageButton) findViewById(R.id.playbtn);
        stop_bt = (ImageButton) findViewById(R.id.stopbtn);

        View b = findViewById(R.id.playbtn);
        b.setVisibility(View.VISIBLE);
    }

    public void play_btn(View view){
        play_bt.setVisibility(View.INVISIBLE);
        stop_bt.setVisibility(View.VISIBLE);
        player = MediaPlayer.create(this, R.raw.videoplayback);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop_btn();
            }
        });
        player.start();
    }

    public void stop_btn(){
        play_bt.setVisibility(View.VISIBLE);
        stop_bt.setVisibility(View.INVISIBLE);
        if (player != null) {
            player.release();
            player = null;
            //Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause(View view) {
        if (player != null) {
            player.pause();
        }
    }
    public void openActivity2(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
