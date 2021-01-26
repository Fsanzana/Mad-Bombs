import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player{
    private final int width = 26; //tamaño del jugador en px
    private final int height = 26;
    private final ImageIcon walk1; //texturas del jugador
    private final ImageIcon walk2;
    private final ImageIcon stand;
    public int positionX;
    public int positionY;
    public  int pjscreen;
    public int dx = 0;//velocidad del jugador
    public int dy = 0;
    Player(int px, int py, int npj){
        walk1 = new ImageIcon("src/main/resources/player/step_1.png");
        walk2 = new ImageIcon("src/main/resources/player/step_2.png");
        stand = new ImageIcon("src/main/resources/player/stand.png");
        this.positionX = px;
        this.positionY = py;
        this.pjscreen=npj;
    }
    public Rectangle bounds(){ //datos de los bordes del jugador
        return (new Rectangle(positionX,positionY,width,height));
    }
    public void move(){ //permite cambiar la posicin del jugador
        this.positionX += dx;
        this.positionY += dy;
    }
    public Image getWalk1() {
        return walk1.getImage();
    }

    public Image getWalk2() {
        return walk2.getImage();
    }

    public Image getStand() {
        return stand.getImage();
    }
    public int getPositionX() {
        return positionX;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
