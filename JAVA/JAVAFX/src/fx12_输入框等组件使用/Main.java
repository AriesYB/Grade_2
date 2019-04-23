package fx12_输入框等组件使用;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root= new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JAVAFX");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		
		TextField text = new TextField();
		text.setText("这是文本");
		text.setLayoutX(100);
		text.setLayoutY(100);
		text.setPrefWidth(100);
		text.setPrefHeight(100);
		text.setFont(Font.font(20));
		
		Tooltip tip = new Tooltip("提示");
		text.setTooltip(tip);//提示
		tip.setFont(Font.font(40));
		text.setPromptText("请输入··");
		text.setFocusTraversable(false);
		
		
		
		root.getChildren().add(text);
		
		primaryStage.show();
	}

}
