package app.view;

import com.jfoenix.controls.JFXTextField;

import app.model.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PlaceDialogController {
	private Stage dialogStage;

	@FXML
	private TextArea introduction;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField popularity;

	@FXML
	private JFXTextField rest;

	@FXML
	private JFXTextField toilet;
	private boolean okClicked = false;
	private Place place;
	@FXML
	private void handOk(ActionEvent event) {//设置对象的相应数据
		place.setName(name.getText());
		place.setPopularity(Integer.parseInt(popularity.getText()));
		if(rest.getText().equals("有")) {			
			place.setHasRest_area(true);
		}else {
			place.setHasRest_area(false);
		}
		if(toilet.getText().equals("有")) {			
			place.setHasToilet(true);
		}else {
			place.setHasToilet(false);
		}
		place.setIntroduction(introduction.getText());
        okClicked = true;
        dialogStage.close();
	}

	@FXML
	private void handCancel(ActionEvent event) {
		dialogStage.close();
	}

	public void setPlace(Place p) {
		this.place = p;
		name.setText(p.getName());
		popularity.setText(String.valueOf(p.getPopularity()));
		if (p.isHasToilet()) {
			toilet.setText("有");
		} else {
			toilet.setText("无");
		}
		if (p.isHasRest_area()) {
			rest.setText("有");
		} else {
			rest.setText("无");
		}
		introduction.setText(p.getIntroduction());
	}
    public boolean isOkClicked() {
        return okClicked;
    }
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
