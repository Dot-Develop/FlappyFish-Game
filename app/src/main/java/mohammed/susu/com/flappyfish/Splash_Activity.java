package mohammed.susu.com.flappyfish;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Splash_Activity extends AppCompatActivity {

    private long backPressed;
    private  Toast backPressedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);







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

    public void on_easy(View v){
        Intent level1  = new Intent(this , MainActivity.class);
        level1.putExtra("levelEasy" ,1);
        startActivity(level1);


    }

    public void on_medium(View v){
        Intent level2 = new Intent(this , MainActivity.class);
        level2.putExtra("levelMedium" ,2);
        startActivity(level2);
    }

    public void on_hard(View v){
        Intent level3 = new Intent(this , MainActivity.class);
        level3.putExtra("levelHard" ,3);
        startActivity(level3);
    }


}
