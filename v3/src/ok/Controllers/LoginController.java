package ok.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ok.ClientHandler;
import ok.Utils;

public class LoginController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private AnchorPane login_root;
    @FXML private JFXPasswordField txt_pin;
    @FXML private JFXTextField txt_email;
    @FXML private JFXButton btn_login;
    @FXML private ImageView img_login_logo;
    @FXML private Group group_login;
    private Utils utils = new Utils();
    
    @FXML
    void login(ActionEvent event) throws NumberFormatException, NoSuchAlgorithmException, IOException {
    	String username = this.txt_email.getText();
    	String pin = this.txt_pin.getText();
    	this.btn_login.setDisable(true);
    	
    	if(username.isEmpty() || username == null) {
    		this.txt_email.requestFocus();
    		this.txt_email.setFocusColor(Color.RED);
    		this.txt_email.setUnFocusColor(Color.RED);
    		this.txt_email.setFocusColor(Color.RED);
    		this.txt_email.setUnFocusColor(Color.RED);
    		System.out.println("Username must not be empty!");
    		this.btn_login.setDisable(false);
    		return;
    	}
    	
    	if(pin.isEmpty() || pin == null) {
    		this.txt_email.requestFocus();
    		this.txt_email.setFocusColor(Color.RED);
    		this.txt_email.setUnFocusColor(Color.RED);
    		this.txt_email.setFocusColor(Color.RED);
    		this.txt_email.setUnFocusColor(Color.RED);
    		System.out.println("PIN must not be empty!");
    		this.btn_login.setDisable(false);
    		return;
    	}
    	    	
    	if(ClientHandler.auth(username, Integer.valueOf(pin))) {
        	AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/ok/FXML/Final.fxml"));
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
        	MainController.mainPane.getChildren().setAll(pane);
    	}else {
    		this.btn_login.setDisable(false);
    		utils.showAlertDialog("Ｅ－ｍａｉｌ　ｏｒ　ＰＩＮ　ａｒｅ　ｉｎｃｏｒｒｅｃｔ．", this.login_root.getScene());
    	}
    }
    
    @FXML
    void initialize() {
    	System.out.println("Initialized Login interface.");
    	TranslateTransition tt = new TranslateTransition(Duration.millis(1500), this.img_login_logo);
    	tt.setFromY(150);
    	tt.setToY(50);
    	tt.setCycleCount(1);
    	tt.setAutoReverse(false);
    	tt.setOnFinished(e -> {
    		
            FadeTransition ft = new FadeTransition(Duration.millis(500), this.group_login);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            
            ft.play();
        });
    	tt.play();
    	
    	
    	txt_pin.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<?extends String> observable, String oldValue, String newValue) {
    	        if(newValue.length() > 4) {
    	        	txt_pin.setText(newValue.substring(0, 4));
    	        }
    	    	if (!newValue.matches("\\d*")) {
    	        	txt_pin.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }
    
}
