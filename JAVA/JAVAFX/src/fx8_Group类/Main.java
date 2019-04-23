package fx8_Group类;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button butn1 = new Button();
		Button butn2 = new Button();
		butn1.setPrefWidth(200);
		butn1.setPrefHeight(100);
		
		butn2.setPrefWidth(200);
		butn2.setPrefHeight(100);
		butn2.setLayoutX(200);
		butn2.setLayoutY(200);
		Group group = new Group();
		System.out.println(group.contains(0,0));
		group.getChildren().add(butn1);
		group.getChildren().add(butn2);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
