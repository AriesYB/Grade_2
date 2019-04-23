package fx3_Stage窗口;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JAVAFX");
		primaryStage.getIcons().add(new Image("/icon/icon.jpg"));
//		primaryStage.setIconified(true);//设置最小化
		primaryStage.setWidth(700);
		primaryStage.setHeight(500);
		//用于宽高自适应
		primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("当前宽度="+newValue.floatValue());
			}
		
		});
		primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("当前高度="+newValue.floatValue());
			}
		});
		
		primaryStage.setFullScreen(true);//全屏 必须设置Scene
		primaryStage.setScene(new Scene(new Group()));
		
		primaryStage.show();
		
	}

}
