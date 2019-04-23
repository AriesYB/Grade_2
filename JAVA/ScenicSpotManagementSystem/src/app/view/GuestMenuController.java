package app.view;

import java.text.Collator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import app.MainApp;
import app.model.AdjPlace;
import app.model.Car;
import app.model.Place;
import app.util.DataUtil;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Duration;

public class GuestMenuController {

	@FXML
	private AnchorPane mainPane_1;
	@FXML
	private AnchorPane mainPane_2;
	@FXML
	private JFXHamburger hamburger;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private JFXButton button_1;

	@FXML
	private JFXButton button_2;
	@FXML
	private AnchorPane tablePane;

	@FXML
	private TableView<Place> tableView;

	@FXML
	private TableColumn<Place, String> placeColumn;

	@FXML
	private TableColumn<Place, String> adjPlaceColumn;

	@FXML
	private JFXButton back;

	@FXML
	private AnchorPane pane_1;
	@FXML
	private AnchorPane pane_2;
	@FXML
	private AnchorPane pane_3;
	@FXML
	private AnchorPane pane_4;

	@FXML
	private Text placeName;

	@FXML
	private TextArea placeIntroduction;

	@FXML
	private TextArea textPath;
	@FXML
	private TextArea textPath1;
	@FXML
	private TextArea textPath2;

	@FXML
	private Text popularity;

	@FXML
	private Text hasRestArea;

	@FXML
	private Text hasToilet;

	@FXML
	private JFXTextField search_content;

	@FXML
	private JFXButton search_butn;

	@FXML
	private JFXButton butn_1;

	@FXML
	private TextField text_1;

	@FXML
	private TextField text_2;
	@FXML
	private TextField text_3;
	@FXML
	private TextField text_4;

	@FXML
	private Text text_5;
	@FXML
	private Text text_6;
	@FXML
	private Text text_7;
	@FXML
	private Text text_8;
	@FXML
	private TextField text_announcement;

	@FXML
	private Label label_car;
	@FXML
	private Label label_time;
	@FXML
	private Label label_nowTime;
	@FXML
	private Label label_fee;
	@FXML
	private Label label_allTime;
	@FXML
	private JFXButton butn_2;
	@FXML
	private JFXButton butn_3;
	@FXML
	private JFXButton butn_4;

	@FXML
	private JFXButton button_3;

	@FXML
	private JFXButton button_4;

	@FXML
	private JFXRadioButton select_1;

	@FXML
	private JFXRadioButton select_2;
	@FXML
	private AnchorPane pane_butn;

	@FXML
	private JFXComboBox<Label> combobox = new JFXComboBox<>();

	private HamburgerBasicCloseTransition burgerTask;
	private MainApp mainApp;

	@FXML
	void backToMain(MouseEvent event) {
		mainApp.backMain();
	}

	@FXML
	void clearn(MouseEvent event) {
		label_car.setVisible(false);
		label_fee.setVisible(false);
		label_time.setVisible(false);
		label_allTime.setVisible(false);
		text_4.setText(null);
		text_5.setText(null);
		text_6.setText(null);
		text_7.setText(null);
		text_8.setText(null);
	}

	@FXML
	void cleanSearch(MouseEvent event) {
		search_content.setText(null);
		tableView.setItems(mainApp.getPlaceData());
	}

	void searchPlace(TextField t) {
		String str = t.getText();
		if(str==null||str.equals("")) {
			return;
		}
		ObservableList<Place> tableData = FXCollections.observableArrayList();
		for (Place place : mainApp.getPlaceData()) {
			if (place.getName().contains(str) || place.getIntroduction().contains(str)) {
				if (!tableData.contains(place)) {
					tableData.add(place);
				}
			}
		}
		tableView.setItems(tableData);
		pane_1.setVisible(true);
	}

	@FXML
	void searchPlace_1(ActionEvent event) {
		searchPlace(search_content);
	}

	@FXML
	void menu_1(ActionEvent event) {
		mainPane_1.setVisible(true);
		pane_1.setVisible(true);
		mainPane_2.setVisible(true);
		pane_2.setVisible(false);
		pane_3.setVisible(false);
		pane_4.setVisible(false);
		menuPane.setVisible(false);
		button_1.setDisable(true);
		button_2.setDisable(false);
		button_3.setDisable(false);
		button_4.setDisable(false);
		burgerTask.setRate(burgerTask.getRate() * -1);
	}

	@FXML
	void menu_2(ActionEvent event) {
		mainPane_1.setVisible(true);
		mainPane_2.setVisible(false);
		pane_2.setVisible(true);
		pane_3.setVisible(false);
		pane_4.setVisible(false);
		menuPane.setVisible(false);
		button_1.setDisable(false);
		button_2.setDisable(true);
		button_3.setDisable(false);
		button_4.setDisable(false);
		burgerTask.setRate(burgerTask.getRate() * -1);
	}

