package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID; 

import com.mysql.jdbc.Statement;
import config.SystemDBConfig;
/**
 * 规则添加器
 * 1、添加规则名称
 */
public class ruleOperator {
	static String dbName="DoubtInvestigation";
	SystemDBConfig systemConf = new SystemDBConfig();
	
	public int addRuleData(String ruleTypeID, String ruleName, String typeIntroduction) throws SQLException  {
		UUID ruleID = UUID.randomUUID();   
		String sql = "insert into rule(UUID, typeUUID, ruleName, typeIntroduction) values (" + ruleID + ", " 
		+ ruleTypeID + ", " + ruleName + ", " + typeIntroduction + ")";

		try {
			Object conn = new DbAccess(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			// statement用来执行SQL语句
			Statement statement = (Statement) ((Connection)conn).createStatement();
			// 结果集
			int rs = ((Statement)statement).executeUpdate(sql);
			
			return rs;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}catch (Exception e) {

			e.printStackTrace();

		}
		return -1;
		
	}
	
	/**
	 * 获得所有规则类型
	 * @param number 当number为0时则筛选所有数据
	 * @return
	 */
	public RuleType[] getAllRuleType(int number) {
		String sql = "select * from ruleType";
		if(number > 0) {
			sql += " limit " + String.valueOf(number);
		}
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			Connection conn = acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			RuleType[] result = {};
			int i = 0;
			while (rs.next()) {
				RuleType item = new RuleType();
				item.UUID=rs.getString("UUID");
				item.typeName=rs.getString("typeName");
				item.typeIntroduction = rs.getString("typeIntroduction");
				result[i++] = item;
			}
			acc.closeConnection(conn);
			System.out.print(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
	}
	public class RuleType{
		public String UUID;
		public String typeName;
		public String typeIntroduction;
	}
	
	public void closeRs() {
		
	}
}
