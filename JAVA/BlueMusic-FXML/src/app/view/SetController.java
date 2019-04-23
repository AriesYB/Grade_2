package app.view;

import app.MainApp;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SetController {

	private MainApp mainApp;
	
    @FXML
    void backTo(MouseEvent event) {
    	mainApp.backFromSet();
    }
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	

}
