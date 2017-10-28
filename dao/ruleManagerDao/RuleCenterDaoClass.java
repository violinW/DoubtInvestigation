package ruleManagerDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbAccess;
import common.ruleOperator;
import common.ruleOperator.RuleType;
import ruleManager.ruleCenterClass;

public class RuleCenterDaoClass extends ruleCenterClass {

	public static void main(String[] args) {
		RuleCenterDaoClass self = new RuleCenterDaoClass();
		self.getAllRuleType();
	}
	
	/**
	 * 对外提供简介
	 */
	@Override
	public String getIntroduction() {
		// TODO Auto-generated method stub
		return "规则中心对外提供规则的读写";
	}

	@Override
	public void addRule(String ruleTypeID, String ruleName, String typeIntroduction) {
		int rs;
		ruleOperator ro = new ruleOperator();
		try {
			rs = ro.addRuleData(ruleTypeID, ruleName, typeIntroduction);
			if(rs >= 0) {
				System.out.println("添加规则成功！");
			}else {
				System.out.println("添加规则失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object[] getAllRuleType() {
		// TODO Auto-generated method stub
		ruleOperator ro = new ruleOperator();
		RuleType[] rs = ro.getAllRuleType(0);
		System.out.println("-----------------");
		System.out.println("ruleTypeList:");
		System.out.println("-----------------");

		
		return null;
	}

	@Override
	public Object[] getRule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getRule(String[] ruleNames) {
		// TODO Auto-generated method stub
		return null;
	}

}
