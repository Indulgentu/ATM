package ok.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane atm_root;

    @FXML
    private Group btn_left_fourth;

    @FXML
    private Group btn_left_third;

    @FXML
    private Group btn_left_second;

    @FXML
    private Group btn_left_first;

    @FXML
    private Group btn_right_fourth;

    @FXML
    private Group btn_right_third;

    @FXML
    private Group btn_right_second;

    @FXML
    private Group btn_right_first;

    @FXML
    private Group atm_group_indicator;

    @FXML
    private Rectangle atm_blinker;

    @FXML
    private AnchorPane atm_main;
    
    private SectionController sc;
    
    private FadeTransition blinker;
    
    public static AnchorPane mainPane;
    
    @FXML
    void initialize() throws InterruptedException, IOException {
    	initAnimation();
    	initMain();
    } 
    
    @FXML
    void insertCard(MouseEvent event) {
    	System.out.println("Click");
		this.atm_group_indicator.setDisable(true);
    	final Rectangle rectPath = new Rectangle(0, 0, 100, 200);
    	rectPath.setArcHeight(10);
    	rectPath.setArcWidth(10);
    	rectPath.setFill(Color.ORANGE);
    	Path path = new Path();
    	path.getElements().add(new MoveTo(20,20));
    	path.getElements().add(new CubicCurveTo(380, 0, 380, 215, 100, 200));
    	PathTransition pathTransition = new PathTransition();
    	pathTransition.setDuration(Duration.millis(4000));
    	pathTransition.setPath(path);
    	pathTransition.setNode(rectPath);
    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    	pathTransition.setCycleCount(1);
    	pathTransition.setOnFinished(e -> {
    		
            FadeTransition ft = new FadeTransition(Duration.millis(500), rectPath);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            
            ft.play();
            ft.setOnFinished(e2 -> {
	    		rectPath.setWidth(0);
	    		rectPath.setHeight(0);
	    		this.stopAnimation();
	    		try {
					this.initSecond();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });
        });
    	pathTransition.play();
    	this.atm_group_indicator.getChildren().add(rectPath);
    }
    
    private void initMain() throws IOException {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ok/FXML/Intro.fxml"));
    	AnchorPane pane = loader.load();
    	sc = loader.getController();
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    	this.atm_main.getChildren().addAll(pane);
    	mainPane = this.atm_main;
	}
    
    private void initSecond() throws IOException {
    	//this.sc.getMP().stop();
    	AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/ok/FXML/Login.fxml"));
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        
    	this.atm_main.getChildren().setAll(pane);
    }
    
	private void initAnimation() {
        this.blinker = new FadeTransition(Duration.millis(500), this.atm_blinker);
        this.blinker.setFromValue(1.0);
        this.blinker.setToValue(0.3);
        this.blinker.setCycleCount(Timeline.INDEFINITE);
        this.blinker.setAutoReverse(true);
        
        this.blinker.play();
    }
	
	private void stopAnimation() {
		this.blinker.stop();
		this.atm_blinker.setFill(Color.GREEN);
	}
}

