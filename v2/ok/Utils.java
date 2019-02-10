package ok;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

public class Utils {
	
	public void showAlertDialog(String msg, Scene sc) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setHeaderText(null);
	    alert.setContentText(msg);
	    alert.initOwner(sc.getWindow());
	    alert.initStyle(StageStyle.UNDECORATED);
	    alert.getDialogPane().setGraphic(new ImageView(new Image(this.getClass().getResource("/res/img/Attention.png").toString())));
	    alert.show();
	}
	
	public TextInputDialog showInputDialog(String msg, Scene sc) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setHeaderText(null);
    	dialog.setContentText(msg);
      	dialog.initStyle(StageStyle.UNDECORATED);
      	dialog.initOwner(sc.getWindow());
      	dialog.getDialogPane().getStylesheets().add(this.getClass().getResource("/res/css/Main.css").toExternalForm());
      	dialog.getDialogPane().getStyleClass().add("dialog-pane");
      	dialog.getDialogPane().setGraphic(new ImageView(new Image(this.getClass().getResource("/res/img/bubble_q.png").toString())));
      	dialog.getEditor().textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<?extends String> observable, String oldValue, String newValue) {
    	    	if (!newValue.matches("\\d*")) {
    	    		dialog.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
      	return dialog;
	}
	
	public void showInfoDialog(String msg, Scene sc) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setHeaderText(null);
	    alert.setContentText(msg);
	    alert.initOwner(sc.getWindow());
	    alert.initStyle(StageStyle.UNDECORATED);
	    alert.getDialogPane().setGraphic(new ImageView(new Image(this.getClass().getResource("/res/img/bubble_i.png").toString())));
	    alert.show();
	}
	
}
