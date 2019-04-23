package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

	private Stage primaryStage;
	private StackPane stackPane;//中间的部分
	private AnchorPane mainPane;//中间右边的部分
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(new Image("file:resources/img/icon.png"));
		this.primaryStage.setWidth(1030);
		this.primaryStage.setHeight(680);
//		this.primaryStage.initStyle(StageStyle.UNIFIED);FIXME 阴影效果
		initRootLayout();
	}

	private void initRootLayout() {
		HBox hbox = new HBox();
		ImageView imgIcon = new ImageView();
		imgIcon.setImage(new Image("file:resources/img/icon1.png"));
		ImageView butnBack = new ImageView();
		butnBack.setImage(new Image("file:resources/img/back.png"));
		ImageView butnForword = new ImageView();
		butnForword.setImage(new Image("file:resources/img/forword.png"));
		TextField searchBar = new TextField();
		searchBar.setPromptText("搜索音乐,视频,歌词,电台");
		
		
		
		hbox.getChildren().addAll(imgIcon,butnBack,butnForword);
		Scene scene = new Scene(hbox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}
