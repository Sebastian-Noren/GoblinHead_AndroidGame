package sebastian.noren.da569a_gameproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        AppConstants.initialization(this.getApplicationContext());

        Button playBtn = findViewById(R.id.playBtn);
        Button settBtn = findViewById(R.id.settBtn);
        Button helpBtn = findViewById(R.id.helpBtn);
        Button credBtn = findViewById(R.id.credBtn);
        Button exitBtn = findViewById(R.id.exitBtn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                AppConstants.getSoundEngine().stopIntroSound();
                startGame();
            }
        });

        settBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                openSettings();
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                openHelp();
            }
        });

        credBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                openCredits();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                System.exit(0);
            }
        });


    }

    public void startGame() {
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
        finish();
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openHelp() {
        Intent intent = new Intent(this, HowToActivity.class);
        startActivity(intent);
    }

    public void openCredits() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    AppConstants.getSoundEngine().stopIntroSound();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppConstants.getSoundEngine().createSoundMenu();
    }
}
