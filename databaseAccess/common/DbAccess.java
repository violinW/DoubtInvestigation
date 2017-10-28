package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.TargetDBConfig;

/**
 * 数据库链接解耦
 * 
 * 1、连接数据库方法（connectDB）
 * 公有方法  需要权限
 */
public class DbAccess {
	
	
	public DbAccess() {
		//System.out.println("请传入数据库名，表明，用户名，密码等信息");
	}
	public DbAccess(String dbName) {
		this.connectDBDefault(dbName);
	}
	public DbAccess(String host, String port, String dbName, String user, String password) {
		this.connectDB(host, port, dbName, user, password);
	}
	
	
	/**
	 * 使用默认域名和端口号访问数据库
	 * @param dbName 数据库名
	 * @param user MySQL配置时的用户名
	 * @param password MySQL配置时的密码
	 * @return
	 */
	public Connection connectDBDefault(String dbName) {
		
		TargetDBConfig conf = new TargetDBConfig();
		
		return this.connectDB(conf.host, conf.port, dbName, conf.user, conf.password);
	}
	
	/**
	 * 使用指定域名和端口号访问数据库
	 * @param host 服务器名
	 * @param port 端口
	 * @param dbName 数据库名
	 * @param user MySQL配置时的用户名
	 * @param password MySQL配置时的密码
	 * @return
	 */
	public Connection connectDB(String host, String port, String dbName, String user, String password) {
		
		System.out.println("host: " + host + ", port " + port + ", adName: " + dbName + ", user: " + user + ", password: " + password);
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名DoubtInvestigation
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

		try {
			// 加载驱动程序
			Class.forName(driver);

			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			
			return conn;

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return null;
		
	}
	
	public void closeConnection(Connection conn) throws SQLException {
		((Connection)conn).close();
	}
}
