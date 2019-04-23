package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *	数据库操作类
 */

public class DBUtil {
	private static DBUtil db;	//静态对象
	private String url="jdbc:mysql://127.0.0.1:3307/good_web?useSSL=false";
	private String name="com.mysql.jdbc.Driver";
	private String user="root";
	private String password="476900";
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private ArrayList<HashMap<String,String>> list; 
	private HashMap<String,String> map;
 
	private DBUtil() {
		
	}
	//返回现有或者实例化一个DBUtil
	public static DBUtil getDBUtil() {	
		if (db == null) {
			db = new DBUtil();
		}
		return db;
	}
	
	//建立连接
	public void getConn() {
		try {
			Class.forName(name);
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}

	//释放资源
	public void close() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	//查询方法 传入查询语句
	public ArrayList<?> executeQuery(String sql) {
		list = new ArrayList<HashMap<String,String>>();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				map = new HashMap<String,String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {//遍历该行的每一列
					map.put(rs.getMetaData().getColumnName(i), rs.getString(i));//列名为键的Hashmap
				}
				list.add(map);//再放入集合
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//更新方法
	public int executeUpdate(String sql) {
		try {
			stat = conn.createStatement();
			int count =  stat.executeUpdate(sql);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
