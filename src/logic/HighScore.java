package logic;

import java.util.prefs.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HighScore {

    private Preferences prefs;
    private IntegerProperty highScore = new SimpleIntegerProperty(0);;

    public HighScore() {
        prefs = Preferences.userNodeForPackage(this.getClass());
        int temp = prefs.getInt("HIGH_SCORE", 0);
//        System.out.println(temp);
        highScore.setValue(temp);
//        if (prefs.get("HIGH_SCORE", "") != null)  {
////            prefs.get("HIGH_SCORE", "");
//            highScore.setValue(Integer.parseInt(prefs.get("HIGH_SCORE", "")));
//        } else {
//            highScore.setValue(0);
//        }

    }

    public IntegerProperty highScoreProperty() {
        return highScore;
    }

    public void update(IntegerProperty score) {
        int currentScore = score.getValue().intValue();
        int currentHighScore = highScore.getValue().intValue();
        if (currentHighScore < currentScore) {
            highScore.setValue(currentScore);
            prefs.putInt("HIGH_SCORE", currentScore);
        }

    }
}