	@FXML
	void menu_3(ActionEvent event) {
		mainPane_1.setVisible(true);
		mainPane_2.setVisible(false);
		pane_2.setVisible(false);
		pane_3.setVisible(true);
		pane_4.setVisible(false);
		menuPane.setVisible(false);
		button_1.setDisable(false);
		button_2.setDisable(false);
		button_3.setDisable(true);
		button_4.setDisable(false);
		burgerTask.setRate(burgerTask.getRate() * -1);
	}

	@FXML
	void menu_4(ActionEvent event) {
		mainPane_1.setVisible(true);
		mainPane_2.setVisible(false);
		pane_2.setVisible(false);
		pane_3.setVisible(false);
		pane_4.setVisible(true);
		menuPane.setVisible(false);
		button_1.setDisable(false);
		button_2.setDisable(false);
		button_3.setDisable(false);
		button_4.setDisable(true);
		burgerTask.setRate(burgerTask.getRate() * -1);
	}

	@FXML
	void select_1(ActionEvent event) {
		select_1.setSelected(true);
		select_2.setSelected(false);
	}

	@FXML
	void select_2(ActionEvent event) {
		select_1.setSelected(false);
		select_2.setSelected(true);
	}

	@FXML
	void setPressedColor(MouseEvent event) {
		pane_butn.setStyle("-fx-background-color:#dfdfdf");
	}

	@FXML
	void setReleasedColor(MouseEvent event) {
		pane_butn.setStyle("-fx-background-color: #a4a4a4");
	}

