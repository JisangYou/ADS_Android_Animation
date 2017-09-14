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
        //LinearInterpolator 외에도 다양한 것이 있다.
        //점점 빠르게 :
        //점점 느리게:
        // 위 둘을 동시에
        // 시작 위치에서 조금뒤로 당겼다 이동
        //도착위치를 조금 지나쳤다가 도착위치로 이동
        //위둘을 동시에
        //도착위치에서 튕김
        aniSet.start();

    }

    public void goJoyStick(View view) {


    }
}
