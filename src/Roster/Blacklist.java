/**
 * 黑白名单
 */
package Roster;

/**
 * 黑名单类 某些长期出现错误的数据流入黑名单
 * 黑名单分为两个：可信度较低数据的黑名单（slightBlacklist），可信度非常低数据的黑名单（seriousBlacklist）。
 * 
 * 黑名单是存放格式举例： { "ruleType": "MissData", "ruleName": "FieldIncoherent",
 * "content": "blablabla" }
 * 
 * 1、对外提取名单（getBlacklist） 公开方法 需要权限验证 2、写入名单（writeIntoBlacklist） 公开方法 需要权限验证
 */
public abstract class Blacklist {

	/**
	 * 提取所有黑名单
	 */
	public abstract String getBlacklist();

	/**
	 * 根据规则名称提取黑名单
	 * 
	 * @param ruleName
	 *            规则类型名称
	 * @return
	 */
	public abstract String getBlacklist(String[] ruleName);

	public abstract String writeIntoBlacklist(Object[] data);

}
