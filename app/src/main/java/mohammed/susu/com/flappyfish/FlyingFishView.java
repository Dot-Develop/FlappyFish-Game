package mohammed.susu.com.flappyfish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import java.util.Properties;

import static android.content.Context.VIBRATOR_SERVICE;

public class FlyingFishView extends View {

    private MediaPlayer playSoundCoin;
    private MediaPlayer playSoundRed;
    private MediaPlayer playSoundHeal;

    public  boolean level1;
    public  boolean level2;
    public  boolean level3;


    // array of the two Fishes (open and closed ) arms
    private Bitmap fish[] = new Bitmap[2];
// variable for background Image
   // private  Bitmap backgroundImage;
  //  private Bitmap splash_UnderWater;

// array of the fish's life (full and empty hearts ..)
    private Bitmap life[] = new Bitmap[2];

// fish player start position x and y coordinates
    protected int fishX = 20;
    protected int fishY ;
    // the maximum height of the fish that can move on it (on Y Axis)
    public int maximum_fishY;
    public  int minimum_fishY;

// fish jump speed
    public int fishSpeed;
// width and height of the canvas
    private  int canvas_Width;
    private  int canvas_Height;
// touch variable for onTouchEvent Method and jumping the fish if (true)
    private boolean touch = false;

// initializing variables to the x , y and the Speed of the yellow Ball
    protected int yellowX , yellowY , yellowSpeed = 5 ;



    // initializing variables to the x , y and the Speed of the blue Ball
    protected int blueX , blueY , blueSpeed = 6 ;

    // initializing variables to the x , y and the Speed of the orange Ball
    protected int HeartX , HeartY , HeartSpeed = 22 ;


    // initializing variables to the x , y and the Speed of the green Ball
    protected int greenX , greenY  , greenSpeed = 7 ;


    // initializing variables to the x , y and the Speed of the red Ball
    protected int redX , redY , redSpeed = 6 ;

    // object of class Paint for adding elements (label , textview ..etc)
    private Paint scorePaint = new Paint();

// object of Paint class for the yellow Ball
    private Paint yellowBallPaint = new Paint();

    // object of Paint class for the blue Ball
    private Paint blueBallPaint = new Paint();
// object of Paint class for the green Ball
    private Paint greenBallPaint = new Paint();
    // object of Paint class for the green Ball
    private Paint redBallPaint = new Paint();

 // object of Paint class for the RED LINE
   // private Paint redLine = new Paint();

    // object of Paint class for the orange Ball
  //  private Paint orangeBallPaint = new Paint();



    // for increasing the speed of the balls ..
    public int Score_Up_Increase_Speed = 20;
    // score variable
    protected int score ;
    // life of the fish which will be 3 Hearts ..
    protected   int lifeCounterOfFish ;

   // private  int red_Line_crash = 0;

    // object of Vibrator class ..
    private Vibrator vibrator;

    // for background and the new underwater design Tree ..
    private Bitmap array_splash [] = new Bitmap [2];



    private  Context con;
// Constructor of FlyingFishView class
    public FlyingFishView(Context context)
    {

        super(context);
    this.con = context;

    // importing the fishes from drawable Folder into the array of fish
        fish [0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish [1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

    // importing background Image of the Main GameView
        //backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        // splash_UnderWater = BitmapFactory.decodeResource(getResources() , R.drawable.splash_underwater);

        array_splash [0] = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        array_splash [1] = BitmapFactory.decodeResource(getResources(),R.drawable.new_design);



    // setting the score label properties to show on the Main GameView
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(80);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);



    //  setting yellow ball properties ..
        yellowBallPaint.setColor(Color.YELLOW);
        yellowBallPaint.setAntiAlias(false);

        //  setting blue ball properties ..
        blueBallPaint.setColor(Color.rgb(0,187,255));
        blueBallPaint.setAntiAlias(false);

        //  setting green ball properties ..
        greenBallPaint.setColor(Color.GREEN);
        greenBallPaint.setAntiAlias(false);

        //  setting orange ball properties ..
       // orangeBallPaint.setColor(Color.rgb(255,165,0));
      //  orangeBallPaint.setAntiAlias(false);


        //  setting green ball properties ..
        redBallPaint.setColor(Color.rgb(190 , 0 , 0));
        redBallPaint.setAntiAlias(false);


    /* setting The Red Line Properties..
        redLine.setColor(Color.RED);
        redLine.setAntiAlias(true);
        redLine.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        redLine.setStrokeWidth(5f);
        redLine.setStyle(Paint.Style.FILL_AND_STROKE); */

    // importing the fish's life (hearts ) full and empty
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);


        // the Position of the Fish Player on the Y axis
         fishY = 550;
        // initializing score Points ..
         score = 0;
        // number of the Hearts (life of fish)
        lifeCounterOfFish = 3;


    }

