package ok.Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ok.Account;
import ok.AccountsHandler;
import ok.ClientHandler;

public class AccountsController {

    @FXML private ResourceBundle resources;

    @FXML private URL location;
    
    @FXML private AnchorPane acc_root;
    
    @FXML private AnchorPane cancel_overlay;
    
    @FXML private BorderPane acc_pane;
    
    @FXML private JFXListView<Label> list_accounts;

    @FXML private Label lbl_name;

    @FXML private JFXButton btn_monthly;

    @FXML private JFXButton btn_yearly;

    @FXML private JFXButton btn_full;

    @FXML private JFXButton btn_back;

    @FXML private JFXButton btn_cancel;
    
    @FXML private ImageView img_logo_cancel;
    
    @FXML private Group group_text;
    
    @FXML
    void cancel(ActionEvent event) throws InterruptedException {
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
    void goBack(ActionEvent event) {
    	this.acc_root.getChildren().removeAll(this.acc_pane);
    	this.acc_root.setVisible(false);
    	this.acc_root.setDisable(true);
    }

    @FXML
    void showMonthlyPrintout(ActionEvent event) {

    }

    @FXML
    void showPrintout(ActionEvent event) {
    	
    }

    @FXML
    void showYearlyPrintout(ActionEvent event) {

    }
    private static AccountsHandler ah = SecondaryController.ah;
    
    @FXML
    void initialize() {
    	this.lbl_name.setText(ClientHandler.getClient().getFirstName());
    	for(Map.Entry<String, Account> s : ah.getAccounts().entrySet()) {
    		Label acc = new Label();
    		acc.setText("ＩＢＡＮ: " + s.getValue().getIBAN() + "      Ｔｙｐｅ: " + s.getValue().getAccType() + "      Ｂａｌａｎｃｅ: " + s.getValue().getBalance() + s.getValue().getCurrency());
    		this.list_accounts.getItems().add(acc);
    	}
    }
    
    public static void setHandler(AccountsHandler ach) {
    	ah = ach;
    }
}
