package fx13_AnchorPane布局;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Button b1 = new Button("Button1");
		
		Button b2 = new Button("Button2");
		
		
		AnchorPane ap = new AnchorPane();
		ap.getChildren().addAll(b1,b2);
		ap.setTopAnchor(b1, 0.0);//管理后 setLayoutX无效
		ap.setLeftAnchor(b1, 0.0);
		
		
		ap.setTopAnchor(b2, 100.0);
		ap.setLeftAnchor(b2, 100.0);
		
		ap.setPadding(new Insets(100));
		
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
