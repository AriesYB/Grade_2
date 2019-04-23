package fx2_app启动方式和生命周期;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		System.out.println("main...");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start......");
		primaryStage.show();
	}
	
	@Override
	public void init() throws Exception {
		System.out.println("init....");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("stop....");
	}
}
