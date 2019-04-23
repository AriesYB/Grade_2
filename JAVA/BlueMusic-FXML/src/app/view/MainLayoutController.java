package app.view;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainLayoutController {
	
	private MainApp mainApp;
	

    @FXML
    void showFindMusic(ActionEvent event) {
    	mainApp.showFindMusic();
    }

	
    @FXML
    void showLocalMusic(ActionEvent event) {
    	mainApp.showLocalMusicPage();
    }

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
