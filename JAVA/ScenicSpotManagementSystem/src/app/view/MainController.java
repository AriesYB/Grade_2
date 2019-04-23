package app.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

public class MainController {
	private Paint p1;
	private Paint p2;

	@FXML
	private AnchorPane pane;

	@FXML
	private Label label;

    @FXML
    private Label label_guest;

    @FXML
    private Label label_admin;
	
	@FXML
	private JFXButton guest_butn;

	@FXML
	private JFXButton admin_butn;

	@FXML
	private JFXToggleButton toggle_butn;

	@FXML
	public void entryGuest(ActionEvent event) {
		mainApp.entryGuest();
	}

	@FXML
	public void entryAdmin(ActionEvent event) {
		mainApp.entryCheck();
	}

	@FXML
	void change(MouseEvent event) {
		if (toggle_butn.isSelected()) {//管理员模式
			guest_butn.setVisible(false);
			admin_butn.setVisible(true);
			p1 = admin_butn.getTextFill();
			p2 = guest_butn.getTextFill();
			label.setTextFill(p2);
			toggle_butn.setTextFill(p2);
			label_guest.setVisible(false);
			label_admin.setVisible(true);
			pane.setBackground(new Background(new BackgroundFill(p1, null, null)));
		} else {//游客模式
			guest_butn.setVisible(true);
			admin_butn.setVisible(false);
			label.setTextFill(p1);
			toggle_butn.setTextFill(p1);
			label_guest.setVisible(true);
			label_admin.setVisible(false);
			pane.setBackground(new Background(new BackgroundFill(p2, null, null)));

		}
	}

	private MainApp mainApp;

	void initialize() {
	}

	public void init(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public Paint getP1() {
		return p1;
	}

}
