
package logic.events;

import gui.SoundController;
import logic.DownData;
import logic.ViewData;


public interface InputEventListener {
    
    DownData onDownEvent(MoveEvent event);
    
    ViewData onLeftEvent();
    
    ViewData onRightEvent();
    
    ViewData onRotateEvent();
    
    void createNewGame();
    
    SoundController getBgMusic();
    
    void playMoveSound();
    void playBonusScoreSound();
    void muteSound();
    void unMuteSound();
}
