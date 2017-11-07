package targetStructure;

public class Blacklist {
	public String UUID;
	public String ruleType;
	public String ruleName;
	public String type;
	public String databaseName;
	public String tableName;
	public String fieldName;
	public String fieldValue;
	public String startNo;
	public String endNo;
	public String content;
	
	public static boolean validate(Blacklist data) {
		if(data.type == "single" && (data.UUID == null || data.ruleType == null || data.ruleName == null || data.tableName == null 
				|| data.databaseName == null || data.fieldName == null || data.fieldValue == null || data.content == null)) {
			return false;
		}
		if(data.type == "muntiple" && (data.UUID == null || data.ruleType == null || data.ruleName == null || data.tableName == null 
				|| data.databaseName == null || data.fieldName == null || data.startNo == null || data.endNo == null || data.content == null)) {
			return false;
		}
		return true;
	}
}
