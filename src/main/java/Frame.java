import javax.swing.*;

public class Frame extends JFrame {
    TileMap board;
    public Frame() {
        board = new TileMap();
        this.add(board);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mad-Bombs");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
