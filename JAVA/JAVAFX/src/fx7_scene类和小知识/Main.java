package fx7_scene类和小知识;

import java.net.URL;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Cursor;
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
		
		HostServices host = getHostServices();
		host.showDocument("www.baidu.com");
		
		URL url = getClass().getClassLoader().getResource("icon/icon.jpg");
		String path = url.toExternalForm();
		
		Button butn = new Button();
		butn.setCursor(Cursor.cursor(path));//设置自定义鼠标
		butn.setPrefWidth(200);
		butn.setPrefHeight(100);
		
		Group group = new Group(); 
		group.getChildren().add(butn);
		Scene scene = new Scene(group);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("JAVAFX");
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		
		primaryStage.show();
		
	}

}
