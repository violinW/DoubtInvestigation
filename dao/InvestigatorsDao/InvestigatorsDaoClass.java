package InvestigatorsDao;

import Investigators.InvestigatorsClass;
import MissData.FieldIncoherentAccessClass;
import config.TargetDBConfig;
import ruleManagerDao.RuleCenterDaoClass;

public class InvestigatorsDaoClass extends InvestigatorsClass {
	RuleCenterDaoClass ruleCenter = new RuleCenterDaoClass();
	TargetDBConfig tarConf = new TargetDBConfig();
	
	@Override
	public Object[] getData(String ruleType, String ruleName, String tableName, String fieldName) {
		if(ruleType == "MissData" && ruleName == "FieldIncoherent") {
			FieldIncoherentAccessClass fieldIncoAcc = new FieldIncoherentAccessClass();
			
			String[] rs = fieldIncoAcc.excuteQuery(tableName, fieldName);
			return rs;
		}
		return null;
	}
	
	@Override
	public Object[] checkData(Object[] data, String ruleType, String ruleName) {
		if(ruleType == "MissData" && ruleName == "FieldIncoherent") {
			String[] fieldIncoData = (String[])data;
			
		}
		
		return null;
	}

	@Override
	protected Object[] getRuleFromRuleCenter(String ruleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void reportSuspect(Object[] data) {
		// TODO Auto-generated method stub
		
	}
	
	
}
