import java.awt.*;
import java.awt.event.*;
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
    Timer timer;


    TileMap() {
        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);
        this.addKeyListener(new MyKeyAdapter());
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
        StartGame();


    }

    public void StartGame(){
        player = new Player();
        timer = new Timer(10,null);
        timer.start();

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
        repaint();
        player.move();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    player.setDy(1);

                    break;
                case KeyEvent.VK_S:
                    player.setDy(-1);

                    break;
                case KeyEvent.VK_LEFT:
                    player.dx=-16;
                    break;
                case KeyEvent.VK_RIGHT:
                    player.dx=16;
                    break;
            }
        }
    }


    }
