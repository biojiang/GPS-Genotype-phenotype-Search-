package org.mdpa.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDriver
{
	public static final String url = "jdbc:mysql://172.17.200.101/MDPA?characterEncoding=utf8&useSSL=true";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "root";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBDriver(String sql)
	{
		try
		{
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			this.conn.close();
			this.pst.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}