import java.awt.Component;
import javax.swing.JFrame;

public class Frame extends JFrame {
    Frame() {
        add(new Panel());
        setTitle("S nake");
        setDefaultCloseOperation(3);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo((Component)null);
    }
}
