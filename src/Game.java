import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Game extends JPanel implements KeyListener, ActionListener {

    Game(){
        playMusic();
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
