package mohammed.susu.com.flappyfish;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Pause_Activity extends AppCompatActivity {

    FlyingFishView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new FlyingFishView(this);

        // get values of FlyingFishView elements when game paused ..

        int yellowX = getIntent().getExtras().getInt("yellowBX");
        int yellowY = getIntent().getExtras().getInt("yellowBY");
        int greenX = getIntent().getExtras().getInt("greenBX");
        int greenY = getIntent().getExtras().getInt("greenBY");
        int blueX = getIntent().getExtras().getInt("blueBX");
        int blueY = getIntent().getExtras().getInt("blueBY");
        int redX = getIntent().getExtras().getInt("redBX");
        int redY = getIntent().getExtras().getInt("redBY");
        int orangeX = getIntent().getExtras().getInt("orangeBX");
        int orangeY = getIntent().getExtras().getInt("orangeBY");
        int fishX = getIntent().getExtras().getInt("fishX");
        int fishY = getIntent().getExtras().getInt("fishY");
        int lifeCounterOfFish = getIntent().getExtras().getInt("lifeOfFish");
        int scoree = getIntent().getIntExtra("sc" , 0);

        // set element values to show ..
        gameView.yellowX=yellowX;
        gameView.yellowY=yellowY;
        gameView.greenX=greenX;
        gameView.greenY=greenY;
        gameView.redX=redX;
        gameView.redY=redY;
        gameView.blueX=blueX;
        gameView.blueY=blueY;
        gameView.HeartX=orangeX;
        gameView.HeartY=orangeY;
        gameView.fishX=fishX;
        gameView.fishY=fishY;
        gameView.lifeCounterOfFish=lifeCounterOfFish;
        gameView.score = scoree;



        setContentView(gameView);

    }

    public void on_Resume_Game(MenuItem resumeItem)
    {
        onBackPressed();

    }

    public void on_mainMenu(MenuItem resumeItem)
    {


        final AlertDialog.Builder alert = new AlertDialog.Builder(Pause_Activity.this);
        alert.setTitle("Back To MainMenu");

        alert.setMessage("Are You Sure , You Want to go back to MainMenu ? Your Score will be lost ! ");
        alert.setCancelable(false);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent main = new Intent(getBaseContext() , Splash_Activity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(main);
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




   // option menu for RESUME ..
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater resumeInflator = getMenuInflater();
        resumeInflator.inflate(R.menu.menu_resume , menu);
        return  true;
    }


}
