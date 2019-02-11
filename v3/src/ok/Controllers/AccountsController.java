package ok.Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ok.Account;
import ok.AccountsHandler;
import ok.ClientHandler;
import ok.Utils;

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
    @FXML private JFXButton btn_send;
    @FXML private ImageView img_logo_cancel;
    @FXML private Group group_text;
    private static AccountsHandler ah = SecondaryController.ah;
    private Utils utils = new Utils();
    
    @FXML 
    void showOptions(MouseEvent event) {
    	if(event.getButton() == MouseButton.SECONDARY) {
	    	 JFXPopup popup = new JFXPopup(this.list_accounts);
	    	 JFXButton copy = new JFXButton("Copy IBAN");
	    	 copy.setMinWidth(120);
	    	 JFXButton copyE = new JFXButton("Copy everything");
	    	 copyE.setMinWidth(120);
	    	 JFXButton send = new JFXButton("Send money");
	    	 send.setMinWidth(120);
	    	 copy.setPadding(new Insets(10));
	    	 copyE.setPadding(new Insets(10));
	    	 send.setPadding(new Insets(10));
	         copy.setOnMouseClicked((e)-> { 	 
	        	 copyToCB(this.list_accounts.getSelectionModel().getSelectedItem().toString().split("      ")[0].split("ＩＢＡＮ: ")[1]);  
	        	 popup.hide(); 
	         });
	         copyE.setOnMouseClicked((e)-> { 
	        	 copyToCB(this.list_accounts.getSelectionModel().getSelectedItem().toString());
	        	 popup.hide(); 	 
	         });
	         
	         send.setOnMouseClicked((e)-> {
				try {
					sendM(this.list_accounts.getSelectionModel().getSelectedItem().toString().split("      ")[0].split("ＩＢＡＮ: ")[1]);
		        	popup.hide(); 
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
	    	 VBox vbox = new VBox(copy, copyE, send);

	    	 popup.setPopupContent(vbox);
	         popup.show(this.list_accounts, PopupVPosition.TOP, PopupHPosition.LEFT, event.getX(), event.getY());

    	}
    }
    
    void copyToCB(String str) {
    	
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(str);
        clipboard.setContent(content);
    }
    
    @FXML
    void sendMoney(ActionEvent event) throws NumberFormatException, IOException {
    	this.sendM();
    }
    
    private void sendM() throws NumberFormatException, IOException {
    	Optional<String> result = utils.showInputDialog("ＩＢＡＮ", this.acc_root.getScene(), false).showAndWait();
    	if (result.isPresent() && !result.get().isEmpty()){
    		Optional<String> result2 = utils.showInputDialog("Ｓｕｍ　ｔｏ　ｓｅｎｄ　ｔｏ  " + result.get(), this.acc_root.getScene(), false).showAndWait();
    		if (result2.isPresent() && !result2.get().isEmpty()){
    			switch(ah.sendMoney(Double.valueOf(result2.get()), ah.getAccountByType(0).getIBAN(), result.get())) {
    				case 1:
    					utils.showInfoDialog("Ｓｕｃｃｅｓｓｆｕｌｌｙ　ｓｅｎｔ　" + result2.get() + "　ｔｏ　" + result.get(), this.acc_root.getScene());
    					this.updateList();
    					break;
    				case 0:
    					utils.showAlertDialog("Ｃｏｕｌｄ　ｎｏｔ　ｓｅｎｄ　" + result2.get() + " ｔｏ　" + result.get(), this.acc_root.getScene());
    					break;
    				default:
    					utils.showAlertDialog("Ｓｏｍｅｔｈｉｎｇ　ｗｅｎｔ　ｅｘｔｒｅｍｅｌｙ　ｗｒｏｎｇ．　：）", this.acc_root.getScene());
    					break;
    			}
    		}		
    	}
    }
    
    private void sendM(String IBAN) throws NumberFormatException, IOException {
		Optional<String> result = utils.showInputDialog("Ｓｕｍ　ｔｏ　ｓｅｎｄ　ｔｏ  " + IBAN, this.acc_root.getScene(), false).showAndWait();
		if (result.isPresent() && !result.get().isEmpty()){
    		switch(ah.sendMoney(Double.valueOf(result.get()), ah.getAccountByType(0).getIBAN(), IBAN)) {
    			case 1:
    				utils.showInfoDialog("Ｓｕｃｃｅｓｓｆｕｌｌｙ　ｓｅｎｔ　" + result.get() + "　ｔｏ　" + IBAN, this.acc_root.getScene());
    				this.updateList();
    				break;
    			case 0:
    				utils.showAlertDialog("Ｃｏｕｌｄ　ｎｏｔ　ｓｅｎｄ　" + result.get() + " ｔｏ　" + IBAN, this.acc_root.getScene());
    				break;
    			default:
    				utils.showAlertDialog("Ｓｏｍｅｔｈｉｎｇ　ｗｅｎｔ　ｅｘｔｒｅｍｅｌｙ　ｗｒｏｎｇ．　：）", this.acc_root.getScene());
    				break;
    		}
    	}		
    }
    
    
    
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
    	utils.showInfoDialog("Ｗｏｒｋ　ｉｎ　ｐｒｏｇｒｅｓｓ👷‍♂️", this.acc_root.getScene());
    }

    @FXML
    void showPrintout(ActionEvent event) {
    	utils.showInfoDialog("Ｗｏｒｋ　ｉｎ　ｐｒｏｇｒｅｓｓ👷‍♂️", this.acc_root.getScene());
    }

    @FXML
    void showYearlyPrintout(ActionEvent event) {
    	utils.showInfoDialog("Ｗｏｒｋ　ｉｎ　ｐｒｏｇｒｅｓｓ👷‍♂️", this.acc_root.getScene());
    }
    
    @FXML
    void initialize() {
    	this.lbl_name.setText(ClientHandler.getClient().getFirstName());
    	this.updateList();
    }
    
    public static void setHandler(AccountsHandler ach) {
    	ah = ach;
    }
    
    private void updateList() {
    	if(!this.list_accounts.getItems().isEmpty()) {
    		this.list_accounts.getItems().clear();
    	}
    	for(Map.Entry<String, Account> s : ah.getAccounts().entrySet()) {
    		Label acc = new Label();
    		acc.setText("ＩＢＡＮ: " + s.getValue().getIBAN() + "      Ｔｙｐｅ: " + s.getValue().getAccType() + "      Ｂａｌａｎｃｅ: " + s.getValue().getBalance() + s.getValue().getCurrency());
    		this.list_accounts.getItems().add(acc);
    	}
    }
}
