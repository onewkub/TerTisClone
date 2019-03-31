
package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        ResourceBundle resource = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resource);
        Parent root = fxmlLoader.load();
        GuiController c = fxmlLoader.getController();
//        Media media = new Media("file:///F:/TertisClone/src/resources/BanksTetrominoes.wav");
//        MediaPlayer player = new MediaPlayer(media);
        // Sound BackGround Player
//        player.setCycleCount(MediaPlayer.INDEFINITE);
//        player.play();
        
        
        
        
        
        primaryStage.setTitle("TirtisFX");
        Scene scene = new Scene(root, 420, 540);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
//        primaryStage.setResizable(false);
        
        primaryStage.show();
        
        new GameController(c);
        
    }
    
}
