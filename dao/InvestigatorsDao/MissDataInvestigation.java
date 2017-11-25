package InvestigatorsDao;

import java.util.UUID;

import common.slightBlacklistAccess;
import config.TargetDBConfig;
import targetStructure.Blacklist;
import targetStructure.RegExpModel;
import java.util.regex.*;
import java.util.*;

/**
 * 数据丢失类型的举报
 * @author romens
 *
 */
public class MissDataInvestigation {
	private List<Object> MissDataList;
	
	public List<Object> getResult(){
		return MissDataList;
	}
	
	public MissDataInvestigation(String ruleName, List<Object> data, String RegExp){
		if(ruleName == "FieldIncoherent") {
			FieldIncoherentDataHandler fiDataHandler = new FieldIncoherentDataHandler();
			List<Object> dealedData = fiDataHandler.defaultDataFI(data, RegExp);
			MissDataList = fiDataHandler.defaultBlacklistFI(dealedData);
		}
	}
	/**
	 * 数据非连续性缺失(FieldIncoherent)
	 * @param data 待查数据
	 * @param type 数据非连续性类型（普通连号，规则性连号等）
	 */
	public class FieldIncoherentDataHandler {
		TargetDBConfig tarConf = new TargetDBConfig();
		slightBlacklistAccess sBlacklistAcc = new slightBlacklistAccess();
		
		/**
		 * 默认方法
		 * @param tableName 数据库名
		 * @param fieldName 字段名
		 * @param data 数据
		 */
		public List<Object> defaultDataFI(List<Object> data, String RegExp) {
			System.out.println("---------------start FieldIncoherentDataHandler defaultDataFI-------------");
			List<HashMap<String, List<HashMap<String, String>>>> finalList = new ArrayList();
			
			for(int i = 0; i < data.size(); i++) {
				List<HashMap<String, String>> hashlist = new ArrayList();
				List<RegExpModel> dataAnalysisResult = analysisRegExp((String)data.get(i), RegExp);
				
				int j = 0;
				
				//处理正则匹配后的数据(把每一个匹配项抓出来)
				while(!dataAnalysisResult.get(j).WhetherLastOne) {
					HashMap<String, String> temp = new HashMap();
					temp.put("code" + (j + 1), dataAnalysisResult.get(j).Value);
					hashlist.add(temp);
					j++;
				}
				
				HashMap<String, String> dataRs = new HashMap();
				dataRs.put("value", dataAnalysisResult.get(j).Value);
				hashlist.add(dataRs);

				HashMap<String, List<HashMap<String, String>>> finalItem = new HashMap();
				finalItem.put("value" + (i + 1), hashlist);
				finalList.add(finalItem);
			}
			
			List<Object> listResult = makeResult(finalList);
			System.out.println("---------------end FieldIncoherentDataHandler defaultDataFI-------------");
			
			return listResult;
		}
	
		public List<Object> defaultBlacklistFI(List<Object> data){
			System.out.println("---------------start FieldIncoherentDataHandler defaultBlacklistFI-------------");
			
			
			
			System.out.println("---------------end FieldIncoherentDataHandler defaultBlacklistFI-------------");
			
			return data;
		}
		
		private List<Object> makeResult(List<HashMap<String, List<HashMap<String, String>>>> list){
			List<HashMap<String, String>> resultList = new ArrayList();
			for(int i = 0; i < list.size(); i++) {
				Object[] item = list.get(i).values().toArray();
			}
			
			return null;
		}
		/**
		 * 解析单个字段
		 * @param fieldValue 需要解析的字段值
		 * @param RegExp 解析用的正则表达式
		 * @return
		 */
		private List<RegExpModel> analysisRegExp(String fieldValue, String RegExp) {
			Pattern r1 = Pattern.compile(RegExp);
			Matcher m1 = r1.matcher(fieldValue);
			Pattern r2 = Pattern.compile("\\(.*?\\)");
			Matcher m2 = r2.matcher(RegExp);
			
			if (m1.find()) {
				List<RegExpModel> list = new ArrayList<RegExpModel>();
				int i = 0;
				while(m2.find()) {
					System.out.println("RegExp: " + m2.group(0) + ", Value: " + m1.group(i+1));
					RegExpModel data = new RegExpModel();
					data.RegExp =  m2.group(0);
					data.Value = m1.group(i+1);
					data.WhetherLastOne = (i >= m1.groupCount()-1);
					i++;
					list.add(data);
				}
				return list;
		      } else {
					return null;
		      }
		}
		
		private int addSingleFISlightBlacklist(String tableName, String fieldName, String fieldValue) {
			Blacklist blData = new Blacklist();
			blData.UUID = UUID.randomUUID().toString();
			blData.ruleType = "MissData";
			blData.ruleName = "FieldIncoherent";
			blData.type = "single";
			blData.databaseName = tarConf.dbName;
			blData.tableName = tableName;
			blData.fieldName = fieldName;
			blData.fieldValue = fieldValue;
			
			int rs = sBlacklistAcc.addSingleSlightBlacklist(blData);
			return rs;
		}
		
		private int addMutipleFISlightBlacklist(String tableName, String fieldName, String startNo, String endNo) {
			Blacklist blData = new Blacklist();
			blData.UUID = UUID.randomUUID().toString();
			blData.ruleType = "MissData";
			blData.ruleName = "FieldIncoherent";
			blData.type = "mutiple";
			blData.databaseName = tarConf.dbName;
			blData.tableName = tableName;
			blData.fieldName = fieldName;
			blData.startNo = startNo;
			blData.endNo = endNo;
			
			int rs = sBlacklistAcc.addMutipleSlightBlacklist(blData);
			return rs;
		}
		
	}

}
