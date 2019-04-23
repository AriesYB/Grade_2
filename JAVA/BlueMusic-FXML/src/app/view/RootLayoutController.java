package app.view;

import app.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RootLayoutController {
	private MainApp mainApp;
	
	@FXML
	private ImageView back;

	@FXML
	private ImageView forword;

	@FXML
	private TextField searchBar;

	@FXML
	private ImageView searchButn;

	@FXML
	private ImageView downArrow;
	
	@FXML
	private ImageView last;

	@FXML
	private ImageView play;

	@FXML
	private ImageView next;

    @FXML
    private ImageView voice;

	
	@FXML
	private void initialize() {
		back.setDisable(true);
		forword.setDisable(true);
		searchBar.setFocusTraversable(false);
		searchBar.setPromptText("搜索音乐,视频,歌词,电台");
		
//    	BackgroundImage bgi = new BackgroundImage(new Image("file:resources/img/search.png"), null, null, BackgroundPosition.DEFAULT, new BackgroundSize(30, 30, false, false, false, false));
//    	Background bg =new Background(bgi);
//    	searchBar.setBackground(bg);
	}

	@FXML
	private void backFirst(MouseEvent event) {
		mainApp.backHome();
	}

	@FXML
	private void back(MouseEvent event) {
		mainApp.back();
	}

	@FXML
	private void forword(MouseEvent event) {
		mainApp.forword();
	}

	@FXML
	private void search(MouseEvent event) {

	}

	@FXML
	private void personalInfo(MouseEvent event) {
		System.out.println("personalInfo");
	}

	@FXML
	private void showAccount(MouseEvent event) {
		System.out.println("showAccount");
	}

	@FXML
	private void showClothes(MouseEvent event) {
		System.out.println("showClothes");
	}

	@FXML
	private void showSet(MouseEvent event) {
		System.out.println("showSet");
		mainApp.showSetPage();
	}


    @FXML
    private void lastMusic(MouseEvent event) {
    	System.out.println("上一首");
//    	changeBack(false);
    }
    
    @FXML
    private void play(MouseEvent event) {
    	System.out.println("播放");
    }
    
    @FXML
    private void nextMusic(MouseEvent event) {
    	System.out.println("下一首");
//    	changeBack(true);
    }
    
    @FXML
    private void mute(MouseEvent event) {
    	System.out.println("静音");
    }
    
    public void changeBack(boolean flag) {
    	back.setDisable(flag);
    	System.out.println("back="+flag);
    }
    
    public void changeForword(boolean flag) {
    	back.setDisable(flag);
    	System.out.println("forword="+flag);
    }
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


}
