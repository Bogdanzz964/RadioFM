package bogdan.spacefm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private TextView textView, textView1;
    private ImageView imageView;
    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_intro);

        textView = findViewById(R.id.upper_text);
        textView1 = findViewById(R.id.bottom_text);
        imageView = findViewById(R.id.logo_image);
        imageButton1 = findViewById(R.id.button_facebook);
        imageButton2 = findViewById(R.id.youtube_button);
        imageButton3 = findViewById(R.id.insta_button);
        imageButton4 = findViewById(R.id.twitter_button);

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("The rhythm of you city");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#000000"),
                        Color.parseColor("#000000"),

                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_intro);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.splash_text);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.bottomtext_anim_splash);
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.facebookbtn_anim_splash);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.youtubebtn_anim_splash);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.instagrambtn_anim_splash);
        Animation animation6 = AnimationUtils.loadAnimation(this, R.anim.twitterbtn_anim_splash);

        imageView.startAnimation(animation);
        textView.startAnimation(animation1);
        textView1.startAnimation(animation2);
        imageButton1.startAnimation(animation3);
        imageButton2.startAnimation(animation4);
        imageButton3.startAnimation(animation5);
        imageButton4.startAnimation(animation6);



        final Intent i = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {

            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
