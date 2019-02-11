package ok.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SectionController {
	
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private AnchorPane section_root;
    @FXML private ImageView img_logo;
    @FXML private Label lbl_insert_card;
        
    @FXML
    void initialize() {
    	TranslateTransition tt = new TranslateTransition(Duration.millis(3000), img_logo);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.lbl_insert_card);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        
    	tt.setFromY(100);
    	tt.setToY(150);
    	tt.setCycleCount(Timeline.INDEFINITE);
    	tt.setAutoReverse(true);
     
    	tt.play();    	
    	ft.play();
    	Media media = new Media(this.getClass().getResource("/res/audio/theme.mp3").toExternalForm());
        MediaPlayer player = new MediaPlayer(media); 
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }
}
