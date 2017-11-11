package InvestigatorsDao;

import java.util.List;

import Investigators.InvestigatorsClass;
import MissData.FieldIncoherentAccessClass;
import config.TargetDBConfig;
import ruleManagerDao.RuleCenterDaoClass;

public class InvestigatorsDaoClass extends InvestigatorsClass {
	RuleCenterDaoClass ruleCenter = new RuleCenterDaoClass();
	TargetDBConfig tarConf = new TargetDBConfig();
	
	@Override
	public List<Object> getData(String tableName, String fieldName) {
		FieldIncoherentAccessClass FIAcc = new FieldIncoherentAccessClass();
		List<Object> data = FIAcc.excuteQuery(tableName, fieldName);
		return data;
	}
	
	@Override
	public List<Object> checkData(List<Object> data, String ruleType, String ruleName, String regExp) {
		if(ruleType == "MissData") {
			MissDataInvestigation MDInv = new MissDataInvestigation(ruleName, data, regExp);
			return MDInv.getResult();
		}
		
		return null;
	}

	@Override
	protected List<Object> getRuleFromRuleCenter(String ruleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void reportSuspect(List<Object> data) {
		// TODO Auto-generated method stub
		
	}
	
	
}
