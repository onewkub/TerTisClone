
package gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    private Media file;
    private MediaPlayer player;
    private Boolean isMute = false;
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
        if(!isMute){
            player.setVolume(value);
        }
        else{
            player.setVolume(0);
        }
    }
    public void unMute(){
        isMute = false;
    }
    public void mute(){
        isMute = true;
    }
    public Boolean getIsmute(){
        return isMute;
    }
}
