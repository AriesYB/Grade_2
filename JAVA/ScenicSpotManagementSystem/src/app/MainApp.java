package app;

import java.io.IOException;
import java.util.ArrayList;

import app.model.Place;
import app.util.DataUtil;
import app.view.AdminCheckController;
import app.view.AdminMenuController;
import app.view.GuestMenuController;
import app.view.MainController;
import app.view.PlaceDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private Stage mainStage;
	private Scene scene;
	private ObservableList<Place> placeData;// table数据集合
	private ArrayList<Place> placeArray;

	public MainApp() {
		// 初始化数据
		placeArray = new ArrayList<Place>();
		for (int i = 0; i < DataUtil.getData().getArr().size(); i++) {
			placeArray.add(DataUtil.getData().getMap().get(DataUtil.getData().getArr().get(i)));
		}
		placeData = FXCollections.observableArrayList(placeArray);
	}

	@Override
	public void start(Stage primaryStage) {
		// 创建主窗口
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("景区信息查询终端");
		this.primaryStage.setResizable(false);
		this.primaryStage.getIcons().add(new Image("file:resources/img/icon.jpg"));

		initMainPane();
	}

	/**
	 * 初始化Main页面
	 */
	public void initMainPane() {
		try {
			// 加载Main页面
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
			AnchorPane mainPane = (AnchorPane) loader.load();
			MainController controller = loader.getController();
			controller.init(this);
			// 在scene显示
			scene = new Scene(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进入游客模块
	 */
	public void entryGuest() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GuestMenu.fxml"));
			AnchorPane guestMenu = (AnchorPane) loader.load();
			scene = new Scene(guestMenu);// 加载场景
			mainStage = new Stage();// new新舞台
			mainStage.setTitle("景区信息");
			mainStage.setResizable(false);
			mainStage.getIcons().add(new Image("file:resources/img/icon.jpg"));
			mainStage.setScene(scene);
			primaryStage.close();// 关闭原舞台
			mainStage.show();
			GuestMenuController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进入管理员模块
	 */
	public void entryAdmin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AdminMenu.fxml"));
			AnchorPane adminMenu = (AnchorPane) loader.load();
			scene = new Scene(adminMenu);
			primaryStage.close();
			mainStage = new Stage();
			mainStage.setTitle("景区管理");
			mainStage.setResizable(false);
			mainStage.getIcons().add(new Image("file:resources/img/icon.jpg"));
			mainStage.setScene(scene);
			mainStage.show();
			AdminMenuController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回主菜单
	 */
	public void backMain() {
		if (mainStage != null) {
			mainStage.close();
		}
		initMainPane();
	}

	/**
	 * 返回到登录页面
	 */
	public void backCheck() {
		if (mainStage != null) {
			mainStage.close();
		}
		primaryStage.show();
		entryCheck();
	}

	/**
	 * 管理员账登录页面
	 */
	public void entryCheck() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AdminCheck.fxml"));
			AnchorPane adminCheck = (AnchorPane) loader.load();
			scene = new Scene(adminCheck);
			primaryStage.setScene(scene);
			AdminCheckController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑信息窗口
	 */
	public boolean showEditDialog(Place p) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlaceDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("编辑景点信息");
			dialogStage.getIcons().add(new Image("file:resources/img/icon.jpg"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			PlaceDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPlace(p);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public ArrayList<Place> getPlaceArray() {
		return placeArray;
	}

	public void setPlaceData(ObservableList<Place> placeData) {
		this.placeData = placeData;
	}

	public ObservableList<Place> getPlaceData() {
		return placeData;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
