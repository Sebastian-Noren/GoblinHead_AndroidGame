package sebastian.noren.da569a_gameproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import static android.content.Context.MODE_PRIVATE;
import static sebastian.noren.da569a_gameproject.SettingsActivity.KEY_DIFFICULTY;
import static sebastian.noren.da569a_gameproject.SettingsActivity.KEY_SOUNDCHECK;
import static sebastian.noren.da569a_gameproject.SettingsActivity.SHARED_PREFS;
import static sebastian.noren.da569a_gameproject.SettingsActivity.isChecked;
import static sebastian.noren.da569a_gameproject.SettingsActivity.selectedID;

public class GameEngine {

    private BackgroundImage backgroundImage;
    private GoblinHead goblinHead;
    Sword sword;
    private Blood blood;
    static int score;
    static GameState gameState;
    private Paint scorePaint, kills;
    private float diffSpeed;
    private int currentBloofFrame;
    boolean bloodSFXBool = false;


    public GameEngine() {
        backgroundImage = new BackgroundImage();
        goblinHead = new GoblinHead();
        sword = new Sword();
        blood = new Blood();
        gameState = GameState.NOPLAY;
        score = 0;
        currentBloofFrame = blood.getBloodFrame();
        Typeface tf = Typeface.createFromAsset(MyContext.getContext().getAssets(), "fonts/trixieplain.ttf");
        kills = new Paint();
        kills.setColor(Color.parseColor("#FFB26424"));
        kills.setTypeface(tf);
        kills.setTextSize(100);
        kills.setTextAlign(Paint.Align.LEFT);
        scorePaint = new Paint();
        scorePaint.setColor(Color.parseColor("#FFCC0000"));
        scorePaint.setTextSize(100);
        scorePaint.setTypeface(tf);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        loadData();

    }

    void updateEngine(Canvas canvas) {
        if (gameState == GameState.PLAYING) {
            switch (selectedID) {
                //easy
                case R.id.easyCheckBox:
                    diffSpeed = 0;
                    break;
                //Medium
                case R.id.medCheckBox:
                    diffSpeed = 2;
                    break;
                //Hard
                case R.id.hardCheckBox:
                    diffSpeed = 4;
                    break;
            }

            if (goblinHead.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getArtEngine().getGoblinHeadHeight())) {
                goblinHead.setVelocity(goblinHead.getVelocity() + AppConstants.gravity + diffSpeed);
                goblinHead.setY(goblinHead.getY() + goblinHead.getVelocity());
            }

            if (collison()) {
                score++;
                blood.setBloodX((int) goblinHead.getX());
                blood.setBloodY((int) goblinHead.getY() - AppConstants.getArtEngine().getGoblinHeadHeight() / 2);
                AppConstants.getSoundEngine().goblin();
                bloodSFXBool = true;
                goblinHead.resetPosistion();

            }
            if (goblinHead.getY() > (AppConstants.SCREEN_HEIGHT - AppConstants.getArtEngine().getGoblinHeadHeight() - 1)) {
                gameState = GameState.GAMEOVER;
                goblinHead.resetPosistion();
                AppConstants.getSoundEngine().stopGameBgSound();
                AppConstants.getSoundEngine().gameOverHit();
                Context context = AppConstants.gameAccContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        }
    }

    void draw(Canvas canvas) {
        if (gameState == GameState.PLAYING) {
            //Draw Background
            canvas.drawBitmap(AppConstants.getArtEngine().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);

            //Draw Sword
            canvas.drawBitmap(AppConstants.getArtEngine().getSword(), sword.getSwordX(), sword.getSwordY(), null);

            //Draw Score
            canvas.drawText("Kills:", 20, 110, kills);
            canvas.drawText("" + score, 350, 110, scorePaint);

            // Draw Goblin Frame
            int currentFrame = goblinHead.getCurrentFrame();
            canvas.drawBitmap(AppConstants.getArtEngine().getGoblinHead(currentFrame), goblinHead.getX(), goblinHead.getY(), null);
            currentFrame++;
            if (currentFrame > goblinHead.maxFrame) {
                currentFrame = 0;
            }
            goblinHead.setCurrentFrame(currentFrame);

            // draw bloodEffects
            if (bloodSFXBool) {
                canvas.drawBitmap(AppConstants.getArtEngine().getBlood(currentBloofFrame), blood.getBloodX(), blood.getBloodY(), null);
                currentBloofFrame++;
                if (currentBloofFrame > blood.maxFrame) {
                    currentBloofFrame = 0;
                    bloodSFXBool = false;
                }
                blood.setBloodFrame(currentBloofFrame);
            }

        } else {
            canvas.drawBitmap(AppConstants.getArtEngine().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
            canvas.drawText("Kills:", 20, 110, kills);
            canvas.drawText("" + score, 350, 110, scorePaint);
            canvas.drawBitmap(AppConstants.getArtEngine().getSword(), sword.getSwordX(), sword.getSwordY(), null);
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = MyContext.getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        isChecked = sharedPreferences.getBoolean(KEY_SOUNDCHECK, true);
        selectedID = sharedPreferences.getInt(KEY_DIFFICULTY, R.id.easyCheckBox);
    }

    boolean collison() {
        if (goblinHead.getX() + AppConstants.getArtEngine().getGoblinHeadWidth() >= sword.getSwordX() &&
                goblinHead.getX() <= sword.getSwordX() + AppConstants.getArtEngine().getSwordWidth() &&
                goblinHead.getY() <= sword.getSwordY() + AppConstants.getArtEngine().getSwordHeight() &&
                goblinHead.getY() + AppConstants.getArtEngine().getGoblinHeadHeight() >= sword.getSwordY()) {
            return true;
        } else {
            return false;
        }
    }


}
