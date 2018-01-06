# ADS04 Android

## 수업 내용
- View Animation
- Property Animation
- 조이스틱을 만들어 뷰를 움직이는 프로그램

## Code Review

1. MainActivity

```java
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
            //view에서 id를 얻어서 분기
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
```
- 각 버튼마다 리스너를 달아주고, 버튼에 해당하는 이벤트를 따로 메소드로 정리함.
- anim 폴더에서 불러와서 구현하는 애니메이션
- /res/anim 폴더 밑에 두어야 하며, 확장자를 xml로 만들어야 함.
- ex)
```Java
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
android:duration="3000"
android:fromXDelta="0"
android:fromYDelta="0"
android:toXDelta="100"
android:fillAfter="true"
android:toYDelta="300">
<!--
fillAfter = true 일 경우 애니메이션의 종료위치에 고정, false 일 경우 원래위치로 복귀(default = false)
duration =1/1000 초

translate-애니메이션의 종류
-->
</translate>
```

2. ProAniActivity

```Java
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

        Intent intent = new Intent(this, JoystickActivity.class);
        startActivity(intent);

    }
}
```

- 버튼이 움직이는 애니메이션 
- 코드로 애니메이션을 구현하는 방법

3. JoystickActivity

```Java
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

```

- 조이스틱 예제
- 각 버튼을 클릭할때마다 해당 이벤트로 변수 plyaerX,playerY의 값이 업데이트되고, 이게 애니메이션에 반영이 되는 흐름
- 코드로 애니메이션을 구현하는 방법

## 보충설명

- 애니메이션의 종류 - ViewAnimation, PropertyAnimation
- 애니메이션을 구현하는 방법은
- 1) Xml 설정 후 Java 코드에서 Load하는 방법
- 2) Java 코드에서 설정 후 Load하는 방법

### ViewAnimation

#### 특징

- 애니메이션 후 대상의 외관만 변경된다.
- 버튼이 이동 후 이동 위치에 클릭 불가능(버튼모양만 이동, 실제 버튼은 이전 위치 존재)
- View 사용 가능
- 코드가 간결하고 셋팅 시간이 짧다


### PropertyAnimation

#### 특징

- 애니메이션 후 대상의 외관과 속성 모두 변경
- 버튼이 이동 후 이동 위치에서 클릭 가능
- View와 non-View 모두 사용 가능
- 코드양이 많고 셋팅기간이 길다.

-------------------------------------------------------------------------------------------------------------------------------------

### TweenAnimation

>> 보여줄 대상을 적절하게 연산한 후 그 결과를 연속적으로 디스플레이하는 방식

- Scale - 확대/축소
- Translate - 위치 이동
- Rotate - 회전
- Alpha - 투명도
- Set - 집합(여러가지 애니메이션을 같이 사용할때 사용)
- Interpolator - 추가적인 속성

>> 사용법
- [ 프로젝트폴더/res/anim ] 에 xml 파일을 만들어서 사용함

### 참고

1. Animation class에도 이벤트가 존재함

```Java
animation.setAnimationListener(new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {
        // 애니메이션이 시작 할 때
    }
 
    @Override
    public void onAnimationEnd(Animation animation) {
        // 애니메이션이 끝났을 때
    }
 
    @Override
    public void onAnimationRepeat(Animation animation) {
        // 애니메이션이 반복 할 때
    }
});

```

2. Interpolator

```Java
속도가 동일하게 이동 : linear_interpolator
점점 빠르게 이동 : accerlerate_interpolator
점점 느리게 이동 : decelerate_interpolator
위 둘을 동시에 : accerlerate_decelerate_interpolator
시작위치에서 조금 뒤로 당겼다 이동 : anticipate__interpolator
도착위치를 조금 지나쳤다가 도착위치로 이동 : overshoot_interpolator
위 둘을 동시에 : anticipate_overshoot_interpolator
도착위치에서 튕김 : bounce_interpolator
```



#### 출처: http://codeman77.tistory.com/68 [☆]
#### 출처: http://heepie.tistory.com/73

## TODO

- 추후 필요할 떄 볼것

## Retrospect

- 단순하지만 간단한 조이스틱 프로그램을 만들어보니 게임을 한번 만들어 보고 싶다는 생각이 들었음.

## Output

- 생략



