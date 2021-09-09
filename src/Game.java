import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Game extends JPanel implements KeyListener, ActionListener {
    private ImageIcon title;    //Title image created from photoshop

    //Snake body is within these arrays
    private int snakeX[] = new int[750];
    private int snakeY[] = new int[750];

    //directions
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    private ImageIcon headUp;
    private ImageIcon headDown;
    private ImageIcon headLeft;
    private ImageIcon headRight;
    private ImageIcon tail;

    private int snakeLength = 1;    //snake starts with a head
    private int score = 0;

    private final Timer timer;
    private boolean first = true;   //is this the first move

    Game(){
        playMusic();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(100, this);
        timer.start();
    }

    public void paint(Graphics g){

        if(first){
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;

            snakeY[0] = 100;
            snakeY[1] = 100;
            snakeY[2] = 100;
        }

        //display title
        title = new ImageIcon("Snake Title.png");
        title.paintIcon(this, g, 225, 30);

        //display border
        g.setColor(Color.red);
        g.drawRect(24, 74, 851, 577);

        //display background
        g.setColor(new Color(0, 153, 0));
        g.fillRect(25, 75, 850, 575);

        //initial position
        headUp = new ImageIcon("head-up.png");
        headUp.paintIcon(this, g, snakeX[0], snakeY[0]);

        for(int i = 0; i < snakeLength; i++){
            if(i == 0 && up){
                headUp = new ImageIcon("head-up.png");
                headUp.paintIcon(this, g, snakeX[i], snakeY[i]);
            }
            else if(i == 0 && right){
                headRight = new ImageIcon("head-right.png");
                headRight.paintIcon(this, g, snakeX[i], snakeY[i]);
            }
            else if(i == 0 && left){
                headLeft = new ImageIcon("head-left.png");
                headLeft.paintIcon(this, g, snakeX[i], snakeY[i]);
            }
            else if(i == 0 && down){
                headDown = new ImageIcon("head-down.png");
                headDown.paintIcon(this, g, snakeX[i], snakeY[i]);
            }
        }

        g.dispose();
    }

    public void playMusic(){
        try{
            File file = new File("res/bensound-energy.wav");
            if(file.exists()) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                System.out.println("Audio file not found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
