package ok.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONException;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ok.AccountsHandler;
import ok.ClientHandler;
import ok.Utils;

public class SecondaryController {


    @FXML private ResourceBundle resources;

    @FXML private URL location;

    @FXML private AnchorPane second_root;
    
    @FXML private AnchorPane cancel_overlay;

    @FXML private ImageView img_logo_cancel;

    @FXML private Group group_text;
    
    @FXML private JFXButton btn_quick_trans;

    @FXML private JFXButton btn_withdraw;

    @FXML private JFXButton btn_quick_trans2;

    @FXML private JFXButton btn_quick_trans1;
    
    @FXML private JFXButton btn_deposit;
    
    @FXML private JFXButton btn_cancel;
    
    @FXML private Label lbl_welcome;

    @FXML private JFXButton btn_accs;

    @FXML private Label lbl_currbalance;
    
    public static AccountsHandler ah = new AccountsHandler();
    private Utils utils = new Utils();
    
    
    @FXML 
    void cancel(ActionEvent event) {
       	this.cancel_overlay.setDisable(false);
    	TranslateTransition tt = new TranslateTransition(Duration.millis(2500), this.img_logo_cancel);
    	FadeTransition ft = new FadeTransition(Duration.millis(1500), this.cancel_overlay);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play();
        
    	tt.setFromY(-250);
    	tt.setToY(35);
    	tt.setCycleCount(1);
    	tt.setAutoReverse(false);
    	tt.setOnFinished(e -> {
    		
            FadeTransition ft2 = new FadeTransition(Duration.millis(1000), this.group_text);
            ft2.setFromValue(1.0);
            ft2.setToValue(0);
            ft2.setCycleCount(10);
            ft2.setAutoReverse(true);
            ft2.setOnFinished(e2 -> {
            	System.exit(0);
            });
            ft2.play();
        });
    	tt.play();
    }
    
    @FXML 
    void initAccs(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ok/FXML/Accounts.fxml"));
    	AnchorPane pane = loader.load();
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    	this.second_root.getChildren().addAll(pane);
    }
    
    @FXML
    void initDeposit(ActionEvent event) {
    	Optional<String> result = utils.showInputDialog("Ａｍｏｕｎｔ　ｔｏ　ｄｅｐｏｓｉｔ：　", this.second_root.getScene()).showAndWait();
    	if (result.isPresent() && !result.get().isEmpty()){
    	    int r = ah.deposit(Double.valueOf(result.get()), 0);
    		switch(r) {
    		case -1: 
    			utils.showAlertDialog("Ａｍｏｕｎｔ　ｓｈｏｕｌｄ　ｂｅ　ａ　ｍｕｌｔｉｐｌｅ　ｏｆ　１０！", this.second_root.getScene());
    			break;
    		case 1:
    			this.lbl_currbalance.setText(ah.getAccountByType(0).getBalance() + " " + ah.getAccountByType(0).getCurrency());
    			utils.showInfoDialog("Ｔｈａｎｋ　ｙｏｕ　ｆｏｒ　ｕｓｉｎｇ　ＡＥＳＴＨＥＴＩＣ　ＢＡＮＫ．　Ｐｌｅａｓｅ　ｐｉｃｋ　ｕｐ　ｙｏｕｒ　ｒｅｃｅｉｐｔ.", this.second_root.getScene());
    			break;
    		}
    	}
    }
    
    @FXML
    void initWithdraw(ActionEvent event) {

    	Optional<String> result = utils.showInputDialog("Ａｍｏｕｎｔ　ｔｏ　withdraw： ", this.second_root.getScene()).showAndWait();
    	if (result.isPresent() && !result.get().isEmpty()){
    	    int r = ah.withdraw(Double.valueOf(result.get()), 0);
    		switch(r) {
    		case -1: 
    			utils.showAlertDialog("Ａｍｏｕｎｔ　ｓｈｏｕｌｄ　ｂｅ　ａ　ｍｕｌｔｉｐｌｅ　ｏｆ　１０！", this.second_root.getScene());
    			break;
    		case 0:
    			utils.showAlertDialog("Ｉｎｓｕｆｆｉｃｉｅｎｔ　ｆｕｎｄｓ．", this.second_root.getScene());
    			break;
    		case 1:
    			this.lbl_currbalance.setText(ah.getAccountByType(0).getBalance() + " " + ah.getAccountByType(0).getCurrency());
    			utils.showInfoDialog("Ｔｈａｎｋ　ｙｏｕ　ｆｏｒ　ｕｓｉｎｇ　ＡＥＳＴＨＥＴＩＣ　ＢＡＮＫ．　Ｐｌｅａｓｅ　ｐｉｃｋ　ｕｐ　ｙｏｕｒ　ｒｅｃｅｉｐｔ.", this.second_root.getScene());
    			break;
    		}
    	}
    }

    @FXML
    void quickWithdraw(ActionEvent event) {
    	String sum = ((Button)event.getSource()).getText().split(" ")[0];
		if(ah.withdraw(Double.valueOf(sum), 0) == 0) {
			utils.showAlertDialog("Ｉｎｓｕｆｆｉｃｉｅｎｔ　ｆｕｎｄｓ．", ((Button)event.getSource()).getScene());
		}else {
			this.lbl_currbalance.setText(ah.getAccountByType(0).getBalance() + " " + ah.getAccountByType(0).getCurrency());
		}
    }
    
    @FXML
    void initialize() throws JSONException, IOException {
    	ah.init();
    	this.btn_quick_trans.setText("50 " + ah.getAccountByType(0).getCurrency());
    	this.btn_quick_trans1.setText("100 " + ah.getAccountByType(0).getCurrency());
    	this.lbl_welcome.setText(ClientHandler.getClient().getFirstName());
    	this.lbl_currbalance.setText(ah.getAccountByType(0).getBalance() + " " + ah.getAccountByType(0).getCurrency());
    }
}
