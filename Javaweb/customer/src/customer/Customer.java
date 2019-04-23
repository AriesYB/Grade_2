package customer;

public class Customer {
	private int id;
	private String name;
	private String sex;
	private String job;
	private String degree;
	private String address;
	
	public Customer(int id, String name, String sex, String job, String degree, String address) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.job = job;
		this.degree = degree;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
