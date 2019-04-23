package fx5_PlatForm类的使用;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start方法里线程="+Thread.currentThread().getName());
		Platform.runLater(new Runnable() {//更新UI组件 但是太多的话会阻塞
			
			@Override
			public void run() {
				System.out.println("run方法里线程="+Thread.currentThread().getName());
				int i = 1;
				while(i<=10) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("i="+i);
					i++;
				}
			}
		});
		System.out.println("runlater下面");
	}
}