    // onDraw method for showing the canvas elements like the(fish , background Image , motion of the fish..)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    // general width and height of the canvas
        canvas_Width = canvas.getWidth();
        canvas_Height = canvas.getHeight();

    // setting the background image
       canvas.drawBitmap(array_splash[0] ,0,0,null);
        canvas.drawBitmap(array_splash[1]  , 0 , 1670 , null);

    // the minimum height of the fish that can move on it (on Y Axis)
         minimum_fishY = fish[0].getHeight() -100;
    // the maximum height of the fish that can move on it (on Y Axis)
         maximum_fishY = canvas_Height - fish[0].getHeight() * 2;
    // updating the current position of the fish(Player)
        fishY = fishY + fishSpeed;
    // re-setting the minimum and maximum height of the fish(Player) on Y Axis
        if(fishY < minimum_fishY)
        {
            fishY = minimum_fishY;
        }

        if(fishY > maximum_fishY)
        {
            fishY = maximum_fishY;
           // Toast.makeText(con , "Touch Screen !" , Toast.LENGTH_SHORT).show();

            // vibrate when player got down to thr RED LINE
           // vibrate(con);
           // play_Sound_Red(con);
            // release the sound to get enough memory ..
          //  playSoundRed.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            //    @Override
           //     public void onCompletion(MediaPlayer mp) {
            //        mp.release();
            //    }
          //  });
            // and make player get away from the RED Line , to not damage the score ..


            fishSpeed = -20;
            fishSpeed = fishSpeed + 2;
            canvas.drawBitmap(fish[1] , fishX , fishY-50 , null);
        // decreasing score and heals ..
           // score = score - 1;

           // if(score >= 3)
           // {
          //      red_Line_crash = red_Line_crash +1;
          //  }
           // if(score < 0)
           // {
            //    lifeCounterOfFish = lifeCounterOfFish -1;
            //    scorePaint.setColor(Color.RED);
            //}

        }



        // for easy level

        if(level1) {

            if(score<=2){
            yellowSpeed = 3;
            greenSpeed = 3;
            blueSpeed = 3;
            redSpeed = 3;
            HeartSpeed = 10;}
            // increasing the speed of the balls when player collects more score points ..
            if (score >= Score_Up_Increase_Speed) {

                yellowSpeed = yellowSpeed + 1;
                greenSpeed = greenSpeed + 1;
                redSpeed = redSpeed + 1;
                blueSpeed = blueSpeed + 1;
                Score_Up_Increase_Speed = Score_Up_Increase_Speed + 30;

            }
        }
        // for medium level
        if (level2)
        {
            if(score<=2){
                yellowSpeed = 4;
                greenSpeed = 5;
                blueSpeed = 4;
                redSpeed = 4;
                HeartSpeed = 10;}
            // increasing the speed of the balls when player collects more score points ..
            if (score >= Score_Up_Increase_Speed) {
                yellowSpeed = yellowSpeed + 1;
                greenSpeed = greenSpeed + 2;
                redSpeed = redSpeed + 2;
                blueSpeed = blueSpeed + 1;
                Score_Up_Increase_Speed = Score_Up_Increase_Speed + 30;

            }
        }

        // for hard level
        if (level3)
        {
            if(score<=2){
                yellowSpeed = 5;
                greenSpeed = 5;
                blueSpeed = 5;
                redSpeed = 5;
                HeartSpeed = 12;}
            // increasing the speed of the balls when player collects more score points ..
            if (score >= Score_Up_Increase_Speed) {
                yellowSpeed = yellowSpeed + 1;
                greenSpeed = greenSpeed + 1;
                redSpeed = redSpeed + 3;
                blueSpeed = blueSpeed + 1;
                Score_Up_Increase_Speed = Score_Up_Increase_Speed + 30;

            }
        }

      //  if(score >= 0)
       // {
       //     scorePaint.setColor(Color.WHITE);
      //  }


