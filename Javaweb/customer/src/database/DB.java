package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customer.Account;
import customer.Customer;

public class DB { 

	private String url = "jdbc:mysql://127.0.0.1:3307/customer_web?useSSL=false";
	private String name = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "476900";
	
	private ArrayList<Customer> list = new ArrayList<Customer> ();

	private Connection conn=null;
	private Statement stat=null;	
	private ResultSet rs = null;
	
	
	public void init() {	//初始化
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
        }
	}
	
	public void close()	//关闭资源
	{
		try {
			if (rs!=null) {
				rs.close();
			}
			if (stat!=null) {
				stat.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean check(int account,String password)	//检测账号密码
	{
		String sql = "SELECT password FROM account_info WHERE id="+account;//SQL语句
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs!=null)
			{
				while(rs.next() && rs.getString("password").equals(password))
				{	
					return true;
				}
				System.out.println("无该账号");
				return false;
			}else
			{
				System.out.println("数据库无数据!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<?> getResultAll()	//获取所有数据
	{
		String sql="SELECT * FROM user_info";//id,name,sex,job,degree,address
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs!=null)
			{
				while(rs.next())//未解决获取结果集列数问题
				{ 		
					list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<?> getResult(int id)	//按照id查询
	{
		String sql="SELECT * FROM user_info WHERE id="+id;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs!=null)
			{
				while(rs.next())//未解决获取结果集列数问题
				{ 		
					list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<?> getResult(String name)	//按照姓名模糊查询
	{
		String str="";
		for (int i = 0; i < name.length(); i++) 
		{
			str+=name.charAt(i)+"%";
		}
		str=str.substring(0,str.length()-1);
		String sql="SELECT * FROM user_info WHERE name like '%"+str+"%'";
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs!=null)
			{
				while(rs.next())//未解决获取结果集列数问题
				{ 		
					list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void add(Customer cust)
	{
		String sql="INSERT INTO user_info VALUES("+"'"+cust.getId()+"'"+","+"'"+cust.getName()+"'"+","+"'"+cust.getSex()+"'"+","+"'"+cust.getJob()+"'"+","+"'"+cust.getDegree()+"'"+","+"'"+cust.getAddress()+"'"+")";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int id)
	{
		String sql="DELETE FROM user_info WHERE id="+id;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modify(Customer cust)
	{
		String sql="UPDATE user_info SET name='"+cust.getName()+"',sex='"+cust.getSex()+"',job='"+cust.getJob()+"',degree='"+cust.getDegree()+"',address='"+cust.getAddress()+"' WHERE id="+cust.getId();
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(Account at)
	{
		String sql="INSERT INTO account_info VALUES("+"'"+at.getId()+"'"+","+"'"+at.getPassword()+"'"+")";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
