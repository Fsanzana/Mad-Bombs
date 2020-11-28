import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player{
    private final ImageIcon walk1;
    private final ImageIcon walk2;
    private final ImageIcon stand;
    public int positionX;
    public int positionY;
    public int dx = 0;
    public int dy = 0;
    Player(){
        walk1 = new ImageIcon("src/main/resources/player/step_1.png");
        walk2 = new ImageIcon("src/main/resources/player/step_2.png");
        stand = new ImageIcon("src/main/resources/player/stand.png");
        this.positionX = 32;
        this.positionY = 32;
    }
    public void move(){
        positionX += dx;
        positionY += dy;
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            dy=16;
        }
        if(key == KeyEvent.VK_DOWN){
            dy=-16;
        }
        if(key == KeyEvent.VK_LEFT){
            dx=-16;
        }
        if(key == KeyEvent.VK_RIGHT){
            dy=16;
        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            dy=0;
        }
        if(key == KeyEvent.VK_DOWN){
            dy=0;
        }
        if(key == KeyEvent.VK_LEFT){
            dx=0;
        }
        if(key == KeyEvent.VK_RIGHT){
            dy=0;
        }

    }

}
