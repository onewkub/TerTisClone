
package logic.bricks;

import gui.GuiController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZBrick implements Brick{
    private final List<int[][]> brickMatrix = new ArrayList<>();
    private int color;
    public ZBrick() {
        do{color = (new Random()).nextInt(GuiController.lenghtColor());}while(color == 0);
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {color, color, 0, 0},
                {0, color, color, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, color, 0, 0},
                {color, color, 0, 0},
                {color, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }

    public List<int[][]> getBrickMatrix() {
        return brickMatrix;
    }
    
    
}
