import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });

    }
}
