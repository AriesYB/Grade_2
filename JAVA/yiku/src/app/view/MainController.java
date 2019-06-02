package app.view;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {
	private MainApp mainApp;
	private String color_1 = "-fx-background-color:	#E0FFFF";
	private String color_2 = "-fx-background-color:white";
	private boolean flag = true;

	@FXML
	private AnchorPane pane;

	@FXML
	private Button button;

	@FXML
	void changeColor(ActionEvent event) {
		if (flag) {
			pane.setStyle(color_1);
		} else {
			pane.setStyle(color_2);
		}
		flag = !flag;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
