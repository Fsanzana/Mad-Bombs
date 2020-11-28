import javax.swing.*;

public class Frame extends JFrame {
    TileMap board;
    public Frame() {
        board = new TileMap();
        this.add(board);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Jueguito uwu");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
