# 애니메이션

## 종류

### move
- 이동하는 애니메이션

### rotate
-회전하는 애니메이션

### scale
- 크기가 커지거나 작아지는 애니메이션

### alpha
- 투명도를 조절하는 애니메이션

### 사용방법 예시

```Java
//1. 대상을 정의한다. >btn_GO
//2. 애니메이터를 설정한다.
y= y+100;
ObjectAnimator ani = ObjectAnimator.ofFloat(
        btn_go, //가 움직일 대상
        "translationY", //나. 애니메이션 속성(움직임)
        y             //다. 속성값(위치일 경우는 거리)
);
// 3. 애니메이터를 실행한다.
ani.start();
```

### 간단한 조이스틱 프로그램
```Java
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
```
- 위치를 나타내는 메서드(up,down,left,right)에 플레이어의 위치를 조절하는 로직을 넣음
- move메서드에 애니메이션 기능을 넣고, 해당 위치를 클릭하면 움직이게 구현
