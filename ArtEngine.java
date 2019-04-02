package sebastian.noren.da569a_gameproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ArtEngine {

    Bitmap background;
    Bitmap sword;
    Bitmap[] goblinHead;
    Bitmap bloodPool[];


    public ArtEngine(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.bg);
        sword = BitmapFactory.decodeResource(res,R.drawable.sword);
        background = scaleImage(background);
        goblinHead = new Bitmap[4];
        goblinHead[0] = BitmapFactory.decodeResource(res, R.drawable.goblin_frame1);
        goblinHead[1] = BitmapFactory.decodeResource(res, R.drawable.goblin_frame2);
        goblinHead[2] = BitmapFactory.decodeResource(res, R.drawable.goblin_frame3);
        goblinHead[3] = BitmapFactory.decodeResource(res, R.drawable.goblin_frame4);
        bloodPool = new Bitmap[12];
        bloodPool[0] = BitmapFactory.decodeResource(res, R.drawable.blood_1);
        bloodPool[1] = BitmapFactory.decodeResource(res, R.drawable.blood_2);
        bloodPool[2] = BitmapFactory.decodeResource(res, R.drawable.blood_3);
        bloodPool[3] = BitmapFactory.decodeResource(res, R.drawable.blood_4);
        bloodPool[4] = BitmapFactory.decodeResource(res, R.drawable.blood_5);
        bloodPool[5] = BitmapFactory.decodeResource(res, R.drawable.blood_6);
        bloodPool[6] = BitmapFactory.decodeResource(res, R.drawable.blood_7);
        bloodPool[7] = BitmapFactory.decodeResource(res, R.drawable.blood_8);
        bloodPool[8] = BitmapFactory.decodeResource(res, R.drawable.blood_9);
        bloodPool[9] = BitmapFactory.decodeResource(res, R.drawable.blood_10);
        bloodPool[10] = BitmapFactory.decodeResource(res, R.drawable.blood_11);
        bloodPool[11] = BitmapFactory.decodeResource(res, R.drawable.blood_12);

    }

    // Return goblinHead bitmap of frame
    public Bitmap getGoblinHead(int frame){
        return goblinHead[frame];
    }

    public int getGoblinHeadWidth(){
        return goblinHead[0].getWidth();
    }

    public int getGoblinHeadHeight(){
        return goblinHead[0].getHeight();
    }

    public Bitmap getBlood(int bloodFrame){
        return bloodPool[bloodFrame];
    }

    public int getBloodWidth(){
        return bloodPool[0].getWidth();
    }

    public int getBloodHeight(){
        return bloodPool[0].getHeight();
    }

    //Return sword bitmap
    public Bitmap getSword(){
        return sword;
    }

    //Return sword width
    public int getSwordWidth(){
        return sword.getWidth();
    }

    //Return sword height
    public int getSwordHeight(){
        return sword.getHeight();
    }


    //Return background bitmap
    public Bitmap getBackground(){
        return background;
    }

    //Return background width
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        return Bitmap.createScaledBitmap(bitmap, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, false);
    }
}
