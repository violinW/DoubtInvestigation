/**
 * 数据库接入
 */
package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 接入数据库Production表
 */
public class ProductionTableAccess {
	public static void main(String[] args) {

		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名DoubtInvestigation
		String url = "jdbc:mysql://localhost:3306/DoubtInvestigation";

		// MySQL配置时的用户名
		String user = "root";

		// MySQL配置时的密码
		String password = "violin";

		try {
			// 加载驱动程序
			Class.forName(driver);

			// 连续数据库
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句
			Statement statement = conn.createStatement();

			// 要执行的SQL语句
			String sql = "select * from slightBlacklist";

			// 结果集
			ResultSet rs = statement.executeQuery(sql);

			System.out.println("-----------------");
			System.out.println("执行结果如下所示:");
			System.out.println("-----------------");

			String ruleType = null;

			while (rs.next()) {

				// 选择sname这列数据
				ruleType = rs.getString("ruleType");

				// 输出结果
				System.out.println("规则类型\t" + ruleType);
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
