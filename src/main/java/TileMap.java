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
    private Wall[] walls;
    private boolean collision;
    private Player netp1;
    private Player netp2;
    private Player netp3;
    private Player netp4;
    TileMap() {
        walls = new Wall[tilesh*tilesw];
        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);
        player = new Player(0,0,0);
        Player player1 = new Player(32,32, 1);
        Player player2 = new Player(676,418,2);
        Player player3 = new Player(676,35,3);
        Player player4 = new Player(35,418,4);
        player = player3;
        dispTexture = new Image[tilesh][tilesw];
        int i=0;
        for(int x=0;x<tilesh;x++){
            for(int y=0;y<tilesw;y++){
                if(x==0 || y==0 || x==tilesh-1 || y==tilesw-1 || x%2==0 && y%2==0 ){
                    texture = new ImageIcon("src/main/resources/tiles/frames/wall_left.png").getImage();
                    walls[i] = new Wall(imgW*x,imgH*y);
                }else {
                    texture = new ImageIcon("src/main/resources/tiles/frames/floor_" + (rnum.nextInt(3) + 1) + ".png").getImage();
                }
                i++;
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
        g2D.setColor(Color.red);
        for(int y=0;y<tilesw;y++){
            for(int x=0;x<tilesh;x++){
                g2D.drawImage(dispTexture[x][y], imgX+imgW*x, imgY+imgH*y,imgW,imgH, null);
            }
        }
        g2D.drawImage(player.getStand(), player.getPositionX(), player.getPositionY(), player.getWidth(),player.getHeight(), this);
        g2D.drawString("Collision = "+String.valueOf(collision),800,120);
        g2D.drawString(String.valueOf("x="+player.positionX+", y="+player.positionY),800,100);
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        collision();
        step();
        movyo();
    }

    private void movyo() {
    }

    public void collision(){
        Rectangle r1 = new Rectangle(player.bounds());
        for(int i = 0; i<walls.length;i++){
            if(walls[i]!=null){
                Rectangle r2 = walls[i].bounds();
                if (r1.intersects(r2)) {
                    collision = true;
                    if(r1.intersection(r2).getX()==r1.getX()&&player.dx<0){
                        player.setPositionX(player.getPositionX()+1);
                        player.setDx(0);
                    }if((r1.intersection(r2).getX()-r1.getX()==(player.getWidth()-1))&&player.dx>0){
                        player.setPositionX(player.getPositionX()-1);
                        player.setDx(0);
                    } if(r1.intersection(r2).getY()==r1.getY()&&player.dy<0){
                        player.setPositionY(player.getPositionY()+1);
                        player.setDy(0);
                    }if((r1.intersection(r2).getY()-r1.getY()==player.getHeight()-1)&&player.dy>0){
                        player.setPositionY(player.getPositionY()-1);
                        player.setDy(0);
                    }
                    System.out.println("wall: "+r1.getY());
                    System.out.println("player: "+player.getPositionY());
                    System.out.println("delta: "+(r1.intersection(r2).getY()-r1.getY()));

                    break;
                } else {
                    collision = false;
                }
            }
        }
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