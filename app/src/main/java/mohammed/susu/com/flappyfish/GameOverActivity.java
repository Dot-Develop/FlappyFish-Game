package mohammed.susu.com.flappyfish;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {

    private Button startGameAgain;
    private TextView display_Score ;
    private int score1 , score2 , score3 ;
    private TextView best_Score;
    private long backPressed;
    private  Toast backPressedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        startGameAgain = findViewById(R.id.play_Again_Button);
        display_Score = findViewById(R.id.score_TextView);
        best_Score = findViewById(R.id.best_Score);

        // getting the score points of the FlyingFishView java class , and displaying ..
        score1 = getIntent().getIntExtra("score1" , 0);
        score2 = getIntent().getIntExtra("score2" , 0);
        score3 = getIntent().getIntExtra("score3" , 0);





        boolean level1 = getIntent().getBooleanExtra("saveLevel1" , false);
       boolean level2 = getIntent().getBooleanExtra("saveLevel2" , false);
        boolean level3 = getIntent().getBooleanExtra("saveLevel3" , false);

        if(level1) {
            // loading data (best score points) which the user gets ..
            SharedPreferences settingsLoad = getSharedPreferences("SCORE" , MODE_PRIVATE);
            int high_Score = settingsLoad.getInt("HIGH_SCORE1" , 0);

            display_Score.setText("Your Score : "+ score1);

            if (score1 > high_Score) {
                best_Score.setText("Best Score (Easy) : " + score1);

                // save the data
                SharedPreferences.Editor editor = settingsLoad.edit();
                editor.putInt("HIGH_SCORE1", score1);
                editor.commit();
            } else {
                best_Score.setText("Best Score (Easy) : " + high_Score);
            }

        }

        else if(level2)
        {
            // loading data (best score points) which the user gets ..
            SharedPreferences settingsLoad = getSharedPreferences("SCORE" , MODE_PRIVATE);
            int high_Score = settingsLoad.getInt("HIGH_SCORE2" , 0);
            display_Score.setText("Your Score : "+ score2);

            if (score2 > high_Score) {
                best_Score.setText("Best Score (Medium) : " + score2);

                // save the data
                SharedPreferences.Editor editor = settingsLoad.edit();
                editor.putInt("HIGH_SCORE2", score2);
                editor.commit();
            } else {
                best_Score.setText("Best Score (Medium) : " + high_Score);
            }
        }

        else if(level3)
        {
            // loading data (best score points) which the user gets ..
            SharedPreferences settingsLoad = getSharedPreferences("SCORE" , MODE_PRIVATE);
            int high_Score = settingsLoad.getInt("HIGH_SCORE3" , 0);
            display_Score.setText("Your Score : "+ score3);

            if (score3 > high_Score) {
                best_Score.setText("Best Score (Hard) : " + score3);

                // save the data
                SharedPreferences.Editor editor = settingsLoad.edit();
                editor.putInt("HIGH_SCORE3", score3);
                editor.commit();
            } else {
                best_Score.setText("Best Score (Hard) : " + high_Score);
            }
        }

        // Play Again button Action , when user clicked ,, go to MainActivity ..
        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(GameOverActivity.this , Splash_Activity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);

            }
        });




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

    public void on_Click_Exit_Button(View v)
    {
        //moveTaskToBack(true);
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(1);

        final AlertDialog.Builder alert = new AlertDialog.Builder(GameOverActivity.this);
        alert.setTitle("Exit Game !");

        alert.setMessage("Are You Sure , You Want To Exit ");
        alert.setCancelable(false);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              finish();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });

        AlertDialog alertShow =  alert.create();
        alertShow.show();

    }



    }





