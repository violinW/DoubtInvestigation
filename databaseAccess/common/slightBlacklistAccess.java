package common;

import java.sql.SQLException;
import java.util.UUID;

import com.mysql.jdbc.Statement;

import config.TargetDBConfig;
import targetStructure.Blacklist;

public class slightBlacklistAccess {
	TargetDBConfig targetConf = new TargetDBConfig();

	public int addSingleSlightBlacklist(Blacklist blData) {
		boolean validResult = Blacklist.validate(blData);
		if(validResult) {
			UUID Id = UUID.randomUUID();
			String sql = "insert into slightBlacklist (UUID, ruleType, ruleName, databaseName, tableName, fieldName, fieldValue, content) value ('" + Id + "', '" + 
			blData.ruleType + "','" + blData.ruleName + "','" + blData.databaseName + "','" + blData.tableName + "','" + blData.fieldName + "','" + blData.fieldValue + "','" + blData.content + "')";

			try {
				
				DbAccess acc = new DbAccess();
				acc.connectDB(targetConf.host, targetConf.port, targetConf.dbName, targetConf.user, targetConf.password);
				Statement statement = (Statement) acc.conn.createStatement();
				
				System.out.println("sql: [" + sql + "]");
				
				// 结果集
				int rs = statement.executeUpdate(sql);
				
				System.out.println("access table " + targetConf.dbName + " insert " + rs);
				return rs;
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}catch (Exception e) {

				e.printStackTrace();

			}
		}else {
			System.out.println("Blacklist数据验证不通过");
		}
		return -1;
	}

	public int addMutipleSlightBlacklist(Blacklist blData) {
		boolean validResult = Blacklist.validate(blData);
		if(validResult) {
			UUID Id = UUID.randomUUID();
			String sql = "insert into slightBlacklist (UUID, ruleType, ruleName, databaseName, tableName, fieldName, startNo, endNo, content) value ('" + Id + "', '" + 
			blData.ruleType + "','" + blData.ruleName + "','" + blData.databaseName + "','" + blData.tableName + "','" + blData.fieldName + "','" + blData.startNo + "','" 
					+ blData.endNo + "','" + blData.content + "')";

			try {
				
				DbAccess acc = new DbAccess();
				acc.connectDB(targetConf.host, targetConf.port, targetConf.dbName, targetConf.user, targetConf.password);
				Statement statement = (Statement) acc.conn.createStatement();
				
				System.out.println("sql: [" + sql + "]");
				
				// 结果集
				int rs = statement.executeUpdate(sql);
				
				System.out.println("access table " + targetConf.dbName + " insert " + rs);
				return rs;
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}catch (Exception e) {

				e.printStackTrace();

			}
		}else {
			System.out.println("Blacklist数据验证不通过");
		}
		return -1;
	}
}
