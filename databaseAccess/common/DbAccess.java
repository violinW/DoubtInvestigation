package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.TargetDBConfig;

/**
 * 数据库链接解耦
 * 
 * 1、连接数据库方法（connectDB）
 * 公有方法  需要权限
 */
public class DbAccess {
	public Connection conn;
	
	/**
	 * 使用默认域名和端口号访问数据库
	 * @param dbName 数据库名
	 * @param user MySQL配置时的用户名
	 * @param password MySQL配置时的密码
	 * 
	 * 用法举例：
	 * DbAccess acc = new DbAccess();
	 * acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
	 * acc.conn.createStatement()
	 * 
	 * @return
	 */
	public void connectDBDefault(String dbName) {
		
		TargetDBConfig conf = new TargetDBConfig();
		
		this.connectDB(conf.host, conf.port, dbName, conf.user, conf.password);
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
	public void connectDB(String host, String port, String dbName, String user, String password) {
		
		System.out.println("host: " + host + ", port " + port + ", adName: " + dbName + ", user: " + user + ", password: " + password);
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名DoubtInvestigation
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

		try {
			// 加载驱动程序
			Class.forName(driver);

			// 连续数据库
			conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
	}
}
