package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Database {
	private String url = ResourceBundle.getBundle("config").getString("url");// 获取config.properties的信息
	private String name = ResourceBundle.getBundle("config").getString("name");
	private String username = ResourceBundle.getBundle("config").getString("username");
	private String password = ResourceBundle.getBundle("config").getString("password");
	private Connection conn;
	private Statement stat;
	private ResultSet rs;
	private static Database db;// 一个自身的静态对象

	// 获取该对象
	public static Database getDatabase() {
		if (db == null) {
			db = new Database();
		}
		return db;
	}

	// 建立连接
	public void getConn() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 释放资源
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//需要写增删查改的方法
	
}
