package app.model;

public class User {
	private String name;
	private int id;

	public User(int id) {
		super();
		this.id = id;
	}

	public User(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
