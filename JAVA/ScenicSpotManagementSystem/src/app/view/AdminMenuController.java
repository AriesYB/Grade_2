package app.view;

import app.MainApp;
import app.model.AdjPlace;
import app.model.Place;
import app.util.DataUtil;

/**
 * Sample Skeleton for 'AdminMenu.fxml' Controller Class
 */

import java.net.URL;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public class AdminMenuController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private TextField text;
	@FXML // fx:id="modify_butn"
	private Button modify_butn; // Value injected by FXMLLoader
	@FXML
	private JFXButton button_1;
	@FXML
	private TableView<Place> tableView;
	@FXML
	private TableView<Place> tableView_1;
	@FXML
	private TableView<Place> tableView_2;
	@FXML
	private TableView<Place> tableView_3;

	@FXML
	private TableColumn<Place, String> placeColumn;
	@FXML
	private TableColumn<Place, String> placeColumn_1;
	@FXML
	private TableColumn<Place, String> placeColumn_2;
	@FXML
	private TableColumn<Place, String> placeColumn_3;

	@FXML
	private TableColumn<Place, String> adjPlaceColumn;
	@FXML
	private JFXButton button_2;
	@FXML
	private JFXButton button_3;
	@FXML
	private AnchorPane menuPane;
	@FXML
	private AnchorPane Pane_1;

	@FXML
	private AnchorPane Pane_2;
	@FXML
	private AnchorPane Pane_3;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private TextArea announcement;

	@FXML
	private JFXButton change_butn2;

	@FXML
	private JFXButton change_butn1;
	@FXML
	private Line line_1;

	@FXML
	private Line line_2;

	@FXML
	private Line line_3;
	@FXML
	private Text placeName;
	@FXML
	private Text popularity;
	@FXML
	private Text hasRestArea;
	@FXML
	private Text hasToilet;
	@FXML
	private TextArea placeIntroduction;
	@FXML
	private JFXComboBox<Label> combobox = new JFXComboBox<>();
	@FXML
	private JFXComboBox<Label> combobox_1 = new JFXComboBox<>();
	@FXML
	private AnchorPane pane_butn;
	private JFXTextField t;
	private ObservableList<Place> adjPlaceData = FXCollections.observableArrayList();// table数据集合(邻接景点);//邻接景点信息
	private ObservableList<Place> selectPlaceData = FXCollections.observableArrayList();// table数据集合(可选择的景点);
	private MainApp mainApp;
	private HamburgerBasicCloseTransition burgerTask;

	@FXML
	void initialize() {
		combobox.getItems().add(new Label("名称"));
		combobox.getItems().add(new Label("热度"));
		combobox.getItems().add(new Label("线路数"));
		combobox_1.getItems().add(new Label("名称"));
		combobox_1.getItems().add(new Label("热度"));
		combobox_1.getItems().add(new Label("线路数"));
		announcement.setText(DataUtil.getData().getAnnouncement());
		burgerTask = new HamburgerBasicCloseTransition(hamburger);
		burgerTask.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
//			burgerTask.play();
			if (burgerTask.getRate() > 0) {// 打开了菜单
				menuPane.setVisible(true);
			} else {// 关闭了菜单
				menuPane.setVisible(false);
			}
		});

		placeColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());// 给景点列添加数据
		adjPlaceColumn.setCellValueFactory(cellData -> cellData.getValue().adjListProperty());// 给领接表列添加数据
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPlaceDetails(newValue));// 给table添加监听器，显示选中的detail

		placeColumn_1.setCellValueFactory(cellData -> cellData.getValue().nameProperty());// 景点的数据
		tableView_1.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showAdjPlace(newValue));
		placeColumn_2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());// 邻接表的数据
		placeColumn_3.setCellValueFactory(cellData -> cellData.getValue().nameProperty());// 可选择的景点数据
	}

	// 显示某一景点的邻接景点
	private void showAdjPlace(Place p) {
		if (p != null) {
			adjPlaceData.clear();
			selectPlaceData.clear();
			AdjPlace ap = p.getAdjList().next();
			while (ap != null) {
				adjPlaceData.add(DataUtil.getData().getPlace(ap.getDestination()));
				ap = ap.next();
			}
			for (Place place : mainApp.getPlaceData()) {
				if (adjPlaceData.contains(place) || place == p) {// 如果已经是邻接点或者是它本身就跳过
					continue;
				}
				selectPlaceData.add(place);// 否则添加
			}
			tableView_2.setItems(adjPlaceData);// 给邻接景点载入数据
			tableView_3.setItems(selectPlaceData);
		}
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
	void cleanSearch(MouseEvent event) {
		text.setText(null);
		tableView.setItems(mainApp.getPlaceData());
	}
	@FXML
	void search(ActionEvent event) {
		String str = text.getText();
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
	}

	@FXML
	void add(ActionEvent event) {
		Place p = new Place(null);
		boolean okClicked = mainApp.showEditDialog(p);
		if (okClicked) {
			mainApp.getPlaceData().add(p);
		}
		updateDetailFile();
	}

	@FXML
	void addAdj(ActionEvent event) {
		Place p = tableView_3.getSelectionModel().getSelectedItem();
		if (p == null) {
			Label l = new Label("请选择一个景点后再操作！");
			l.setTextFill(Color.rgb(19, 115, 241));
			tip(l);
		} else if (adjPlaceData.contains(p)) {
			Label l = new Label("不能添加已有路线，请重试！");
			l.setTextFill(Color.rgb(19, 115, 241));
			tip(l);
		} else {
			t = new JFXTextField();
			t.setPromptText("请输入路线距离");
			tip(t);
			if (t.getText().equals("")) {
				return;
			}
			adjPlaceData.add(p);
			selectPlaceData.remove(p);
			updateAdjPlace(p, true);
		}
	}

	@FXML
	void deleteAdj(ActionEvent event) {
		Place p = tableView_2.getSelectionModel().getSelectedItem();
		if (p == null) {
			Label l = new Label("请选择一个景点后再操作！");
			l.setTextFill(Color.rgb(19, 115, 241));
			tip(l);
		} else {
			selectPlaceData.add(p);
			adjPlaceData.remove(p);
			updateAdjPlace(selectPlaceData.get(selectPlaceData.size() - 1), false);
		}
	}

	@FXML
	void delete(ActionEvent event) {
		Place p = tableView.getSelectionModel().getSelectedItem();// 获取选中行对象
		if (p == null) {
			Label l = new Label("请选择一个景点后再操作！");
			l.setTextFill(Color.rgb(19, 115, 241));
			tip(l);
		} else {
			Place p1 = tableView.getSelectionModel().getSelectedItem();
			mainApp.getPlaceData().remove(tableView.getSelectionModel().getSelectedIndex());// 删除选中行
			AdjPlace ap = p1.getAdjList().next();
			while (ap != null) {
				for (int i = 0; i < mainApp.getPlaceData().size(); i++) {
					Place p2 = mainApp.getPlaceData().get(i);
					if (p2.getName().equals(ap.getDestination())) {
						AdjPlace ap1 = p2.getAdjList();
						while (ap1.next() != null) {
							if (ap1.next().getDestination().equals(p1.getName())) {
								ap1.setNext(ap1.next().next());
								i = mainApp.getPlaceData().size() - 1;
								break;
							}
							ap1 = ap1.next();
						}
					}
				}
				ap = ap.next();
			}
			// 删除该景点的边及包含该景点的边
		}
		updateInfoFile();
		updateDetailFile();
	}

	@FXML
	void edit(ActionEvent event) {
		Place p = tableView.getSelectionModel().getSelectedItem();// 获取选择行对象
		if (p == null) {
			Label l = new Label("请选择一个景点后再操作！");
			l.setTextFill(Color.rgb(19, 115, 241));
			tip(l);
		} else {
			boolean okClicked = mainApp.showEditDialog(p);
			if (okClicked) {
				showPlaceDetails(p);
			}
		}
		updateDetailFile();
	}

	@FXML
	void menu_1(ActionEvent event) {
		button_1.setDisable(true);
		button_2.setDisable(false);
		button_3.setDisable(false);
		Pane_1.setVisible(true);
		Pane_2.setVisible(false);
		Pane_3.setVisible(false);
		burgerTask.setRate(-1);
		menuPane.setVisible(false);
	}

	@FXML
	void menu_2(ActionEvent event) {
		button_2.setDisable(true);
		button_1.setDisable(false);
		button_3.setDisable(false);
		Pane_1.setVisible(false);
		Pane_2.setVisible(true);
		Pane_3.setVisible(false);
		burgerTask.setRate(-1);
		menuPane.setVisible(false);
	}

	@FXML
	void menu_3(ActionEvent event) {// 邻接景点管理页

		button_3.setDisable(true);
		button_1.setDisable(false);
		button_2.setDisable(false);
		Pane_1.setVisible(false);
		Pane_2.setVisible(false);
		Pane_3.setVisible(true);
		burgerTask.setRate(-1);
		menuPane.setVisible(false);
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
	void changeText(ActionEvent event) {// 发布公告
		announcement.setEditable(false);
		change_butn2.setDisable(true);
		DataUtil.getData().setAnnouncement(announcement.getText());
	}

	@FXML
	void editText(ActionEvent event) {
		announcement.setEditable(true);
		change_butn2.setDisable(false);
	}

	@FXML
	void backToMain(MouseEvent event) {
		mainApp.backCheck();
	}

	@FXML
	void sort(ActionEvent event) {
		sort(tableView, combobox);
	}

	@FXML
	void sort_1(ActionEvent event) {
		sort(tableView_1, combobox_1);
	}

	private void sort(TableView<Place> table, JFXComboBox<Label> c) {
		if (c.getSelectionModel().getSelectedItem() == null) {
			Label l = new Label("请选择一种方式后排序");
			tip(l);
		} else {
			int i = c.getSelectionModel().getSelectedIndex();
			if (i == 0) {// 按照名称排
				mainApp.getPlaceArray().sort(new Comparator<Place>() {
					@Override
					public int compare(Place p1, Place p2) {
						return Collator.getInstance(Locale.CHINESE).compare(p1.getName(), p2.getName());
					}
				});
				mainApp.setPlaceData(FXCollections.observableArrayList(mainApp.getPlaceArray()));
				table.setItems(mainApp.getPlaceData());
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
				table.setItems(mainApp.getPlaceData());
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
				table.setItems(mainApp.getPlaceData());
			}
		}
	}

	private void updateDetailFile() {// 将景点数据写入文件
		DataUtil.getData().writeDetailFile(mainApp.getPlaceData());
	}

	private void updateInfoFile() {// 将路径数据写入文件
		DataUtil.getData().writeInfoFile(mainApp.getPlaceData());
	}

	private void updateAdjPlace(Place p, boolean flag) {// 传入对象 添加为true 删除为false
		if (flag) {// 说明是添加
			Place place = tableView_1.getSelectionModel().getSelectedItem();// 找到添加新景点的景点
			AdjPlace ap = place.getAdjList();
			while (ap.next() != null) {
				ap = ap.next();
			}
			ap.setNext(new AdjPlace(p.getName(), Integer.parseInt(t.getText())));
			AdjPlace ap1 = p.getAdjList();
			while (ap1.next() != null) {
				ap1 = ap1.next();
			}
			ap1.setNext(new AdjPlace(place.getName(), Integer.parseInt(t.getText())));
			updateInfoFile();
		} else {// 是删除操作
			Place place = tableView_1.getSelectionModel().getSelectedItem();// 找到操作的原始景点
			AdjPlace ap = place.getAdjList();
			while (ap.next() != null) {
				if (ap.next().getDestination().equals(p.getName())) {// 删除邻接点
					ap.setNext(ap.next().next());
					break;
				}
				ap = ap.next();
			}
			AdjPlace ap0 = p.getAdjList();
			while (ap0.next() != null) {
				if (ap0.next().getDestination().equals(place.getName())) {
					ap0.setNext(ap0.next().next());
					break;
				}
				ap0 = ap0.next();
			}
			updateInfoFile();
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

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tableView.setItems(mainApp.getPlaceData());// 给table传入数据
		tableView_1.setItems(mainApp.getPlaceData());
	}

}
