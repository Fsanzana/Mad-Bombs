import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class TileMap extends JPanel {
    private final int windowWidth = 480;
    private final int windowHeight = 480;
    private Image imagen;
    private int imgX = 0;
    private int imgY = 0;
    private int imgW = 32;
    private int imgH = 32;
    private Random rnum = new Random();
    private Image[][] uwu;

    TileMap() {

        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);

        uwu = new Image[15][15];
        for(int y=0;y<15;y++){
            for(int x=0;x<15;x++){
                if(x==0 || y==0 || x==14 || y==14){
                    imagen = new ImageIcon("src/main/resources/tiles/frames/wall_left.png").getImage();
                }else {
                    imagen = new ImageIcon("src/main/resources/tiles/frames/floor_" + (rnum.nextInt(3) + 1) + ".png").getImage();
                }
                uwu[x][y] = imagen;
            }
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for(int y=0;y<15;y++){
            for(int x=0;x<15;x++){
                g2D.drawImage(uwu[x][y], imgX+32*x, imgY+32*y,imgW,imgH, null);
            }
        }


    }


}