
package logic;

import java.awt.Point;
import logic.bricks.Brick;
import logic.bricks.RandomBrickGenerator;

public class SimpleBoard {
    
    private final int width;
    private final int height;
    private int[][] currentGameMatrix;
    private final RandomBrickGenerator brickGenerator;
    private Brick brick;
    private int currentShape = 0;
    private Point currentOffset;
    private Score score;
    
    public SimpleBoard(int height, int width) {
        this.width = width;
        this.height = height;
        currentGameMatrix = new int[height][width];
        brickGenerator = new RandomBrickGenerator();
        score = new Score();
    }
//    public void clearBoard(){
//        for(int i = 0; i < height; i++){
//            for(int j = 0; j < width; j++){
//                currentGameMatrix[i][j] = 0;
//            }
//        }
//        score = new Score();
//    }
    public void setBrick(Brick brick){
        this.brick = brick;
        currentOffset = new Point(3, 0);
    }
    public int[][] getCurrentShape(){
        return brick.getBrickMatrix().get(currentShape);
    }
    public boolean createNewBrick(){
        currentShape = 0;
        Brick currentBrick = brickGenerator.getBrick();
        setBrick(currentBrick);
        currentOffset = new Point(3, 0);
        return MatrixOperations.intersects(
                currentGameMatrix, getCurrentShape(),
                (int) currentOffset.getX(), (int) currentOffset.getY());
    }
    public int[][] getBoardMatrix(){
        return currentGameMatrix;
    }
    public ViewData getViewData(){
        return new ViewData(getCurrentShape(), (int)currentOffset.getX(), 
                (int) currentOffset.getY(), brickGenerator.getNextBricks().getBrickMatrix().get(0));
    }
    
    public boolean moveBrickDown(){
        int [][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        Point p = new Point(currentOffset);
        p.translate(0, 1);
        currentOffset = p;
        boolean conflict = MatrixOperations.intersects(currentMatrix, getCurrentShape(), 
                (int) p.getX() , (int) p.getY());
        
        if(conflict){
//            System.out.println("Out Of Bounds");
            return false;
        }
        else{
            currentOffset = p;
            return true;
        }
    }
    public boolean moveBrickLeft() {
        int [][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        Point p = new Point(currentOffset);
//        System.out.printf("x:%d y:%d\n", (int) p.getX(), (int) p.getY()); 
        p.translate(-1, 0);
        boolean conflict = MatrixOperations.intersects(currentMatrix, getCurrentShape(), 
                (int) p.getX() , (int) p.getY());
        if(conflict){
//            System.out.println("Out Of Bounds");
            return false;
        }
        else{
            currentOffset = p;
            return true;
        }
        
    }
    public boolean moveBrickRight() {
        int [][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        Point p = new Point(currentOffset);
        p.translate(1, 0);
        boolean conflict = MatrixOperations.intersects(currentMatrix, getCurrentShape(), 
                (int) p.getX() , (int) p.getY());
        if(conflict){
//            System.out.println("Out Of Bounds");
            return false;
        }
        else{
            currentOffset = p;
            return true;
        }
        
    }
    public Score getScore() {
        return score;
    }

    public void mergeBrickToBackground() {
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, 
                getCurrentShape(),
                (int) currentOffset.getX(), 
                (int) currentOffset.getY());
//        for(int i = 0; i < currentGameMatrix.length; i++){
//            for(int j = 0; j < currentGameMatrix[i].length; j++){
//                System.out.printf("%d ",currentGameMatrix[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.printf("%d %d\n", currentGameMatrix.length, currentGameMatrix[0].length);
    }

    public boolean rotateBrickLeft() {
        int [][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        NextShapeInfo nextShape = getNextShape();
//        System.out.printf("currShape = %d\n", currentShape);
        boolean conflic = MatrixOperations.intersects(
                currentMatrix, nextShape.getShape(), 
                (int) currentOffset.getX(),
                (int) currentOffset.getY());
        if(conflic){
            return false;
        }
        else{
            setCurrentShape(nextShape.getPosition());
            return true;
        }
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }
    
    
    
    public NextShapeInfo getNextShape(){
        int nextShape = currentShape;
        nextShape = ++nextShape % brick.getBrickMatrix().size();
        return new NextShapeInfo(brick.getBrickMatrix().get(nextShape), nextShape);
    }

    public ClearRow clearRow() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        currentGameMatrix = clearRow.getNewMatrix();
        return clearRow;
    }

    public void newGame() {
        currentGameMatrix = new int[height][width];
        score.reset();
        createNewBrick();
    }


    
}
