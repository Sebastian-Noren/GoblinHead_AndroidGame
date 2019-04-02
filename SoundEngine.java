package sebastian.noren.da569a_gameproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static sebastian.noren.da569a_gameproject.SettingsActivity.KEY_SOUNDCHECK;
import static sebastian.noren.da569a_gameproject.SettingsActivity.SHARED_PREFS;
import static sebastian.noren.da569a_gameproject.SettingsActivity.isChecked;


class SoundEngine {

    Context context;
    private static int goblinPain1, goblinPain2, goblinPain3, goblinPain4, fleshHit, hit, menuClick, heroDeath, gameOver;
    private SoundPool soundPool, menuSoundPool, gameOverSoundPool;
    private AudioAttributes audio, audio2, audio3;
    private MediaPlayer bgSound, bgMenuSound;
    private Random randSound = new Random();
    private int length, gameLength;

    SoundEngine(Context context) {
        this.context = context;
        createSoundMenu();

    }

    void createSoundMenu() {
        loadData();
        if (isChecked) {

            if (bgMenuSound == null) {
                bgMenuSound = MediaPlayer.create(MyContext.getContext(), R.raw.intro);
                bgMenuSound.setVolume(0.5f, 0.5f);
                bgMenuSound.start();
                bgMenuSound.setLooping(true);
            }

            // start load introsound Stream
            if (menuSoundPool == null) {
                audio2 = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                menuSoundPool = new SoundPool.Builder().setAudioAttributes(audio2).setMaxStreams(2).build();
                menuClick = menuSoundPool.load(MyContext.getContext(), R.raw.click, 1);
            }
        }
    }

    void buttonClick() {
        if (menuSoundPool != null) {
            menuSoundPool.play(menuClick, 1, 1, 0, 0, 1);
        }
    }

    void stopIntroSound() {
        if (bgMenuSound != null && menuSoundPool != null) {
            bgMenuSound.release();
            bgMenuSound = null;
            menuSoundPool.release();
            menuSoundPool = null;
            audio2 = null;
        }
    }

    void resumeIntroSound() {
        if (bgMenuSound != null) {
            bgMenuSound.seekTo(length);
            bgMenuSound.start();
        }
    }

    void pauseIntroSound() {
        if (bgMenuSound != null) {
            bgMenuSound.pause();
            length = bgMenuSound.getCurrentPosition();
        }
    }

    void createGameSound() {
        loadData();
        if (isChecked) {
            if (soundPool == null) {
                audio = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                soundPool = new SoundPool.Builder().setAudioAttributes(audio).setMaxStreams(10).build();
                goblinPain1 = soundPool.load(MyContext.getContext(), R.raw.goblinpain1, 1);
                goblinPain2 = soundPool.load(MyContext.getContext(), R.raw.goblinpain2, 1);
                goblinPain3 = soundPool.load(MyContext.getContext(), R.raw.goblinpain3, 1);
                goblinPain4 = soundPool.load(MyContext.getContext(), R.raw.goblinpain4, 1);
                fleshHit = soundPool.load(MyContext.getContext(), R.raw.hitflesh, 1);
                hit = soundPool.load(MyContext.getContext(), R.raw.hit, 1);
            }
        }
    }

    void gameOverHit() {
        if (soundPool != null) {
            soundPool.play(hit, 1, 1, 0, 0, 1);

        }
    }

    void goblin() {
        if (soundPool != null) {
            soundPool.play(fleshHit, 1, 1, 0, 0, 1);
            switch (randSound.nextInt(4) + 1) {
                case 1:
                    soundPool.play(goblinPain1, 1, 1, 0, 0, 1);
                    break;
                case 2:
                    soundPool.play(goblinPain2, 1, 1, 0, 0, 1);
                    break;
                case 3:
                    soundPool.play(goblinPain3, 1, 1, 0, 0, 1);
                    break;
                case 4:
                    soundPool.play(goblinPain4, 1, 1, 0, 0, 1);
                    break;
            }
        }
    }

    void stopGameEffectSound() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
            audio = null;
        }
    }

    void createGameBgSound() {
        loadData();
        try {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    if (isChecked) {
                        if (bgSound == null) {
                            bgSound = MediaPlayer.create(context, R.raw.battlescore);
                            bgSound.start();
                            bgSound.setLooping(true);

                        }
                    }
                } // Exit Run
            });
            thread.start();
        } catch (Exception e) {
        }
    }

    void stopGameBgSound() {
        if (bgSound != null) {
            bgSound.stop();
            bgSound.release();
            bgSound = null;
        }
    }

    void resumeGameBgSound() {
        if (bgSound != null) {
            bgSound.seekTo(gameLength);
            bgSound.start();
        }
    }

    void pauseGameBgSound() {
        if (bgSound != null) {
            bgSound.pause();
            gameLength = bgSound.getCurrentPosition();
        }
    }


    void createGameOverSound() {
        loadData();
        if (isChecked) {
            if (gameOverSoundPool == null) {
                audio3 = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();
                gameOverSoundPool = new SoundPool.Builder().setAudioAttributes(audio3).setMaxStreams(5).build();
                gameOver = gameOverSoundPool.load(MyContext.getContext(), R.raw.gameover, 1);
                heroDeath = gameOverSoundPool.load(MyContext.getContext(), R.raw.death1, 1);
            }
        }
    }

    void gameOver() {
        if (gameOverSoundPool != null) {
            gameOverSoundPool.play(gameOver, 1, 1, 0, 0, 1);
            gameOverSoundPool.play(heroDeath, 1, 1, 0, 0, 1);
        }
    }


    void gameOverStop() {
        if (gameOverSoundPool != null) {
            gameOverSoundPool.release();
            gameOverSoundPool = null;
            audio3 = null;
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = MyContext.getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        isChecked = sharedPreferences.getBoolean(KEY_SOUNDCHECK, true);

    }

}
