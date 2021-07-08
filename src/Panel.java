import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 750;

    static final int SCREEN_HEIGHT = 750;

    static final int UNIT_SIZE = 25;

    static final int GAME_UNITS = 22500;

    static final int DELAY = 70;

    final int[] x = new int[22500];

    final int[] y = new int[22500];

    int body = 6;

    int applesEaten;

    int appleX;

    int appleY;

    char direction = 'R';

    boolean running = false;

    Timer timer;

    Random random;

    Panel() {
        this.random = new Random();
        setPreferredSize(new Dimension(750, 750));
        setBackground(new Color(11, 102, 35));
        setFocusable(true);
        addKeyListener(new MykeyAdapter());
        start();
    }

    public void start() {
        generateNewApple();
        this.running = true;
        this.timer = new Timer(70, this);
        this.timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        buildGame(g);
    }

    public void buildGame(Graphics g) {
        if (this.running) {
            g.setColor(Color.red);
            g.fillOval(this.appleX, this.appleY, 25, 25);
            for (int i = 0; i < this.body; i++) {
                if (i == 0) {
                    g.setColor(new Color(101, 67, 33));
                    g.fillRect(this.x[i], this.y[i], 25, 25);
                } else {
                    g.setColor(new Color(128, 96, 67));
                    g.fillRect(this.x[i], this.y[i], 25, 25);
                }
                g.setColor(Color.black);
                g.setFont(new Font("Ink Free", 1, 75));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + this.applesEaten, (750 - metrics.stringWidth("Score: " + this.applesEaten)) / 2, g.getFont().getSize());
            }
        } else {
            over(g);
        }
    }

    public void generateNewApple() {
        this.appleX = this.random.nextInt(30) * 25;
        this.appleY = this.random.nextInt(30) * 25;
    }

    public void move() {
        for (int i = this.body; i > 0; i--) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }
        switch (this.direction) {
            case 'U':
                this.y[0] = this.y[0] - 25;
                break;
            case 'D':
                this.y[0] = this.y[0] + 25;
                break;
            case 'L':
                this.x[0] = this.x[0] - 25;
                break;
            case 'R':
                this.x[0] = this.x[0] + 25;
                break;
        }
    }

    public void eatApple() {
        if (this.x[0] == this.appleX && this.y[0] == this.appleY) {
            this.body++;
            this.applesEaten++;
            generateNewApple();
        }
    }

    public void collision() {
        for (int i = this.body; i > 0; i--) {
            if (this.x[0] == this.x[i] && this.y[0] == this.y[i])
                this.running = false;
        }
        if (this.x[0] < 0)
            this.running = false;
        if (this.x[0] > 750)
            this.running = false;
        if (this.y[0] > 750)
            this.running = false;
        if (this.y[0] < 0)
            this.running = false;
        if (!this.running)
            this.timer.stop();
    }

    public void over(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Ink Free", 1, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (750 - metrics.stringWidth("Game Over")) / 2, 375);
        g.setColor(Color.black);
        g.setFont(new Font("Ink Free", 1, 75));
        FontMetrics metricsScore = getFontMetrics(g.getFont());
        g.drawString("Score: " + this.applesEaten, (750 - metricsScore.stringWidth("Score: " + this.applesEaten)) / 2, g.getFont().getSize());
    }

    public void actionPerformed(ActionEvent e) {
        if (this.running) {
            move();
            eatApple();
            collision();
        }
        repaint();
    }

    public class MykeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 37:
                    if (Panel.this.direction != 'R')
                        Panel.this.direction = 'L';
                    break;
                case 65:
                    if (Panel.this.direction != 'R')
                        Panel.this.direction = 'L';
                    break;
                case 39:
                    if (Panel.this.direction != 'L')
                        Panel.this.direction = 'R';
                    break;
                case 68:
                    if (Panel.this.direction != 'L')
                        Panel.this.direction = 'R';
                    break;
                case 38:
                    if (Panel.this.direction != 'D')
                        Panel.this.direction = 'U';
                    break;
                case 87:
                    if (Panel.this.direction != 'D')
                        Panel.this.direction = 'U';
                    break;
                case 40:
                    if (Panel.this.direction != 'U')
                        Panel.this.direction = 'D';
                    break;
                case 83:
                    if (Panel.this.direction != 'U')
                        Panel.this.direction = 'D';
                    break;
            }
        }
    }
}
