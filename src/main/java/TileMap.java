import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


public class TileMap extends JPanel implements ActionListener {
    private final int windowWidth = 480;
    private final int windowHeight = 480;
    private final int imgW = 32;
    private final int imgH = 32;
    private int tiles = 15;
    private int imgX = 0;
    private int imgY = 0;
    private Image texture;
    private Image[][] dispTexture;
    private Random rnum = new Random();
    private Player player;


    TileMap() {
        Timer timer = new Timer(10,null);
        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);
        player = new Player();
        dispTexture = new Image[tiles][tiles];
        for(int y=0;y<tiles;y++){
            for(int x=0;x<tiles;x++){
                if(x==0 || y==0 || x==tiles-1 || y==tiles-1 || x%2==0 && y%2==0 ){
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
        for(int y=0;y<tiles;y++){
            for(int x=0;x<tiles;x++){
                g2D.drawImage(dispTexture[x][y], imgX+imgW*x, imgY+imgH*y,imgW,imgH, null);
            }
        }
        g2D.drawImage(player.getStand(), player.getPositionX(), player.getPositionY(), imgW,imgH, null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}