/**
 * 规则管理部门
 */
package ruleManager;

/**
 * 规则中心 
 * 提供规则的读写定义等内容 
 * 规则中心--规则类型--具体规则 
 * 例："MissData"(数据缺失) 类型下面有个 "FieldIncoherent"(根据字段不连贯判断出的数据缺失)规则
 * 
 * 1、对外提供本方法介绍(getIntroduction) 完全公开 不需要权限验证
 * 2、调用某脚本存入某规则（addRule） 对外公开 需要权限验证
 * 3、读取所有规则类型（getAllRuleType） 对外公开 需要权限验证
 * 4、读取某规则（getRule） 对外公开 需要权限验证
 *
 */
public abstract class ruleCenterClass {
	final static String introduction = "介绍"; // TODO 完善介绍

	final static String[] ruleTypes = { "MissData", // 数据缺失类型
			"DataExpired", // 数据过期类型
			"DataWrong" // 数据错误类型
	};

	/**
	 * 对外提供简介
	 * @return
	 */
	public abstract String getIntroduction();

	/**
	 * 调用某脚本存入某规则
	 */
	public abstract void addRule(String ruleType, String ruleName, String typeIntroduction);

	/**
	 * 获取某规则类型
	 * @return
	 */
	public abstract Object[] getAllRuleType();

	/**
	 * 获取全部规则
	 * @return
	 */
	public abstract Object[] getRule();

	/**
	 * 通过名称获取规则
	 * 
	 * @return
	 */
	public abstract Object[] getRule(String[] ruleNames);
}