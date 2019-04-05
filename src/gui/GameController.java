
package gui;

import logic.ClearRow;
import logic.DownData;
import logic.SimpleBoard;
import logic.ViewData;
import logic.events.EventSource;
import logic.events.InputEventListener;
import logic.events.MoveEvent;

public class GameController implements InputEventListener{
    private SimpleBoard board = new SimpleBoard(20, 10);
    private final GuiController viewController;
    private final SoundController bgMusic = new SoundController(getClass().getResource("/BanksTetrominoes.wav").toString(), true);
    private SoundController sfxSound;
    private final String brickPlaceSound = getClass().getResource("/brickPlace.wav").toString();
    private final String moveBrickSound = getClass().getResource("/moveBrick.wav").toString();
    private final String bonusScoreSound = getClass().getResource("/bonusScore.wav").toString();
    private final String overSound = getClass().getResource("/overSound.wav").toString();
            
    public GameController(GuiController c) {
        this.viewController = c;
        board.createNewBrick();
        viewController.setEventListener(this);
        viewController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewController.bindScore(board.getScore().scoreProperty());
        viewController.bindHighScore(board.getHighScore().highScoreProperty());
        bgMusic.playMedia();
        sfxSound = new SoundController();
    }
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if(!canMove){
            playPlaceBrickSound();
            board.mergeBrickToBackground();
            clearRow = board.clearRow();
            if(clearRow.getLinesRemoved() > 0){
                board.getScore().add(clearRow.getScoreBonus());
                playBonusScoreSound();
            }
//            System.out.println(clearRow.getLinesRemoved());
            if(board.createNewBrick()){
                playOverSound();
                viewController.gameOver();
                
            }
//            board.createNewBrick();
        }
        else{
            if(event.getEventSource() == EventSource.USER){
                playMoveSound();
                board.getScore().add(1);
            }
        }
        viewController.refreshGameBackground(board.getBoardMatrix());
//        board.moveBrickDown();
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent() {
        board.moveBrickLeft();
        playMoveSound();       
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent() {
        board.moveBrickRight();
        playMoveSound();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent() {
        board.rotateBrickLeft();
        playMoveSound();
        return board.getViewData();
    }

    @Override
    public void createNewGame() {
        board.newGame();
        viewController.refreshGameBackground(board.getBoardMatrix());
    }
    
    public SoundController getBgMusic(){
        return bgMusic;
    }
    
    public void playMoveSound(){
        sfxSound.setSource(moveBrickSound);
        sfxSound.setVol(0.1);
        sfxSound.playMedia();
    }
    public void playPlaceBrickSound(){
        sfxSound.setSource(brickPlaceSound);
        sfxSound.setVol(0.5);
        sfxSound.playMedia();
    }
    public void playBonusScoreSound(){
        sfxSound.setSource(bonusScoreSound);
        sfxSound.setVol(0.8);
        sfxSound.playMedia();
    }
    public void playOverSound(){
        sfxSound.setSource(overSound);
        sfxSound.setVol(1);
        sfxSound.playMedia();
    }
}
