
package logic.bricks;

import gui.GuiController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Paint;
import logic.MatrixOperations;

public class IBrick implements Brick{
    private final List<int[][]> brickMatrix = new ArrayList<>();
    private int color;
    public IBrick(){
       do{color = (new Random()).nextInt(GuiController.lenghtColor());}while(color == 0);
       brickMatrix.add(new int[][]{
           {0, 0, 0, 0},
           {color, color, color, color},
           {0, 0, 0, 0},
           {0, 0, 0, 0},
       });
       brickMatrix.add(new int[][]{
           {0, color, 0, 0},
           {0, color, 0, 0},
           {0, color, 0, 0},
           {0, color, 0, 0},
       });
       
    }

    public List<int[][]> getBrickMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
    
    
}
