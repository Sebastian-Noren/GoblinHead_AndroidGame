package sebastian.noren.da569a_gameproject;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class PlayGameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AppConstants.gameAccContext = this;
        GameView gameArea = new GameView(getApplicationContext());
        setContentView(gameArea);
        //Create the game sound
        AppConstants.getSoundEngine().createGameSound();
        AppConstants.getSoundEngine().createGameBgSound();
        AppConstants.getSoundEngine().createGameOverSound();
        AppConstants.getGameEngine().gameState = GameState.PLAYING;

        //SENSOR
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onResume() {
        super.onResume();
        AppConstants.getSoundEngine().createGameSound();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        AppConstants.getGameEngine().sword.setSwordX(((float)AppConstants.SCREEN_WIDTH/2) - ((float)AppConstants.getArtEngine().getSwordWidth()/2));
        AppConstants.getSoundEngine().resumeGameBgSound();
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        AppConstants.getSoundEngine().pauseGameBgSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConstants.getSoundEngine().stopGameEffectSound();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppConstants.getSoundEngine().createGameOverSound();
        AppConstants.getGameEngine().gameState = GameState.PLAYING;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        AppConstants.getGameEngine().sword.setSwordX(AppConstants.getGameEngine().sword.getSwordX() - event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
