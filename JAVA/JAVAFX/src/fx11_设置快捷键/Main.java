package fx11_设置快捷键;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button butn1 = new Button();
		butn1.setPrefWidth(200);
		butn1.setPrefHeight(100);
		butn1.setLayoutX(50);
		butn1.setLayoutY(100);
		butn1.setText("确定");
		butn1.setStyle("-fx-background-color:#436EEE;-fx-text-fill:#FFFFFF;-fx-cursor:hand;-fx-background-radius:20;");
//		butn1.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println("butn1的单击事件....");
//			}
//		});
		
		butn1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount()==1) {
					System.out.println("鼠标点击");
				}
				if(event.getClickCount()==2&&event.getButton().name().equals(MouseButton.PRIMARY.name())) {
					System.out.println("鼠标双击左键");
				}
				System.out.println("鼠标点击="+event.getButton().name());
			}
		});
		
		butn1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().getName().equals(KeyCode.ENTER.getName())) {
					System.out.println("按下"+event.getCode().getName());
				}
			}
		});
		
//		butn1.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent event) {
//				System.out.println("释放"+event.getCode().getName());
//			}
//		});
		
		
		
		Group group = new Group();
		group.getChildren().add(butn1);
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);
		
		
		
		//快捷键
//		KeyCombination kc1 = new KeyCharacterCombination("A",KeyCombination.CONTROL_DOWN);
//		Mnemonic mnemonic = new Mnemonic(butn1, kc1);
//		scene.addMnemonic(mnemonic);
		
		KeyCombination kc = new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN);
		scene.getAccelerators().put(kc, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("快捷键");
			}
		});
		
		primaryStage.show();
	}
	
}
