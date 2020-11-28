import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class TileMap extends JPanel {
    private final int tiles = 15;
    private final int windowWidth = 480;
    private final int windowHeight = 480;
    private final int imgW = 32;
    private final int imgH = 32;
    private int imgX = 0;
    private int imgY = 0;
    private Image texture;
    private Image[][] dispTexture;
    private Random rnum = new Random();


    TileMap() {

        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);

        dispTexture = new Image[15][15];
        for(int y=0;y<15;y++){
            for(int x=0;x<15;x++){
                if(x==0 || y==0 || x==14 || y==14 || x%2==0 && y%2==0 ){
                    texture = new ImageIcon("src/main/resources/tiles/frames/wall_left.png").getImage();
                }else {
                    texture = new ImageIcon("src/main/resources/tiles/frames/floor_" + (rnum.nextInt(3) + 1) + ".png").getImage();
                }
                dispTexture[x][y] = texture;
            }
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for(int y=0;y<15;y++){
            for(int x=0;x<15;x++){
                g2D.drawImage(dispTexture[x][y], imgX+imgW*x, imgY+imgH*y,imgW,imgH, null);
            }
        }



    }


}