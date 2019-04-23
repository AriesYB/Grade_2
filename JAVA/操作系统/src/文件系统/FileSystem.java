package 文件系统;

import java.util.ArrayList;
import java.util.HashMap;

public class FileSystem {
	HashMap<String,ArrayList<File>> MFD = new HashMap<String,ArrayList<File>>(10);//主目录

	public FileSystem() {
		ArrayList<File> arr = new ArrayList<File>(10);
		arr.add(new File("文件1", new int[] {0,0,0}, 10));
		arr.add(new File("文件2", new int[] {0,0,0}, 9));
		arr.add(new File("文件3", new int[] {0,0,0}, 18));
		arr.add(new File("文件4", new int[] {0,0,0}, 7));
		arr.add(new File("文件5", new int[] {0,0,0}, 6));
		arr.add(new File("文件6", new int[] {0,0,0}, 5));
		arr.add(new File("文件7", new int[] {0,0,0}, 5));
		arr.add(new File("文件8", new int[] {0,0,0}, 5));
		arr.add(new File("文件9", new int[] {0,0,0}, 5));
		MFD.put("文件夹1", arr);
		MFD.put("文件夹2", arr);
	}
}
