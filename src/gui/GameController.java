
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

    public GameController(GuiController c) {
        this.viewController = c;
        board.createNewBrick();
        viewController.setEventListener(this);
        viewController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewController.bindScore(board.getScore().scoreProperty());
        viewController.bindHighScore(board.getHighScore().highScoreProperty());
    }
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if(!canMove){
            board.mergeBrickToBackground();
            clearRow = board.clearRow();
            if(clearRow.getLinesRemoved() > 0){
                board.getScore().add(clearRow.getScoreBonus());
            }
//            System.out.println(clearRow.getLinesRemoved());
            if(board.createNewBrick()){
                viewController.gameOver();
            }
//            board.createNewBrick();
        }
        else{
            if(event.getEventSource() == EventSource.USER){
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
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent() {
        board.moveBrickRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent() {
        board.rotateBrickLeft();
        return board.getViewData();
    }

    @Override
    public void createNewGame() {
        board.newGame();
        viewController.refreshGameBackground(board.getBoardMatrix());
    }
    

    
}
