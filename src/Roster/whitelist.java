/**
 * 黑白名单
 */
package Roster;

/**
 * 白名单类 某些不需要审核的数据流入白名单
 * 
 * 白名单是存放格式举例： { "ruleType": "MissData", "ruleName": "FieldIncoherent",
 * "content": "blablabla" }
 * 
 * 1、对外提取名单（getWhitelist） 公开方法 需要权限验证 2、写入名单（writeIntoWhitelist） 公开方法 需要权限验证
 */
public abstract class whitelist {

	/**
	 * 提取所有白名单
	 */
	public abstract String getWhitelist();

	/**
	 * 根据规则名称提取白名单
	 * 
	 * @param ruleName
	 *            规则类型名称
	 * @return
	 */
	public abstract String getWhitelist(String[] ruleName);

	public abstract String writeIntoWhitelist(Object[] data);

}
