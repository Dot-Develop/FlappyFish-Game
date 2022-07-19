package mohammed.susu.com.flappyfish;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    private long backPressed;
    private  Toast backPressedToast;
    // object reference of FlyingFishView class ..
    private FlyingFishView gameView;



    // Handler Object to handle a new message to the Thread ..
    private Handler handler = new Handler();
    // the interval of TimerTask to be executed in milliSecond
    private final static long Interval = 23;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int level1 = getIntent().getIntExtra("levelEasy" ,0);
        int level2 = getIntent().getIntExtra("levelMedium",0);
        int level3 = getIntent().getIntExtra("levelHard" ,0);

        gameView = new FlyingFishView(this);



        if(level1==1) {
            gameView.level1 = true;
            setContentView(gameView);

        }
        else if(level2==2) {

            gameView.level2 =true;
            setContentView(gameView);
        }
        else if(level3==3) {

            gameView.level3 =true;
            setContentView(gameView);
        }

/*

        // initializing new Thread to be executed in milliSecond
        final Thread thread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    // sleep for 3 Seconds the Main Thred before starting ..
                   // sleep(3000);

                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }

                finally
                {


                    // if user does not pressed home Button then go to the main Intent (main Activity).
                   // if(!onPause)
                   // {
                     //   Intent mainIntent = new Intent(Splash_Activity.this,MainActivity.class);
                      //  startActivity(mainIntent);
                    //}

                }
            }
        };

        thread.start();

        */

        // object from Timer class and throwing a new timer Task ..
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                // handling a new message to the thread that attached to like(changing the UI elemnts(widgets ..))..
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // invalidate method to re-Drawing the UI elemnts and Frameworks that has been changed
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }

    @Override
    public void onBackPressed() {

        if(backPressed+500 > System.currentTimeMillis())
        {
            backPressedToast.cancel();
            super.onBackPressed();
            return;
        }
        else
            {
               backPressedToast = Toast.makeText(getBaseContext() , "Press back again to EXIT" , Toast.LENGTH_SHORT);
               backPressedToast.show();
            }
        backPressed = System.currentTimeMillis();

    }

    // option menu for PAUSE .
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater pauseMenu = getMenuInflater();
        pauseMenu.inflate(R.menu.menu_pause, menu);

        return true;
    }

    // sending values of FlyingFishView elements to an new Intent when game paused ..
    public void on_Pause_Game(MenuItem pauseItem)
    {

    Intent pause = new Intent(getBaseContext() , Pause_Activity.class);

    int scoree =gameView.score;
    pause.putExtra("sc",scoree);

        pause.putExtra("yellowBX" ,gameView.yellowX);
        pause.putExtra("yellowBX" ,gameView.yellowX);
        pause.putExtra("yellowBY" ,gameView.yellowY);
        pause.putExtra("greenBX" ,gameView.greenX);
        pause.putExtra("greenBY" ,gameView.greenY);
        pause.putExtra("blueBX" ,gameView.blueX);
        pause.putExtra("blueBY" ,gameView.blueY);
        pause.putExtra("redBX" ,gameView.redX);
        pause.putExtra("redBY" ,gameView.redY);
        pause.putExtra("orangeBX" ,gameView.HeartX);
        pause.putExtra("orangeBY" ,gameView.HeartY);

        pause.putExtra("fishX" ,gameView.fishX);
        pause.putExtra("fishY" ,gameView.fishY);
        pause.putExtra("lifeOfFish" ,gameView.lifeCounterOfFish);

        startActivity(pause);


    }


}
