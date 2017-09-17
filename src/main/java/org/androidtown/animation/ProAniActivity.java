package org.androidtown.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class ProAniActivity extends AppCompatActivity {

    Button btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_ani);
        btn_go = (Button) findViewById(R.id.btn_go);
    }

    float y = 0;

    public void move(View view) {
//        //1. 대상을 정의한다. >btn_GO
//        //2. 애니메이터를 설정한다.
//        y= y+100;
//        ObjectAnimator ani = ObjectAnimator.ofFloat(
//                btn_go, //가 움직일 대상
//                "translationY", //나. 애니메이션 속성(움직임)
//                y             //다. 속성값(위치일 경우는 거리)
//        );
//        // 3. 애니메이터를 실행한다.
//        ani.start();


        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btn_go,
                "translationY",
                100
        );

        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btn_go,
                "translationY",
                100
        );
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY, aniX);
        aniSet.setDuration(3000);
        aniSet.setInterpolator(new LinearInterpolator());

        aniSet.start();

    }

    public void goJoyStick(View view) {


    }
}
