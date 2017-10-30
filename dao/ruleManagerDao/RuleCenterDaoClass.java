package ruleManagerDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbAccess;
import common.ruleOperator;
import defaultStructure.Rule;
import defaultStructure.RuleType;
import ruleManager.ruleCenterClass;

public class RuleCenterDaoClass extends ruleCenterClass {

	public static void main(String[] args) {
		RuleCenterDaoClass self = new RuleCenterDaoClass();
		String[] names = {"FieldIncoherent"};
		self.getRule(names);
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
	public void addRule(String ruleTypeID, String ruleName, String ruleIntroduction) {
		ruleOperator ro = new ruleOperator();
		int rs = ro.addRuleData(ruleTypeID, ruleName, ruleIntroduction);
		if(rs >= 0) {
			System.out.println("添加规则成功！");
		}else {
			System.out.println("添加规则失败！");
		}
	}

	@Override
	public RuleType[] getAllRuleType() {
		ruleOperator ro = new ruleOperator();
		RuleType[] rs = (RuleType[])(ro.getAllRuleType(0));
		
		return rs;
	}

	@Override
	public Rule[] getRule() {
		ruleOperator ro = new ruleOperator();
		Rule[] rs = (Rule[])(ro.getAllRule(0));
		return rs;
	}

	@Override
	public Rule[] getRule(String[] ruleNames) {
		ruleOperator ro = new ruleOperator();
		Rule[] rs = (Rule[])(ro.getRuleByNames(ruleNames));
		return rs;
	}

}
