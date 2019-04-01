package com.jtdx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.LifecycleListener;

import com.jtdx.bean.User;



public class jdbc {

	public void insertUser(String userName , String userPassword) throws Exception {
		//1.获取数据库连接的4个基本信息
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://39.104.123.155:3306/mail";
		String user = "root";
		String password = "wawa1314";
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "INSERT INTO user (NAME, PASSWORD) VALUES ('"+userName+"', '"+userPassword+"')";
		System.out.println(sql);
		
		statement.execute(sql);
		
		conn.close();
		
	}
	
	/**
	 * 
	 *根据用户查询
	 */
	public User selectUser(String userName) throws Exception {
		//1.获取数据库连接的4个基本信息
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://39.104.123.155:3306/mail";
		String user = "root";
		String password = "wawa1314";
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "select * from user where name = '"+userName+"'";
		System.out.println(sql);
		
		User u = new User();
		
		ResultSet rs = statement.executeQuery(sql);
		
		 while(rs.next()){
	         String name = rs.getString("name");
	         String userPassword = rs.getString("password");
	         
	         u.setName(name);
	         u.setPassword(userPassword);
	      }
		conn.close();
		return u;
	}
	
	/**
	 * 
	 * 查询所有
	 */
	public List<User> selectUserList() throws Exception {
		//1.获取数据库连接的4个基本信息
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://39.104.123.155:3306/mail";
		String user = "root";
		String password = "wawa1314";
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "select * from user";
		System.out.println("sql语句为："+sql);
		
		
		ResultSet rs = statement.executeQuery(sql);
		
		List<User> list = new ArrayList<>();
		while (rs.next()) {
			User u = new User();
			for (int i = 0; i < 2; i++) {
				if (rs.getString(1) != null) {
					String name = rs.getString(1);
					u.setName(name);
				} 
				
				if(rs.getString(2) != null){
					String pw = rs.getString(2);
					u.setPassword(pw);
				}
			}
			list.add(u);
		}
		
		
		conn.close();
		return list;
	}

    /**
     * 根据用户名删除
     * @throws ClassNotFoundException 
     *
     */
	public void deleteUser(String name) throws Exception {
		//1.获取数据库连接的4个基本信息
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://39.104.123.155:3306/mail";
		String user = "root";
		String password = "wawa1314";
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "delete from user where name = '"+name+"'";
		System.out.println(sql);
		
		statement.execute(sql);
		
		conn.close();
		
	}


	/**
	 *
	 *修改 
	 */
	public void updateUser(String name, String newName, String newPassword) throws Exception{
		//1.获取数据库连接的4个基本信息
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://39.104.123.155:3306/mail";
		String user = "root";
		String password = "wawa1314";
		
		//2.加载驱动
		Class.forName(driverClass);
		
		//3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		
		String sql = "update user set name='"+newName+"',password='"+newPassword+"' where name = '"+name+"'";
		System.out.println(sql);
		
		statement.execute(sql);
		
		conn.close();
		
	}

}
