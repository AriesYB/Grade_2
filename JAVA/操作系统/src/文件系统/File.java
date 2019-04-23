package 文件系统;

public class File {
	public String name;//文件名
	public int [] protect_code;//保护码
	public int length;//文件长度
	public boolean open;//是否打开
	
	public File(String name, int [] protect_code, int length) {
		super();
		this.name = name;
		this.protect_code = protect_code;
		this.length = length;
		this.open=false;
	}
}
