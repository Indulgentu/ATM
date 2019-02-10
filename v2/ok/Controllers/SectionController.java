package ok.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class SectionController {
    @FXML private ResourceBundle resources;

    @FXML private URL location;
    
    @FXML private AnchorPane section_root;
    
    @FXML private ImageView img_logo;
    
    @FXML private Label lbl_insert_card;
    
    //private MediaPlayer player;
    
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
    	Media media = new Media(this.getClass().getResource("/res/audio/theme.mp3").toExternalForm()); //replace /Movies/test.mp3 with your file
        MediaPlayer player = new MediaPlayer(media); 
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
      /*  player = new MediaPlayer(new Media(getClass().getResource("intro.mp4").toExternalForm()));
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(player);
        mediaView.setPreserveRatio(true);
        InvalidationListener resizeMediaView = observable -> {
            mediaView.setFitWidth(section_root.getWidth());
            mediaView.setFitHeight(section_root.getHeight());

            // After setting a big fit width and height, the layout bounds match the video size. Not sure why and this feels fragile.
            Bounds actualVideoSize = section_root.getLayoutBounds();
            mediaView.setX((section_root.getWidth() - actualVideoSize.getWidth()) / 2);
            mediaView.setY((section_root.getHeight() - actualVideoSize.getHeight()) / 2);
        };
        section_root.heightProperty().addListener(resizeMediaView);
        section_root.widthProperty().addListener(resizeMediaView);
        section_root.getChildren().add(mediaView);

        player.play();
    }
    
    public MediaPlayer getMP() {
    	return this.player;
    }*/
    }
}
