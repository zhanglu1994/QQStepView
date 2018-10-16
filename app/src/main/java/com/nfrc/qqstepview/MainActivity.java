package com.nfrc.qqstepview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final QQStepView qqStepView = findViewById(R.id.step_view);

        qqStepView.setmStepMax(4000);




        //属性动画

        ValueAnimator valueAnimator =  ObjectAnimator.ofInt(0,3000);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentStep = (int) animation.getAnimatedValue();
                qqStepView.setmCurrentStep(currentStep);
            }
        });

        valueAnimator.start();

    }
}
