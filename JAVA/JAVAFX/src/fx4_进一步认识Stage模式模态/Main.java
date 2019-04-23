package fx4_进一步认识Stage模式模态;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		primaryStage.setWidth(700);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		
		primaryStage.setOpacity(0.8);//透明度
		primaryStage.setAlwaysOnTop(true);//最上端
		primaryStage.setX(200);//加载出来离边距离
		primaryStage.setY(100);
		
		primaryStage.xProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("窗口离左边距离="+newValue.floatValue());
			}
		});
		primaryStage.show();
		
	}

}
