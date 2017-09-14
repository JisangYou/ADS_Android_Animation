package org.androidtown.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_move, btn_rotate, btn_scale, btn_alpha, btn_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btn_move = (Button) findViewById(R.id.btn_move);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_object = (Button) findViewById(R.id.btn_object);
    }

    private void initListener() {
        btn_move.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_alpha.setOnClickListener(this);
        btn_object.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_move:
                move();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_alpha:
                alpha();
                break;

            case R.id.btn_object:
                Intent intent = new Intent(this, ProAniActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void move() {
        // - View 애니메이션 실행하기
        //1. 애니메이션 xml 정의
        //2. AnimaitionUtil로 정의된 애니메이션을 로드
        //3. 로드된 애니메이션을 실제 위젯에 적용한다.
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        btn_object.startAnimation(animation);
    }

    private void rotate() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btn_object.startAnimation(animation);
    }

    private void scale() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        btn_object.startAnimation(animation);
    }

    private void alpha() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        btn_object.startAnimation(animation);
    }
}
