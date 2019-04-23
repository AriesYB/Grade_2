package app.view;


import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.MainApp;
import app.util.DataUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;

public class AdminCheckController {
	@FXML
	private Line line_1;

	@FXML
	private Line line_2;

	@FXML
	private Line line_3;
	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXTextField account;

	@FXML
	private JFXButton login_butn;

	private MainApp mainApp;

	@FXML
	private AnchorPane pane_butn;

	@FXML
	void checkAccount(ActionEvent event) {
		if (DataUtil.getData().checkAccount(account.getText(), password.getText())) {// 登录成功
			mainApp.entryAdmin();
		} else {
			JFXDialogLayout layout = new JFXDialogLayout();
			Label n =new Label("账号或密码错误");
			layout.setBody(n);
			layout.setPrefWidth(200);
			JFXAlert<Void> alert = new JFXAlert<>(mainApp.getPrimaryStage());
			alert.setOverlayClose(true);
			alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);// 提示在窗口的中央
			alert.setContent(layout);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}
	}

	@FXML
	void backToMain(MouseEvent  event) {
		mainApp.backMain();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'AdminCheck.fxml'.";
		assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'AdminCheck.fxml'.";
		assert login_butn != null : "fx:id=\"login_butn\" was not injected: check your FXML file 'AdminCheck.fxml'.";
	}

	@FXML
	void setPressedColor(MouseEvent event) {
		pane_butn.setStyle("-fx-background-color:#dfdfdf");
	}

	@FXML
	void setReleasedColor(MouseEvent event) {
		pane_butn.setStyle("-fx-background-color: #a4a4a4");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
