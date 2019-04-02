package sebastian.noren.da569a_gameproject;

import java.util.Random;

public class GoblinHead {

    private int currentFrame;
    private float goblinHeadX, goblinHeadY, velocity;
    int maxFrame;
//AppConstants.SCREEN_WIDTH/2

    Random rand = new Random();
    public GoblinHead(){
        goblinHeadX = rand.nextInt(AppConstants.SCREEN_WIDTH) - (AppConstants.getArtEngine().getGoblinHeadWidth()/2);
        goblinHeadY = 0 - AppConstants.getArtEngine().getGoblinHeadWidth()-100;
        currentFrame = 0;
        maxFrame = 3;
        velocity = 0;
    }

    // Getter method for velocity
    public float getVelocity(){
        return velocity;
    }

    // Setter method for velocity
    public void setVelocity(float velocity){
        this.velocity = velocity;
    }

    // Getter method for current frame
    public int getCurrentFrame(){
        return currentFrame;
    }

    // Setter method for current frame
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    // Getter method for getting X-coordinate of the GoblinHead
    public float getX(){
        return goblinHeadX;
    }

    // Getter method for getting the Y-coordinate of the GoblinHead
    public float getY(){
        return goblinHeadY;
    }

    // Setter method for setting the X-coordinate
    public void setX(int gobX){
        this.goblinHeadX = gobX;
    }

    // Setter method for setting the Y-coordinate
    public void setY(float gobY){
        this.goblinHeadY = gobY;
    }

    public void resetPosistion(){
        goblinHeadX = rand.nextInt(AppConstants.SCREEN_WIDTH) - (AppConstants.getArtEngine().getGoblinHeadWidth()/2);
        goblinHeadY = 0 - AppConstants.getArtEngine().getGoblinHeadWidth()-100;
        velocity = 0;
        setVelocity(getVelocity() + AppConstants.gravity);
    }
}
