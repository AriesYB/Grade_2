package app;

import java.io.IOException;

import app.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainApp extends Application {

	private Stage mainStage;// 程序主窗口

	public MainApp() {// 构造方法 可以初始化数据等

	}

	@Override
	public void start(Stage primaryStage) {
			this.mainStage = primaryStage;
			this.mainStage.setTitle("易库");
			this.mainStage.setResizable(false);
			initMainPane();//初始化主页面
	}

	private void initMainPane() {
			try {
				// 加载主页面
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
				AnchorPane mainPane = (AnchorPane) loader.load();
				MainController controller = loader.getController();
				controller.setMainApp(this);
				// 在scene显示
				Scene scene = new Scene(mainPane);
				mainStage.setScene(scene);
				mainStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
