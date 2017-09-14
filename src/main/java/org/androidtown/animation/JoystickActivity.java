package org.androidtown.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class JoystickActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout layoutground;
    private Button btn_Down, btn_Up, btn_Left, btn_Right,btn_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        init();
        initListener();

    }

    public void init(){
        btn_Down = (Button) findViewById(R.id.btn_Down);
        btn_Up = (Button) findViewById(R.id.btn_Up);
        btn_Left = (Button) findViewById(R.id.btn_Left);
        btn_Right = (Button) findViewById(R.id.btn_Right);
        btn_player = (Button) findViewById(R.id.btn_player);
        layoutground = (FrameLayout) findViewById(R.id.Layoutground);

    }

    public void initListener(){
        btn_Down.setOnClickListener(this);
        btn_Up.setOnClickListener(this);
        btn_Left.setOnClickListener(this);
        btn_Right.setOnClickListener(this);
        btn_player.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_Up :
                up();
                move();
                break;
            case R.id.btn_Down :
                down();
                move();
                break;
            case R.id.btn_Left :
                left();
                move();
                break;
            case R.id.btn_Right :
                right();
                move();
                break;
        }
    }

    int playerX = 0; // 플레이어의 위치 기준
    int playerY = 0;

    private void up(){
        playerY -=100; // 위치기준으로 -100으로 이동
    }
    private void down(){
        playerY +=100;
    }
    private void left(){
        playerX -=100;
    }
    private void right(){
        playerX +=100;
    }

    private void move(){
        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btn_player,"translationY", playerY
        );
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btn_player,"translationX", playerX
        );
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(aniY,aniX);
        aniSet.start();
    }
}
