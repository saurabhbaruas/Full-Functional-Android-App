package androidjavatech4u.com.androjavatech4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView=(ImageView)findViewById(R.id.imageView2);

        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                        }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });











    }
}
