package fx14_VBox和HBox;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Button b1 = new Button("Button1");
		
		Button b2 = new Button("Button2");
		
		HBox hbox = new HBox();
		hbox.setPrefWidth(400);
		hbox.setPrefHeight(400);
		hbox.getChildren().addAll(b1,b2);
		hbox.setStyle("-fx-background-color:#E51248");
//		hbox.setMargin(b1, new Insets(20));
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(50);
		
		AnchorPane ap = new AnchorPane();
		ap.getChildren().add(hbox);
		
		ap.setPadding(new Insets(100));
		ap.setPrefWidth(800);
		ap.setPrefHeight(800);
		ap.setStyle("-fx-background-color:#555555");//Group不行 没有这些效果
		ap.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("点击事件");
			}
		});
		
		
		Scene scene = new Scene(ap);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFx");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		primaryStage.show();
	}

}
