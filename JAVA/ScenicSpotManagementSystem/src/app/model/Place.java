package app.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 景区类（点）property是界面表格需要用的，所以全是设为这种
 */

public class Place {
	private final StringProperty name;// 景点名称
	private final StringProperty introduction;// 简介
	private final IntegerProperty popularity;// 人气
	private final BooleanProperty hasRest_area;// 有无休息区
	private final BooleanProperty hasToilet;// 有无厕所
	private final ObjectProperty<AdjPlace> adjList;// 存邻接景点的头节点

	private boolean visited;// 是否被访问
	private int total_distance;// 总距离
	private Place from_place;// 从上一个景区来

	public Place(String name) {
		this.name = new SimpleStringProperty(name);
		this.introduction = new SimpleStringProperty(null);
		this.popularity = new SimpleIntegerProperty(-1);
		this.hasRest_area = new SimpleBooleanProperty(false);
		this.hasToilet = new SimpleBooleanProperty(false);
		this.adjList = new SimpleObjectProperty<AdjPlace>(new AdjPlace());
	}

	public Place(String name, AdjPlace adjList) {
		super();
		this.name = new SimpleStringProperty(name);
		this.introduction = new SimpleStringProperty(null);
		this.popularity = new SimpleIntegerProperty(-1);
		this.hasRest_area = new SimpleBooleanProperty(false);
		this.hasToilet = new SimpleBooleanProperty(false);
		this.adjList = new SimpleObjectProperty<AdjPlace>(new AdjPlace());
		this.adjList.get().setNext(adjList);
	}

	public Place(String name, String introduction, int popularity, boolean restAera, boolean toilet) {
		this.name = new SimpleStringProperty(name);
		this.introduction = new SimpleStringProperty(introduction);
		this.popularity = new SimpleIntegerProperty(popularity);
		this.hasRest_area = new SimpleBooleanProperty(restAera);
		this.hasToilet = new SimpleBooleanProperty(toilet);
		this.adjList = new SimpleObjectProperty<AdjPlace>(new AdjPlace());
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getIntroduction() {
		return introduction.get();
	}

	public void setIntroduction(String introduction) {
		this.introduction.set(introduction);
	}

	public StringProperty introductionProperty() {
		return this.introduction;
	}

	public int getPopularity() {
		return popularity.get();
	}

	public void setPopularity(int popularity) {
		this.popularity.set(popularity);
	}

	public IntegerProperty popularityProperty() {
		return this.popularity;
	}

	public boolean isHasRest_area() {
		return hasRest_area.get();
	}

	public void setHasRest_area(boolean hasRest_area) {
		this.hasRest_area.set(hasRest_area);
	}

	public BooleanProperty hasRestAreaProperty() {
		return this.hasRest_area;
	}

	public boolean isHasToilet() {
		return hasToilet.get();
	}

	public void setHasToilet(boolean hasToilet) {
		this.hasToilet.set(hasToilet);
	}

	public BooleanProperty hasToiletProperty() {
		return this.hasToilet;
	}

	public AdjPlace getAdjList() {
		return adjList.get();
	}

	public void setAdjList(AdjPlace adjList) {
		this.adjList.set(adjList);
		;
	}

	public StringProperty adjListProperty() {
		String str = "";
		AdjPlace current = getAdjList().next();
		while (current != null) {
			str += "  " + current.getDestination();
			current = current.next();
		}
		if (!str.equals("")) {
			str = str.substring(2);
		}
		return new SimpleStringProperty(str);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getTotal_distance() {
		return total_distance;
	}

	public void setTotal_distance(int total_distance) {
		this.total_distance = total_distance;
	}

	public Place getFrom_place() {
		return from_place;
	}

	public void setFrom_place(Place from_place) {
		this.from_place = from_place;
	}

	@Override
	public String toString() {
		return name.get() + "_" + introduction.get() + "_" + popularity.get() + "_" + hasRest_area.get() + "_"
				+ hasToilet.get();
	}

}
