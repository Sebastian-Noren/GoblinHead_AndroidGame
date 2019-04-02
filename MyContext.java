package sebastian.noren.da569a_gameproject;

import android.app.Application;
import android.content.Context;

public class MyContext extends Application {
    private static MyContext instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }
}