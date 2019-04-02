package sebastian.noren.da569a_gameproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {

    TextView killScore;
    Button mainBtn, replayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gameover);

        mainBtn = findViewById(R.id.mainbtn);
        replayBtn = findViewById(R.id.replayBtn);

        int score = getIntent().getExtras().getInt("score");
        killScore = findViewById(R.id.points);
        killScore.setText("" + score);
        AppConstants.getSoundEngine().gameOver();

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().stopGameEffectSound();
                AppConstants.getSoundEngine().gameOverStop();
                mainMenu();
            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().createGameSound();
                AppConstants.getSoundEngine().gameOverStop();
                AppConstants.getGameEngine().score = 0;
                startGame();
            }
        });
    }

    public void startGame() {
        Intent intent = new Intent(GameOver.this, PlayGameActivity.class);
        startActivity(intent);
        finish();
    }

    public void mainMenu() {
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
