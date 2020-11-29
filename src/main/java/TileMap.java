import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
import javax.swing.*;


public class TileMap extends JPanel implements ActionListener, KeyListener {
    private final int windowWidth = 960;
    private final int windowHeight = 480;
    private final int imgW = 32;
    private final int imgH = 32;
    private int tilesw = 15;
    private int tilesh = 23;
    private int imgX = 0;
    private int imgY = 0;
    private Image texture;
    private Image[][] dispTexture;
    private Random rnum = new Random();
    private Player player;
    private Timer timer;

    TileMap() {


        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);
        player = new Player();
        dispTexture = new Image[tilesh][tilesw];
        for(int x=0;x<tilesh;x++){
            for(int y=0;y<tilesw;y++){
                if(x==0 || y==0 || x==tilesh-1 || y==tilesw-1 || x%2==0 && y%2==0 ){
                    texture = new ImageIcon("src/main/resources/tiles/frames/wall_left.png").getImage();
                }else {
                    texture = new ImageIcon("src/main/resources/tiles/frames/floor_" + (rnum.nextInt(3) + 1) + ".png").getImage();
                }
                dispTexture[x][y] = texture;
            }
        }

        addKeyListener(this);
        timer = new Timer(10,this);
        timer.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for(int y=0;y<tilesw;y++){
            for(int x=0;x<tilesh;x++){
                g2D.drawImage(dispTexture[x][y], imgX+imgW*x, imgY+imgH*y,imgW,imgH, null);
            }
        }
        g2D.drawImage(player.getStand(), player.getPositionX(), player.getPositionY(), imgW,imgH, this);
        g2D.drawString(String.valueOf("x="+player.positionX+", y="+player.positionY),800,100);
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }
    private void step(){
        repaint();
        player.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

}