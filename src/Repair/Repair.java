/**
 * 修复者
 */
package Repair;

/**
 * 修复者 接受来自审核者的修复订单
 * 
 * 1、修复数据方法（repairData） 私有方法
 */
public abstract class Repair {

	/**
	 * 修复数据
	 * 
	 * @param ruleType
	 *            规则类型
	 * @param ruleName
	 *            规则名称
	 * @param data
	 *            待修复数据
	 * @return
	 */
	protected abstract String repairData(String ruleType, String ruleName, Object[] data);
}
