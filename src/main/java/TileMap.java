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
    private Timer timer;
    private Wall[] walls;
    private boolean collision;
    private Player netp1;
    private Player netp2;
    private Player netp3;
    private Player netp4;

    private Player player1 = new Player(32,32, 1);
    private Player player2 = new Player(676,418,2);
    private Player player3 = new Player(676,35,3);
    private Player player4 = new Player(35,418,4);
    TileMap() {
        walls = new Wall[tilesh*tilesw];
        this.setPreferredSize(new Dimension(windowWidth,windowHeight));
        this.setBackground(Color.black);
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
        //Dibujo player 1
        g2D.drawImage(player1.getStand(), player1.getPositionX(), player1.getPositionY(), player1.getWidth(),player1.getHeight(), this);
        g2D.drawString("Collision = "+String.valueOf(collision),800,120);
        g2D.drawString(String.valueOf("x="+player1.positionX+", y="+player1.positionY),800,100);
        //Dibujo player 2
        g2D.drawImage(player2.getStand(), player2.getPositionX(), player2.getPositionY(), player2.getWidth(),player2.getHeight(), this);
        g2D.drawString("Collision = "+String.valueOf(collision),800,120);
        g2D.drawString(String.valueOf("x="+player2.positionX+", y="+player2.positionY),800,100);
        //Dibujo player 3
        g2D.drawImage(player3.getStand(), player3.getPositionX(), player3.getPositionY(), player3.getWidth(),player3.getHeight(), this);
        g2D.drawString("Collision = "+String.valueOf(collision),800,120);
        g2D.drawString(String.valueOf("x="+player3.positionX+", y="+player3.positionY),800,100);
        //Dibujo player 4
        g2D.drawImage(player4.getStand(), player4.getPositionX(), player4.getPositionY(), player4.getWidth(),player4.getHeight(), this);
        g2D.drawString("Collision = "+String.valueOf(collision),800,120);
        g2D.drawString(String.valueOf("x="+player4.positionX+", y="+player4.positionY),800,100);
        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        collision(player1);
        collision(player2);
        collision(player3);
        collision(player4);
        step();

    }



    public void collision(Player player){
        Rectangle r1 = new Rectangle(player.bounds());
        for(int i = 0; i<walls.length;i++){
            if(player.getPositionX()>678){
                player.setPositionX(678);
            }
            if(player.getPositionX()<32){
                player.setPositionX(32);
            }
            if(player.getPositionY()>422){
                player.setPositionY(422);
            }
            if(player.getPositionY()<32){
                player.setPositionY(32);
            }
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
        player1.move();
        player2.move();
        player3.move();
        player4.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            player1.setDy(-1);
            System.out.println("arribapres p1");
        }
        if(key == KeyEvent.VK_S){
            player1.setDy(1);
            System.out.println("abajopres p1");
        }
        if(key == KeyEvent.VK_A){
            player1.setDx(-1);
            System.out.println("izpress p1");
        }
        if(key == KeyEvent.VK_D){
            player1.setDx(1);
            System.out.println("derpress p1");
        }

        //Player 2
        if(key == KeyEvent.VK_I){
            player2.setDy(-1);
            System.out.println("arribapres p2");
        }
        if(key == KeyEvent.VK_K){
            player2.setDy(1);
            System.out.println("abajopres p2");
        }
        if(key == KeyEvent.VK_J){
            player2.setDx(-1);
            System.out.println("izpress p2");
        }
        if(key == KeyEvent.VK_L){
            player2.setDx(1);
            System.out.println("derpress p2");
        }

        //Player 3

        if(key == KeyEvent.VK_UP){
            player3.setDy(-1);
            System.out.println("arribapres p3");
        }
        if(key == KeyEvent.VK_DOWN){
            player3.setDy(1);
            System.out.println("abajopres p3");
        }
        if(key == KeyEvent.VK_LEFT){
            player3.setDx(-1);
            System.out.println("izpress p3");
        }
        if(key == KeyEvent.VK_RIGHT){
            player3.setDx(1);
            System.out.println("derpress p3");
        }

        //Player 4

        if(key == KeyEvent.VK_NUMPAD8){
            player4.setDy(-1);
            System.out.println("arribapres p4");
        }
        if(key == KeyEvent.VK_NUMPAD5){
            player4.setDy(1);
            System.out.println("abajopres p4");
        }
        if(key == KeyEvent.VK_NUMPAD4){
            player4.setDx(-1);
            System.out.println("izpress p4");
        }
        if(key == KeyEvent.VK_NUMPAD6){
            player4.setDx(1);
            System.out.println("derpress p4");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //Player 1
        if(key == KeyEvent.VK_W){
            player1.setDy(0);

        }
        if(key == KeyEvent.VK_S){
            player1.setDy(0);

        }
        if(key == KeyEvent.VK_A){
            player1.setDx(0);

        }
        if(key == KeyEvent.VK_D){
            player1.setDx(0);

        }

        //Player 2

        if(key == KeyEvent.VK_I){
            player2.setDy(0);

        }
        if(key == KeyEvent.VK_K){
            player2.setDy(0);

        }
        if(key == KeyEvent.VK_J){
            player2.setDx(0);

        }
        if(key == KeyEvent.VK_L){
            player2.setDx(0);

        }

        //Player 3

        if(key == KeyEvent.VK_UP){
            player3.setDy(0);

        }
        if(key == KeyEvent.VK_DOWN){
            player3.setDy(0);

        }
        if(key == KeyEvent.VK_LEFT){
            player3.setDx(0);

        }
        if(key == KeyEvent.VK_RIGHT){
            player3.setDx(0);

        }

        //Player 4

        if(key == KeyEvent.VK_NUMPAD8){
            player4.setDy(0);

        }
        if(key == KeyEvent.VK_NUMPAD5){
            player4.setDy(0);

        }
        if(key == KeyEvent.VK_NUMPAD4){
            player4.setDx(0);

        }
        if(key == KeyEvent.VK_NUMPAD6){
            player4.setDx(0);

        }
    }

}