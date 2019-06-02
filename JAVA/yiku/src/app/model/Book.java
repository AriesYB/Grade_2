package app.model;

public class Book {
	private String name;
	private String author;
	private boolean notes;
	private double price;
	private int quality;
	private String serial_number;

	public Book(String name, String author, double price, int quality, String serial_number) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.quality = quality;
		this.serial_number = serial_number;
		this.notes = false;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public boolean getNotes() {
		return notes;
	}

	public double getPrice() {
		return price;
	}

	public int getQuality() {
		return quality;
	}

	public String getSerial_number() {
		return serial_number;
	}

}
