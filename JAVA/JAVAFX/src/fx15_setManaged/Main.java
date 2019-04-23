package fx15_setManaged;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		Button butn1= new Button("Button1");
		Button butn2= new Button("Button2");
		Button butn3= new Button("Button3");
		Button butn4= new Button("Button4");
		
		Button butn5= new Button("b3.setManaged(false)");
		Button butn6= new Button("b3.setVisible(false)");
		Button butn7= new Button("b3.setOpacity(0)");
		
		AnchorPane ap = new AnchorPane();
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(butn1,butn2,butn3,butn4);
		
		ap.getChildren().addAll(hbox);
		
		Scene scene = new Scene(ap);
		
		primaryStage.setScene(scene);
		primaryStage.setHeight(800);
		primaryStage.setWidth(800);
		primaryStage.setTitle("fx");
		primaryStage.show();
	}

}
