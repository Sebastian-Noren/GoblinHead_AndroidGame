package sebastian.noren.da569a_gameproject;


public class Sword {

    private float swordX, swordY;
            private int velocity;

    public Sword() {
        swordX = ((float)AppConstants.SCREEN_WIDTH/2) - ((float)AppConstants.getArtEngine().getSwordWidth()/2);
        swordY = AppConstants.SCREEN_HEIGHT - AppConstants.getArtEngine().getSwordHeight();
        velocity = 0;
    }
    public float getSwordX() {
        return swordX;
    }

    public void setSwordX(float swordX) {
        this.swordX = swordX;
    }

    public float getSwordY() {
        return swordY;
    }

    public void setSwordY(int swordY) {
        this.swordY = swordY;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }


}
