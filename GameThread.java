package sebastian.noren.da569a_gameproject;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder; //Surfaceholder object reference
    private boolean isRunning; // Flag to detect whether the thread is running or not
    private long startTime, loopTime; // Loop start time and loop duration
    private long DELAY = 30; // Delay in milliseconds between screen refreshes

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run() {
        // Looping until the boolean is false
        while (isRunning) {
            startTime = SystemClock.uptimeMillis();
            //locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null) {
                synchronized (surfaceHolder) {
                    AppConstants.getGameEngine().draw(canvas);
                    AppConstants.getGameEngine().updateEngine(canvas);


                    //unlocking the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
            // Pausing here to make sure update the right amount per second
            if (loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // Return whether the thread is running
    public boolean isRunning() {
        return isRunning;
    }

    // Sets the thread state, false = stopped, true = running
    public void setIsRunning(boolean state) {
        isRunning = state;
    }
}
