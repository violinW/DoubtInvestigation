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
	public MissDataInvestigation(String tableName, String fieldName, String ruleName, String type, String[] data, String RegExp){
		if(ruleName == "FieldIncoherent") {
			FieldIncoherentDataHandler fiDataHandler = new FieldIncoherentDataHandler();
			if(type == "default") {
				fiDataHandler.defaultDataFI(tableName, fieldName, data, RegExp);
			}else if(type == "partition") {
				fiDataHandler.partitionDataFI(data);
			}
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
		 * 普通连号，要求数据必须全部是阿拉伯数字且连续(default)
		 * @param tableName 数据库名
		 * @param fieldName 字段名
		 * @param data 数据
		 */
		public void defaultDataFI(String tableName, String fieldName, String[] data, String RegExp) {
			System.out.println("---------------start FieldIncoherentDataHandler defaultDataFI-------------");
			List<HashMap[]> finalList = new ArrayList<HashMap[]>();
			
			for(int i = 0; i < data.length; i++) {
				int dt = Integer.parseInt(data[i]);
				RegExpModel[] dataAnalysisResult = this.analysisRegExp(data[i], RegExp);
				List<HashMap> hashlist = new ArrayList<HashMap>();
				int j = 0;
				while(!dataAnalysisResult[j].WhetherLastOne) {
					HashMap item = new HashMap<"code"+j, dataAnalysisResult[j].Value>();
					hashlist.add(item);
					j++;
				}
				
				//对第一个做处理
				if(i == 0 && dt == 2) {
					addSingleFISlightBlacklist(tableName, fieldName, data[i]);
				}else if(i == 0 && dt > 2) {
					//addMutipleFISlightBlacklist(tableName, fieldName, '1');
				}
				
			}
			System.out.println("---------------end FieldIncoherentDataHandler defaultDataFI-------------");
			
		}
		/**
		 * 解析单个字段
		 * @param fieldValue 需要解析的字段值
		 * @param RegExp 解析用的正则表达式
		 * @return
		 */
		private RegExpModel[] analysisRegExp(String fieldValue, String RegExp) {
			Pattern r1 = Pattern.compile(RegExp);
			Matcher m1 = r1.matcher(fieldValue);
			Pattern r2 = Pattern.compile("\\([\\s\\S]+?\\)");
			Matcher m2 = r2.matcher(RegExp);
			String[] rs = m2.toString().split(",");
			
			if (m1.find()) {
				List<RegExpModel> list = new ArrayList<RegExpModel>();
				for(int i = 0; i < m1.groupCount(); i++) {
					System.out.println("RegExp: " + rs[i] + ", Value: " + m1.group(i));
					
					RegExpModel data = new RegExpModel();
					data.RegExp = rs[i];
					data.Value = m1.group(i);
					data.WhetherLastOne = (i >= m1.groupCount());
					list.add(data);
				}
				return (RegExpModel[])list.toArray();
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
		
		//分段连号，按照正则表达式对数据分段，其中某一段要求连续(partition)
		public void partitionDataFI(String[] data) {
			
		}
		
	}

}
