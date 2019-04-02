package sebastian.noren.da569a_gameproject;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static ArtEngine artEngine; // Bitmap object reference
    static GameEngine gameEngine; // GameEngine object reference
    static SoundEngine soundEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static float gravity;
    static Context gameAccContext;

    public static void initialization(Context context){
        setScreenSize(context);
        artEngine = new ArtEngine(context.getResources());
        gameEngine = new GameEngine();
        AppConstants.gravity = 1;
        soundEngine = new SoundEngine(context);

    }

    public static SoundEngine getSoundEngine(){
        return soundEngine;
    }

    // Return ArtEngine instance
    public static ArtEngine getArtEngine(){
        return artEngine;
    }

    // Return GameEngine instance
    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }

}
