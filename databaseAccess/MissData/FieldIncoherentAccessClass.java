package MissData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import common.DbAccess;
import config.TargetDBConfig;
import defaultStructure.RuleType;

/**
 * 针对MissData规则类型下的FieldIncoherent规则
 * 链接到某数据库获取某表的数据
 * @author romens 返回 ResultSet 类型
 *
 */
public class FieldIncoherentAccessClass {
	TargetDBConfig tarConf = new TargetDBConfig();
	
	public List<Object> excuteQuery(String tableName, String fieldName) {
		return excuteQuery(tarConf.dbName, tableName, fieldName);
	}
	
	private List<Object> excuteQuery(String dbName, String tableName, String fieldName) {
		String sql = "select " + fieldName + " from " + tableName;

		try {
			DbAccess acc = new DbAccess();
			acc.connectDBDefault(dbName);
			Statement statement = (Statement) acc.conn.createStatement();
			
			System.out.println("sql: [" + sql + "]");
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			
			List<Object> result = new ArrayList<Object>();
			
			int i = 0;
			while (rs.next()) {
				result.add((Object)rs.getString(fieldName));
			}
			acc.closeConnection();

			System.out.println("access table " + tableName + " result:  resultLength  " + result.size() + "\n");
			
			System.out.println("| " + tableName + "            |\n");
			for(int j = 0; j < result.size(); j++) {
				System.out.println("| " + result.get(j) + "       |\n");
			}
			return result;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}
}
