package MissData;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import common.DbAccess;

/**
 * 链接到某数据库获取某表的数据
 * @author romens 返回 ResultSet 类型
 *
 */
public class FieldIncoherentAccess {

	public FieldIncoherentAccess() {
		System.out.println("请传入数据库名，表明，用户名，密码等信息");
	}
	
	public FieldIncoherentAccess(String dbName, String tableName, String fieldName) {
		this.excuteQuery(dbName, tableName, fieldName);
	}
	
	public ResultSet excuteQuery(String dbName, String tableName, String fieldName) {
		DbAccess myAccess = new DbAccess(dbName);
		
		String sql = "select * from " + tableName + " limit 2";

		try {
			// 结果集
			ResultSet rs = ((Statement)myAccess).executeQuery(sql);
			
			return rs;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}
}
