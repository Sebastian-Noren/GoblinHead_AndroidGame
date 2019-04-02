package sebastian.noren.da569a_gameproject;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton diffButton;
    CheckBox sound;
    SharedPreferences sharedPreferences;
    public static int selectedID;
    public static boolean isChecked;
    public static final String KEY_SOUNDCHECK = "soundCheck_key";
    public static final String KEY_DIFFICULTY = "difficult_key";
    public static final String SHARED_PREFS = "sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        radioGroup = findViewById(R.id.difficulty);
        sound = (CheckBox) findViewById(R.id.soundCheckBox);

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getSoundEngine().buttonClick();
                if (sound.isChecked()) {
                    AppConstants.getSoundEngine().resumeIntroSound();
                } else {
                    AppConstants.getSoundEngine().pauseIntroSound();
                }
                saveData();
            }
        });

        loadData();
        updateData();
        diffButton = (RadioButton) findViewById(selectedID);
        diffButton.setChecked(true);
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        selectedID = radioGroup.getCheckedRadioButtonId();
        diffButton = (RadioButton) findViewById(selectedID);
        diffButton.setChecked(true);

        switch (selectedID) {
            //easy
            case R.id.easyCheckBox:
                if (diffButton.isChecked())
                    AppConstants.getSoundEngine().buttonClick();
                break;
            //Medium
            case R.id.medCheckBox:
                if (diffButton.isChecked())
                    AppConstants.getSoundEngine().buttonClick();
                break;
            //Hard
            case R.id.hardCheckBox:
                if (diffButton.isChecked())
                    AppConstants.getSoundEngine().buttonClick();
                break;
        }
        saveData();
    }

    public void saveData() {
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SOUNDCHECK, sound.isChecked());
        editor.putInt(KEY_DIFFICULTY, selectedID);
        editor.apply();
    }

    public void loadData() {
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        isChecked = sharedPreferences.getBoolean(KEY_SOUNDCHECK, true);
        selectedID = sharedPreferences.getInt(KEY_DIFFICULTY, R.id.easyCheckBox);
    }

    public void updateData() {
        sound.setChecked(isChecked);

    }

}


