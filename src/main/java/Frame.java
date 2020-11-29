import javax.swing.*;

public class Frame extends JFrame {
    TileMap map;
    public Frame() {
        map = new TileMap();
        this.add(map);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mad-Bombs");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
