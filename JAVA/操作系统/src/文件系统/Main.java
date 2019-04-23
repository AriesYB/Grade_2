package 文件系统;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		FileSystem FS = new FileSystem();
		Scanner scan = new Scanner(System.in);
		String str = "";
		while(true) {
			System.out.println("*******************");
			System.out.println("1.文件目录");
			System.out.println("2.退出");
			System.out.println("*******************");
			int cmd=scan.nextInt();
			switch (cmd) {
			case 1:
				System.out.println("*******************");
				System.out.println(" root");
				for (String s : FS.MFD.keySet()) {
					System.out.println(" -"+s);
				}
				System.out.println("1.返回上一层");
				System.out.println("*******************");
				str=scan.next();
				if(str.equals("1")) {
					break;
				}
				if(FS.MFD.containsKey(str)) {
					ArrayList<File> files = FS.MFD.get(str);
					System.out.println("*******************");
					System.out.println(" root");
					System.out.println(" -"+str);
					for (int i = 0; i < files.size(); i++) {
						System.out.println("--"+files.get(i).name);
					}
					System.out.println("1.返回主目录");
					System.out.println("*******************");
					String s=scan.next();
					if(str.equals("1")) {
						continue;
					}
					int i;
					for (i = 0; i < files.size(); i++) {
						if(files.get(i).name.equals(s)) {
							System.out.println("*******************");
							System.out.println(" root");
							System.out.println(" -"+str);
							System.out.println(" --"+s);
							System.out.println("1.新建文件");
							System.out.println("2.删除文件");
							System.out.println("3.打开文件");
							System.out.println("4.关闭文件");
							System.out.println("5.读取文件");
							System.out.println("6.写入文件");
							System.out.println("7.返回主目录");
							System.out.println("*******************");
							int d = scan.nextInt();
							switch (d) {
							case 1:
								if(files.size()>=10) {
									System.out.println("---------只允许创建10个文件");
								}else {
									System.out.println("请输入文件名");
									String e = scan.next();
									files.add(new File(e,new int[] {1,1,1}, 10));
								}
								i--;
								continue;
							case 2:
								System.out.println("---------现在已经删除文件"+files.get(i).name);
								files.remove(i);
								i--;
								continue;
							case 3:
								System.out.println("---------现在已经打开文件");
								files.get(i).protect_code[0]=1;
								files.get(i).protect_code[1]=1;
								files.get(i).open=true;
								i--;
								continue;
							case 4:
								if(files.get(i).open==false) {
									System.out.println("---------请先打开该文件");
								}else {
									System.out.println("---------现在已经关闭文件");
									files.get(i).protect_code[0]=0;
									files.get(i).open=false;
								}
								i--;
								continue;
							case 5:
								if(files.get(i).protect_code[0]==0) {
									System.out.println("---------无读取权限");
								}else {
									System.out.println(files.get(i).name+" "+files.get(i).protect_code[0]+files.get(i).protect_code[1]+files.get(i).protect_code[2]+files.get(i).length);
									System.out.println("---------现在能读取文件了");
								}
								i--;
								continue;
							case 6:
								if(files.get(i).protect_code[1]==0) {
									System.out.println("---------无写入权限");
								}else {
									System.out.println(files.get(i).name+" "+files.get(i).protect_code[0]+files.get(i).protect_code[1]+files.get(i).protect_code[2]+files.get(i).length);
									System.out.println("---------现在可以写入文件了");
								}
								i--;
								continue;
							case 7:
								break;
							}
							i=100;
						}
					}
					System.out.println("*******************");
				}else{
					System.out.println("无此用户文件");
					continue;
				}
				break;
			case 2:
				scan.close();
				System.exit(0);
			default:
				System.out.println("请重新选择！");
				continue;
			}
		}
	}
}