	@FXML
	void initialize() {
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			int second = LocalDateTime.now().getSecond();
			int minute = LocalDateTime.now().getMinute();
			int hour = LocalDateTime.now().getHour();
			label_nowTime.setText(hour + ":" + (minute) + ":" + second);
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		text_announcement.setText(DataUtil.getData().getAnnouncement());// 设置公告

		combobox.getItems().add(new Label("名称"));
		combobox.getItems().add(new Label("热度"));
		combobox.getItems().add(new Label("线路数"));

		placeColumn.setReorderable(false);
		adjPlaceColumn.setReorderable(false);
		burgerTask = new HamburgerBasicCloseTransition(hamburger);// 菜单按钮
		burgerTask.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
//			burgerTask.play(); 不需要改变图标
			if (burgerTask.getRate() > 0) {// 打开了菜单
				menuPane.setVisible(true);
			} else {// 关闭了菜单
				menuPane.setVisible(false);
			}
		});
		assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'GuestMenu.fxml'.";
		placeColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		adjPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().adjListProperty());
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPlaceDetails(newValue));
	}

	// 将景点信息显示在面板上
	private void showPlaceDetails(Place p) {
		if (p != null) {
			placeName.setText(p.getName());
			placeIntroduction.setText(p.getIntroduction());
			if (p.isHasToilet()) {
				hasToilet.setText("有");
			} else {
				hasToilet.setText("无");
			}
			if (p.isHasRest_area()) {
				hasRestArea.setText("有");
			} else {
				hasRestArea.setText("无");
			}
			String s = "";
			for (int i = 0; i < p.getPopularity(); i++) {
				s += "★";
			}
			popularity.setText(s);
		} else {
			placeName.setText("");
			placeIntroduction.setText("");
			hasToilet.setText("无");
			hasRestArea.setText("无");
			popularity.setText("");
		}
	}

	@FXML
	void setText_1(ActionEvent event) {
		Place p = tableView.getSelectionModel().getSelectedItem();
		if (p == null) {
			return;
		}
		text_1.setText(p.getName());
	}

	@FXML
	void setText_2(ActionEvent event) {
		Place p = tableView.getSelectionModel().getSelectedItem();
		if (p == null) {
			return;
		}
		text_2.setText(p.getName());
		if (!text_1.getText().equals("") && !text_2.getText().equals("")
				&& !text_1.getText().equals(text_2.getText())) {
			ArrayList<Place> a = DataUtil.getData().shortestPath(text_1.getText(), text_2.getText());// 计算最短路径
			String str = "最短路径： \n";
			for (int i = a.size() - 1; i > 0; i--) {
				str += a.get(i).getName() + "→" + a.get(i - 1).getName() + "\n";
			}
			str += "最短距离：" + a.get(0).getTotal_distance();
			textPath.setText(str);
			textPath.setVisible(true);
		} else {
			JFXDialogLayout layout = new JFXDialogLayout();
			Label l = null;
			if (text_1.getText().equals(text_2.getText())) {
				l = new Label("不能查找相同景点！");

			}
			if (text_1.getText().equals("") || text_2.getText().equals("")) {

				l = new Label("请在选择景点操作！");
			}
			l.setTextFill(Color.rgb(19, 115, 241));
			layout.setBody(l);
			layout.setPrefWidth(250);
			JFXAlert<Void> alert = new JFXAlert<>(mainApp.getMainStage());
			alert.setOverlayClose(true);
			alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);// 提示在窗口的中央
			alert.setContent(layout);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();

		}

	}

	@FXML
	void setText_3(ActionEvent event) {// 导游路线图
		Place p = tableView.getSelectionModel().getSelectedItem();
		if (p == null) {
			return;
		}
		text_3.setText(p.getName());
		String str = "导游线路图：\n";
		int d = 0;
		ArrayList<Place> a = DataUtil.getData().traceablePath(text_3.getText());
		for (int i = 0; i < a.size() - 1; i++) {
			str += a.get(i).getName() + "→" + a.get(i + 1).getName() + "\n";
			AdjPlace ap = a.get(i).getAdjList().next();
			while (ap != null) {
				if (ap.getDestination().equals(a.get(i + 1).getName())) {
					d += ap.getDistance();
					break;
				}
				ap = ap.next();
			}
		}
		str += "距离：" + d;
		textPath1.setText(str);
	}

	@FXML
	void setText_4(ActionEvent event) {// 获取车牌号 停车 or 取车
		String str = text_4.getText();
		if (!str.equals("") && (select_1.isSelected() || select_2.isSelected())) {
			if (select_1.isSelected()) {// 停车
				boolean flag = true;
				if (DataUtil.getData().getCar(str) == null) {
					flag = DataUtil.getData().entryParking(str);
				}
				if (flag) {// 成功停车
					label_car.setVisible(true);
					label_time.setVisible(true);
					Car m = DataUtil.getData().getCar(str);
					text_5.setText(String.valueOf(m.getIndex()) + "号车道");
					text_6.setText(DataUtil.getData().timeFormat().format(m.getArr_time()));
				} else {
					text_5.setText("停车场已满，便道停放不计费");
				}
			} else {// 取车
				Car c = DataUtil.getData().outParking(str);
				if (c == null) {
					text_5.setText("未查询到相关车辆");
					return;
				}
				label_car.setVisible(true);
				label_fee.setVisible(true);
				label_time.setVisible(true);
				label_allTime.setVisible(true);
				text_5.setText(String.valueOf(c.getIndex()) + "号车道");
				text_6.setText(DataUtil.getData().timeFormat().format(c.getArr_time()));
				text_7.setText(String.valueOf(c.getSpend()) + "元");
				text_8.setText(String.valueOf(c.getTime()) + "小时");
			}
		} else {
			Label l = null;
			if (!select_1.isSelected() && !select_2.isSelected()) {
				l = new Label("请选择方式后操作");
			}
			if (str.equals("")) {
				l = new Label("请输入车牌号后操作");
			}
			JFXDialogLayout layout = new JFXDialogLayout();
			l.setTextFill(Color.rgb(19, 115, 241));
			layout.setBody(l);
			layout.setPrefWidth(250);
			JFXAlert<Void> alert = new JFXAlert<>(mainApp.getMainStage());
			alert.setOverlayClose(true);
			alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);// 提示在窗口的中央
			alert.setContent(layout);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}
	}

	public void tip(Node n) {// 弹窗消息
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setBody(n);
		layout.setPrefWidth(250);
		JFXAlert<Void> alert = new JFXAlert<>(mainApp.getMainStage());
		alert.setOverlayClose(true);
		alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);// 提示在窗口的中央
		alert.setContent(layout);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
	}

	@FXML
	void sort(ActionEvent event) {
		if (combobox.getSelectionModel().getSelectedItem() == null) {
			Label l = new Label("请选择一种方式后排序");
			tip(l);
		} else {
			int i = combobox.getSelectionModel().getSelectedIndex();
			if (i == 0) {// 按照名称排
				mainApp.getPlaceArray().sort(new Comparator<Place>() {
					@Override
					public int compare(Place p1, Place p2) {
						return Collator.getInstance(Locale.CHINESE).compare(p1.getName(), p2.getName());
					}
				});
				mainApp.setPlaceData(FXCollections.observableArrayList(mainApp.getPlaceArray()));
				tableView.setItems(mainApp.getPlaceData());
			} else if (i == 1) {// 按照热度排序
				mainApp.getPlaceArray().sort(new Comparator<Place>() {
					@Override
					public int compare(Place p1, Place p2) {
						if (p1.getPopularity() < p2.getPopularity()) {
							return 1;
						} else if (p1.getPopularity() > p2.getPopularity()) {
							return -1;
						} else {
							return 0;
						}
					}
				});
				mainApp.setPlaceData(FXCollections.observableArrayList(mainApp.getPlaceArray()));
				tableView.setItems(mainApp.getPlaceData());
			} else {// 按照周围邻接点数排
				mainApp.getPlaceArray().sort(new Comparator<Place>() {
					@Override
					public int compare(Place p1, Place p2) {
						int i = 0;
						int j = 0;
						AdjPlace ap1 = p1.getAdjList().next();
						AdjPlace ap2 = p2.getAdjList().next();
						while (ap1 != null) {
							i++;
							ap1 = ap1.next();
						}
						while (ap2 != null) {
							j++;
							ap2 = ap2.next();
						}
						return j - i;
					}
				});
				mainApp.setPlaceData(FXCollections.observableArrayList(mainApp.getPlaceArray()));
				tableView.setItems(mainApp.getPlaceData());
			}
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tableView.setItems(mainApp.getPlaceData());
	}
}
