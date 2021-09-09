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

    private int snakeLength = 3;    //snake starts with a head
    private int score = 0;

    private final Timer timer;
    private boolean first = true;   //is this the first move

    private int fruitX;
    private int fruitY;
    private ImageIcon fruit;

    Game(){
        playMusic();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(100, this);
        timer.start();
    }

    public void paint(Graphics g){
        if(first){  //how game is initialized
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
        Image resizer;  //used to resize the image in case the resources are to large
        headUp = new ImageIcon("headUp.png");
        resizer = headUp.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        headUp = new ImageIcon(resizer);
        headUp.paintIcon(this, g, snakeX[0], snakeY[0]);

        for(int i = 0; i < snakeLength; i++){
            if(i == 0 && up){
                flipUp(resizer, g, i);
            }
            else if(i == 0 && right){
                flipRight(resizer, g, i);
            }
            else if(i == 0 && left){
                flipLeft(resizer, g, i);
            }
            else if(i == 0 && down){
                flipDown(resizer, g, i);
            }
            if(i != 0){
                printTail(resizer, g, i);
            }
        }
        g.dispose();
    }

    /**
     * Flips the head image up
     * @param resizer helper object to resize the image
     */
    private void flipUp(Image resizer, Graphics g, int i){
        headUp = new ImageIcon("headUp.png");
        resizer = headUp.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        headUp = new ImageIcon(resizer);
        headUp.paintIcon(this, g, snakeX[i], snakeY[i]);
    }

    /**
     * Flips the head image down
     * @param resizer helper object to resize the image
     */
    private void flipDown(Image resizer, Graphics g, int i){
        headDown = new ImageIcon("headDown.png");
        resizer = headDown.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        headDown = new ImageIcon(resizer);
        headDown.paintIcon(this, g, snakeX[i], snakeY[i]);
    }

    /**
     * Flips the head image to the right
     * @param resizer helper object to resize the image
     */
    private void flipRight(Image resizer, Graphics g, int i){
        headRight = new ImageIcon("headRight.png");
        resizer = headRight.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        headRight = new ImageIcon(resizer);
        headRight.paintIcon(this, g, snakeX[i], snakeY[i]);
    }

    /**
     * Flips the head image to the left
     * @param resizer helper object to resize the image
     */
    private void flipLeft(Image resizer, Graphics g, int i){
        headLeft = new ImageIcon("headLeft.png");
        resizer = headLeft.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        headLeft = new ImageIcon(resizer);
        headLeft.paintIcon(this, g, snakeX[i], snakeY[i]);
    }

    /**
     * Print the tail
     * @param resizer helper object to resize the image
     */
    private void printTail(Image resizer, Graphics g, int i){
        tail = new ImageIcon("tail.png");
        resizer = tail.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        tail = new ImageIcon(resizer);
        tail.paintIcon(this, g, snakeX[i], snakeY[i]);
    }

    /**
     * Play the music in the res folder
     */
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
        timer.restart();
        if(right){
            moveRight();
        }
        if(left){
            moveLeft();
        }
        if(up){
            moveUp();
        }
        if(down){
            moveDown();
        }
        repaint();
    }

    /**
     * Move to the right
     */
    private void moveRight(){
        for(int y = snakeLength - 1; y >=0; y--){
            snakeY[y+1] = snakeY[y];
        }
        for(int x = snakeLength - 1; x >= 0; x--){
            if(x == 0){ //move the head forward
                snakeX[x] = snakeX[x] + 25;
            }
            else{   //move the tail relative to the head
                snakeX[x] = snakeX[x - 1];
            }
            if(snakeX[x] > 850){
                //Implement game over
            }
        }
    }

    /**
     * Move to the left
     */
    private void moveLeft(){
        for(int y = snakeLength - 1; y >=0; y--){
            snakeY[y+1] = snakeY[y];
        }
        for(int x = snakeLength - 1; x >= 0; x--){
            if(x == 0){ //move the head forward
                snakeX[x] = snakeX[x] - 25;
            }
            else{   //move the tail relative to the head
                snakeX[x] = snakeX[x - 1];
            }
            if(snakeX[x] < 25){
                //Implement game over
            }
        }
    }

    /**
     * Move up
     */
    private void moveUp(){
        for(int x = snakeLength - 1; x >=0; x--){
            snakeX[x+1] = snakeX[x];
        }
        for(int y = snakeLength - 1; y >= 0; y--){
            if(y == 0){ //move the head forward
                snakeY[y] = snakeY[y] - 25;
            }
            else{   //move the tail relative to the head
                snakeY[y] = snakeY[y - 1];
            }
            if(snakeX[y] < 55){
                //Implement game over
            }
        }
    }

    /**
     * Move down
     */
    private void moveDown(){
        for(int x = snakeLength - 1; x >=0; x--){
            snakeX[x+1] = snakeX[x];
        }
        for(int y = snakeLength - 1; y >= 0; y--){
            if(y == 0){ //move the head forward
                snakeY[y] = snakeY[y] + 25;
            }
            else{   //move the tail relative to the head
                snakeY[y] = snakeY[y - 1];
            }
            if(snakeX[y] > 625){
                //Implement game over
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){
            up();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down();
        }
    }

    /**
     * Set direction right
     */
    private void right(){
        first = false;  //no longer the first move
        if(!left){  //prevent snake from doing a 180 deg turn
            right = true;
        }
        else{
            right = false;
        }
        up = false;
        down = false;
    }

    /**
     * Set direction left
     */
    private void left(){
        first = false;  //no longer the first move
        if(!right){  //prevent snake from doing a 180 deg turn
            left = true;
        }
        else{
            left = false;
        }
        up = false;
        down = false;
    }

    /**
     * Set direction up
     */
    private void up(){
        first = false;  //no longer the first move
        if(!down){  //prevent snake from doing a 180 deg turn
            up = true;
        }
        else{
            up = false;
        }
        left = false;
        right = false;
    }

    /**
     * Set direction down
     */
    private void down(){
        first = false;  //no longer the first move
        if(!up){  //prevent snake from doing a 180 deg turn
            down = true;
        }
        else{
            down = false;
        }
        left = false;
        right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
