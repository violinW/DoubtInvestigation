package MissData;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public String[] excuteQuery(String tableName, String fieldName) {
		return excuteQuery(tarConf.dbName, tableName, fieldName);
	}
	
	private String[] excuteQuery(String dbName, String tableName, String fieldName) {
		String countSql = "select count(*) as count from " + tableName;
		String sql = "select " + fieldName + " from " + tableName;

		try {
			DbAccess acc = new DbAccess();
			acc.connectDBDefault(dbName);
			Statement statement = (Statement) acc.conn.createStatement();
			
			System.out.println("countSql: [" + countSql + "]");
			System.out.println("sql: [" + sql + "]");
			ResultSet countRs = statement.executeQuery(countSql);
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			countRs.next();
			int resultLength = Integer.parseInt(countRs.getString("count"));
			
			String[] result = new String[resultLength];
			
			int i = 0;
			while (rs.next()) {
				result[i++] = rs.getString(tableName);
			}
			acc.closeConnection();

			System.out.println("access table " + tableName + " result:  resultLength  " + resultLength + "\n");
			
			System.out.println("| " + tableName + "            |\n");
			for(int j = 0; j < resultLength; j++) {
				System.out.println("| " + result[j] + "       |\n");
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
