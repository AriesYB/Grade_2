package fx4_进一步认识Stage模式模态;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main3 extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage s1= new Stage();
		s1.setTitle("s1");
		s1.show();
		Stage s2= new Stage();
		s2.initOwner(s1);//s2是属于s1的
		s2.initModality(Modality.WINDOW_MODAL);//该窗口关闭之后才能点击它的所有者
		s2.setTitle("s2");
		s2.show();
		
		Stage s3= new Stage();
		s3.initModality(Modality.APPLICATION_MODAL);//设置窗口模态 该窗口其他所有窗口在关闭该窗口之后才可点击
		s3.setTitle("s3");
		s3.show();
	}

}
