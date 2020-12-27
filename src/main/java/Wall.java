import java.awt.*;

public class Wall {
    private final int width = 32;
    private final int height = 32;
    public int posx;
    public int posy;

    public Wall(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }
    public Rectangle bounds(){
        return (new Rectangle(posx,posy,width,height));
    }
}
