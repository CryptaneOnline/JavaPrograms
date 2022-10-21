package com.example.assignemnt4;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv1=(ImageView)findViewById(R.id.imageView);
        final ImageView iv2=(ImageView)findViewById(R.id.imageView1);
        final Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        final Animation animZoomIn2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        final Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        final Animation animZoomOut2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        iv1.startAnimation(animZoomIn);
        iv2.startAnimation(animZoomOut2);
        animZoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                iv1.startAnimation(animZoomOut);
            }
            @Override
            public void onAnimationRepeat(Animation animation){ } });
        animZoomOut.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                iv1.startAnimation(animZoomIn);
            }
            @Override
            public void onAnimationRepeat(Animation animation){
            }
        });
        animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                iv2.startAnimation(animZoomOut2);
            }
            @Override
            public void onAnimationRepeat(Animation animation){
            }
        });
        animZoomOut2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation){
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                iv2.startAnimation(animZoomIn2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}