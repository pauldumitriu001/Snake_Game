import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame  frame = new JFrame();
        Game game = new Game();

        frame.setBounds(10, 10, 905, 700);
        frame.setBackground(Color.GRAY);
        frame.setTitle("Pauls Snake Game Reincarnated");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }
}
