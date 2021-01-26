import java.awt.*;

public class Wall {
    private final int width = 32; //tamaño de la muralla en px
    private final int height = 32;
    public int posx;
    public int posy;

    public Wall(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }
    public Rectangle bounds(){// datos de los bordes de cada muralla
        return (new Rectangle(posx,posy,width,height));
    }
}
