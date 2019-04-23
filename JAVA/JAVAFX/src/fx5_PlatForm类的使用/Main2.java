package fx5_PlatForm类的使用;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main2 extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Platform.setImplicitExit(false);//程序并没有退出 只是关闭了窗口
		
		primaryStage.show();
	}
}