        // go to game over activity if player died with the 3 hearts ..
        if(lifeCounterOfFish == 0)
        {
            if(level1)
            {
                level1=false;
           Intent  gameOverIntent = new Intent(getContext() , GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameOverIntent.putExtra("score1" , score);
            gameOverIntent.putExtra("saveLevel1" , true);
            getContext().startActivity(gameOverIntent);

            }

        else if (level2)
        {
            level2=false;
            Intent gameOverIntent = new Intent(getContext() , GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameOverIntent.putExtra("score2" , score);
            gameOverIntent.putExtra("saveLevel2" , true);
            getContext().startActivity(gameOverIntent);
        }
        else if(level3)
            {
                level3=false;
                Intent gameOverIntent = new Intent(getContext() , GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score3" , score);
                gameOverIntent.putExtra("saveLevel3" , true);
                con.startActivity(gameOverIntent);
            }

        }
        // decrease heal of the fish if player got down
        /*
        if(red_Line_crash == 4)
        {
            lifeCounterOfFish = lifeCounterOfFish -1;
            red_Line_crash = 0;
        }
        */

        // HHHHHHHHHHHHHHHEEEEEEEEEEEAAAAAAAAAAAAAARRRRRRRRRRRRRTTTTTTTTTTTT    HEART BALL ..
         if(lifeCounterOfFish ==1)
        {
            HeartX = HeartX - HeartSpeed;

            // check the position of fish and orange ball to match and get Points
            if(hitBallCheck(HeartX , HeartY))
            {
                play_Sound_Heal();
                // release the sound to get enough memory ..
                playSoundHeal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });

                lifeCounterOfFish = lifeCounterOfFish +1;

                // minimizing the position of x from orange Ball to hide from the screen and get another ball from different Y position
                HeartX = -100;
            }



            // re-setting a new position of x and y Axis of the orange ball when it disappeared ..
            if(HeartX < 0)
            {
                HeartX = canvas_Width + 4000;
                HeartY = (int) Math.floor(Math.random() * ((maximum_fishY - minimum_fishY)-50) ) + (minimum_fishY + 80) ;
            }

            // drawing the Heart ball
            canvas.drawBitmap(life[0] , HeartX , HeartY , null);

        }




    // the speed Of the fish MOVEMENT
       fishSpeed = fishSpeed + 2;


    // re-Positioning the fish on Y Axis when Screen Touched
        if(touch)
        {
            canvas.drawBitmap(fish[1] , fishX , fishY , null);
            touch = false;
        }
        else
        {

            canvas.drawBitmap(fish[0] , fishX , fishY , null);

        }

        // YYYYYYYYYYYYYYYYYYYLLLLLLLLLLLLLLLLLOOOOOOOOOOOOOOWWWWWWWWWWWWWWW   YELLOW BALL ..

    // yellow ball position on X Axis with the speed
        yellowX = yellowX - yellowSpeed ;

        // check the position of fish and yellow ball to match and get Points
        if(hitBallCheck(yellowX , yellowY))
        {
            play_Sound_Coin();
            // release the sound to get enough memory ..
            playSoundCoin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            score = score + 2;

            // minimizing the position of x from yellow Ball to hide from the screen and get another ball from different Y position
            yellowX = -100;
        }



        // re-setting a new position of x and y Axis of the yellow ball when it disappeared ..
        if(yellowX < 0)
        {
            yellowX = canvas_Width + 20;
            yellowY = (int) Math.floor(Math.random() * ((maximum_fishY - minimum_fishY)-50) ) + (minimum_fishY + 80) ;
        }

        // drawing the yellow ball
        canvas.drawCircle(yellowX , yellowY , 20 , yellowBallPaint);



        // BBBBBBBBBBLLLLLLLLLLLLLLLLUUUUUUUUUUUUUUEEEEEEEEEEEEE   BLUE BALL ...

        // yellow ball position on X Axis with the speed
        blueX = blueX - blueSpeed ;

        // check the position of fish and yellow ball to match and get Points
        if(hitBallCheck(blueX , blueY))
        {
            play_Sound_Coin();
            // release the sound to get enough memory ..
            playSoundCoin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            score = score + 3;

            // minimizing the position of x from yellow Ball to hide from the screen and get another ball from different Y position
            blueX = -100;
        }



        // re-setting a new position of x and y Axis of the blue ball when it disappeared ..
        if(blueX < 0)
        {
            blueX = canvas_Width + 22;
            blueY = (int) Math.floor(Math.random() * ((maximum_fishY - minimum_fishY)-50) ) + (minimum_fishY + 80) ;
        }

        // drawing the blue ball
        canvas.drawCircle(blueX , blueY , 24 , blueBallPaint);



// GGRReeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnnnn  GREEN BALL ...
        // green ball position on X Axis with the speed
        greenX = greenX - greenSpeed;

        // check the position of fish and green ball to match and get Points
        if(hitBallCheck(greenX , greenY))
        {
           play_Sound_Coin();
            // release the sound to get enough memory ..
            playSoundCoin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mp) {
                   mp.release();
               }
           });


            score = score + 4;
            // minimizing the position of x from green Ball to hide from the screen and get another ball from different Y position
            greenX = -100;
        }



        // re-setting a new position of x and y Axis of the green ball when it disappeared ..
        if(greenX < 0)
        {
            greenX = canvas_Width + 20;
            greenY = (int) Math.floor(Math.random() * ((maximum_fishY - minimum_fishY)-50) ) + (minimum_fishY + 80) ;
        }

        // drawing the green ball
        canvas.drawCircle(greenX , greenY , 27 , greenBallPaint);


// RREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEDDDDDDDDDDDDDD   RED BALL ..

        // red ball position on X Axis with the speed
        redX = redX - redSpeed;

        // check the position of fish and red ball to match and decrease health of the fish
        if(hitBallCheck(redX , redY))
        {
            play_Sound_Red(con);
            // release the sound to get enough memory ..
            playSoundRed.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            // minimizing the position of x from red Ball to hide from the screen and get another ball from different Y position
            redX = -100;
            vibrate(con);

            // decrementing the number of the hearts when damaged by the red ball
            lifeCounterOfFish --;


        }



        // re-setting a new position of x and y Axis of the red ball when it disappeared ..
        if(redX < 0)
        {
            redX = canvas_Width + 20;
            redY = (int) Math.floor(Math.random() * ((maximum_fishY - minimum_fishY)-170) ) + (minimum_fishY + 80) ;
        }

        // drawing the green ball
        canvas.drawCircle(redX , redY , 32 , redBallPaint);

        // drawing the Red Line on the start and end of  x,y position
      //  canvas.drawLine(0 , 1684 ,canvas_Width ,1684, redLine);


        // drawing the text Label(Score) on Upper Left Corner
        canvas.drawText("Score : "+score,15 , 80 ,scorePaint);



        //  changing life of the fish when damaged by the Red Ball,
        for (int i = 0 ; i < 3 ; i++)
        {
            // position of the life of the fish (Hearts)..
            int x = (int) (650 + life[0].getWidth() * 1.5 * i);
            int y = 20;

            if(i < lifeCounterOfFish)
            {
                canvas.drawBitmap(life[0] , x , y , null);
            }

            else
            {
                canvas.drawBitmap(life[1] , x , y , null);

            }
        }





    }

    // check the current posiotion of fish and the ball to match ..
    public  boolean hitBallCheck( int x , int y)
    {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {

            return true ;
        }
        return false ;
    }



    // onTouchEvent method for returning value whether the Player or(Screen) Touched or not To MOVE ..
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {



        // user ,pressed screen action
        float pressure = event.getPressure();
        // user touch screen action
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
                fishSpeed = -23;

        }
        // pressure between 0 and 1 return touch 'true' and get a speed for the fish
        if(pressure >=0 && pressure <=1)
            touch = true;
         // giving value to fishSpeed for the amount of speed MOVEMENT of The (Player) on Y Axis
            fishSpeed = -20;

        return true ;

    }


   //vibrate for the red ball enemy..
    public void vibrate(Context con)
    {
        vibrator = (Vibrator) con.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(100);




    }

    public void play_Sound_Red(Context con)
    {
         playSoundRed = MediaPlayer.create(con , R.raw.sound_red_ball);
        playSoundRed.start();
        playSoundRed.seekTo(800);
    }

    public void play_Sound_Coin()
    {
         playSoundCoin = MediaPlayer.create(con , R.raw.sound_coin);
        playSoundCoin.start();
    }

    public void play_Sound_Heal()
    {
        playSoundHeal = MediaPlayer.create(con , R.raw.sound_eating_heals);
        playSoundHeal.start();
    }


}



