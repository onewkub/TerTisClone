
package gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    private Media file;
    private MediaPlayer player;
    public SoundController(String source, Boolean loop){
        file = new Media(source);
        player = new MediaPlayer(file);
        if(loop)
            player.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public SoundController(){    
    }
    public void setSource(String source){
        file = new Media(source);
        player = new MediaPlayer(file);
    }
    public void playMedia(){
        player.play();
    }
    public void pauseMedia(){
        player.pause();
    }
    public void stopMedia(){
        player.stop();
    }
    public void setVol(double value){
        player.setVolume(value);
    }
    
}
