package sebastian.noren.da569a_gameproject;

public class Blood {


    int bloodFrame;
    int bloodX, bloodY;
    public int maxFrame;

    public Blood(){
        bloodX =0;
        bloodY = 0;
        bloodFrame = 0;
        maxFrame = 11;

    }

    public int getBloodX() {
        return bloodX;
    }

    public void setBloodX(int bloodX) {
        this.bloodX = bloodX;
    }

    public int getBloodY() {
        return bloodY;
    }

    public void setBloodY(int bloodY) {
        this.bloodY = bloodY;
    }

    public int getBloodFrame() {
        return bloodFrame;
    }

    public void setBloodFrame(int bloodFrame) {
        this.bloodFrame = bloodFrame;
    }

}
