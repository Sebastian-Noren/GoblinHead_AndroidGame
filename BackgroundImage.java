package sebastian.noren.da569a_gameproject;

public class BackgroundImage {

    private int backgroundImageX, backgroundImageY;

    public BackgroundImage(){
        backgroundImageX = 0;
        backgroundImageY = 0;
    }

    //Getter method for getting the X-coordinate
    public int getX(){
        return backgroundImageX;
    }

    //Getter method for getting the Y-coordinate
    public int getY(){
        return backgroundImageY;
    }

    //Setter method for setting the X-coordinate
    public void setX(int backgroundImageX){
        this.backgroundImageX = backgroundImageX;
    }

    // Setter method for setting the Y-coordinate
    public void setY(int backgroundImageY){
        this.backgroundImageY = backgroundImageY;
    }

}
