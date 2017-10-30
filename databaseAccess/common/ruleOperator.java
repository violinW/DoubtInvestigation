package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID; 
import com.mysql.jdbc.Statement;
import config.SystemDBConfig;
import defaultStructure.Rule;
import defaultStructure.RuleType;

/**
 * 规则添加器
 * 1、添加规则名称
 */
public class ruleOperator {
	static String dbName = "DoubtInvestigation";
	SystemDBConfig systemConf = new SystemDBConfig();
	
	public int addRuleData(String ruleTypeID, String ruleName, String ruleIntroduction) {
		System.out.println("enter addRuleData 添加规则类型\n\n");
		
		UUID ruleID = UUID.randomUUID();   
		
		String sql = "insert into rule(UUID, typeUUID, ruleName, ruleIntroduction) values ('" + ruleID + "', '" 
		+ ruleTypeID + "', '" + ruleName + "', '" + ruleIntroduction + "')";
		
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();
			
			System.out.println("sql: [" + sql + "]");
			
			// 结果集
			int rs = statement.executeUpdate(sql);
			
			System.out.println("access table ruleType insert " + rs);
			
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
	 * @param limit 当limit为0时则筛选所有数据
	 * @return
	 */
	public RuleType[] getAllRuleType(int limit) {
		System.out.println("enter getAllRuleType 获取所有规则类型\n\n");
		
		String countSql = "select count(*) as count from ruleType";
		String sql = "select * from ruleType";
		if(limit > 0) {
			sql += " limit " + String.valueOf(limit);
		}
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();
			
			ResultSet countRs = statement.executeQuery(countSql);
			countRs.next();
			int resultLength = Integer.parseInt(countRs.getString("count"));
			
			ResultSet rs = statement.executeQuery(sql);
			RuleType[] result = new RuleType[resultLength];
			int i = 0;
			while (rs.next()) {
				RuleType item = new RuleType();
				item.UUID=rs.getString("UUID");
				item.typeName=rs.getString("typeName");
				item.typeIntroduction = rs.getString("typeIntroduction");
				result[i++] = item;
			}
			acc.closeConnection();

			System.out.println("access table ruleType result:  resultLength  " + resultLength + "\n");
			
			System.out.println("| UUID            | typeName             | typeIntroduction\n");
			for(int j = 0; j<resultLength; j++) {
				System.out.println("| " + result[j].UUID + "       | " + result[j].typeName + "        | " + result[j].typeIntroduction + "\n");
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得所有规则类型
	 * @param limit 当limit为0时则筛选所有数据
	 * @return
	 */
	public Rule[] getAllRule(int limit) {
		System.out.println("enter getAllRule 获取所有规则\n\n");
		
		String countSql = "select count(*) as count from rule";
		String sql = "select * from rule";
		if(limit > 0) {
			sql += " limit " + String.valueOf(limit);
		}
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();
			
			ResultSet countRs = statement.executeQuery(countSql);
			countRs.next();
			int resultLength = Integer.parseInt(countRs.getString("count"));
			
			ResultSet rs = statement.executeQuery(sql);
			Rule[] result = new Rule[resultLength];
			int i = 0;
			while (rs.next()) {
				Rule item = new Rule();
				item.UUID = rs.getString("UUID");
				item.typeUUID = rs.getString("typeUUID");
				item.ruleName = rs.getString("ruleName");
				item.ruleIntroduction = rs.getString("ruleIntroduction");
				result[i++] = item;
			}
			acc.closeConnection();

			System.out.println("access table rule result:  resultLength  " + resultLength + "\n");
			
			System.out.println("| UUID            | typeUUID            | ruleName             | ruleIntroduction\n");
			for(int j = 0; j < resultLength; j++) {
				System.out.println("| " + result[j].UUID + "       | " + result[j].typeUUID + "       | " + result[j].ruleName + 
						"        | " + result[j].ruleIntroduction + "\n");
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据规则类型Id获取规则类型
	 * @param uuid 规则类型Id
	 * @return
	 */
	public RuleType getRuleTypeById(String uuid) {
		System.out.println("enter getRuleTypeById 根据规则类型Id获取规则类型\n\n");
		
		String sql = "select * from ruleType where UUID = '" + uuid + "'";
		
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			RuleType result = new RuleType();
			rs.next();
			
			result.UUID = rs.getString("UUID");
			result.typeName = rs.getString("typeName");
			result.typeIntroduction = rs.getString("typeIntroduction");
			
			acc.closeConnection();

			System.out.println("access table ruleType result:\n");
			
			System.out.println("| UUID            | typeName             | typeIntroduction\n");
			System.out.println("| " + result.UUID + "       | " + result.typeName + "        | " + result.typeIntroduction + "\n");
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 根据规则Id获取规则
	 * @param uuid
	 * @return
	 */
	public Rule getRuleById(String uuid) {
		System.out.println("enter getRuleById 根据规则Id获取规则\n\n");
		
		String sql = "select * from rule where UUID = '" + uuid + "'";
		
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();

			System.out.println("sql: " + sql);
			ResultSet rs = statement.executeQuery(sql);
			Rule result = new Rule();
			rs.next();
			
			result.UUID = rs.getString("UUID");
			result.typeUUID = rs.getString("typeUUID");
			result.ruleName = rs.getString("ruleName");
			result.ruleIntroduction = rs.getString("ruleIntroduction");
			
			acc.closeConnection();

			System.out.println("access table rule result:\n");
			
			System.out.println("| UUID            | typeUUID            | ruleName             | ruleIntroduction\n");
			System.out.println("| " + result.UUID + "       | " + result.typeUUID + 
					"        | \" + result.ruleName + \"        | " + result.ruleIntroduction + "\n");
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 根据规则名称列表获取规则
	 * @param names
	 * @return
	 */
	public Rule[] getRuleByNames(String[] names) {
		System.out.println("enter getRuleByNames 根据规则名称列表获取规则\n\n");
		
		String nameList = "";
		
		for(int i = 0; i<names.length;i++) {
			nameList += names[i];
			nameList += "', '";
		}

		String countSql = "select count(*) as count from rule where ruleName in ('" + nameList + "')";
		String sql = "select * from rule where ruleName in ('" + nameList + "')";
		
		try {
			DbAccess acc = new DbAccess();
			// statement用来执行SQL语句
			acc.connectDB(systemConf.host, systemConf.port, dbName, systemConf.user, systemConf.password);
			Statement statement = (Statement) acc.conn.createStatement();

			System.out.println("countSql: " + countSql);
			System.out.println("sql: " + sql);
			ResultSet countRs = statement.executeQuery(countSql);
			countRs.next();
			int resultLength = Integer.parseInt(countRs.getString("count"));
			ResultSet rs = statement.executeQuery(sql);
			int i = 0;
			Rule[] result = new Rule[resultLength];
			while (rs.next()) {
				Rule item = new Rule();
				item.UUID = rs.getString("UUID");
				item.typeUUID = rs.getString("typeUUID");
				item.ruleName = rs.getString("ruleName");
				item.ruleIntroduction = rs.getString("ruleIntroduction");
				result[i++] = item;
			}
			
			acc.closeConnection();

			System.out.println("access table rule result:\n");
			
			System.out.println("| UUID            | typeUUID            | ruleName             | ruleIntroduction\n");
			for(int j = 0; j < resultLength; j++) {
				System.out.println("| " + result[j].UUID + "       | " + result[j].typeUUID + "       | " + result[j].ruleName + 
						"        | " + result[j].ruleIntroduction + "\n");
			}
		
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
