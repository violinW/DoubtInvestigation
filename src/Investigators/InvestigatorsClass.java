/**
 * 侦查员
 */
package Investigators;


/**
 * 侦查员类 用于从给定数据中找出“可疑”的数据缺失或错误，但不进行验证
 * 
 * 1、扫描数据方法（checkData）需要传入规则和待扫描数据 公开方法 需要权限验证
 * 2、向规则中心获取规则（getRuleFromRuleCenter） 私有方法 提供给解读规则的对象使用
 * 3、举报怀疑对象（reportSuspect）需要将可疑对象交给核实者 私有方法 提供给解读规则的对象使用 4、检举员子类
 */
public abstract class InvestigatorsClass {

	public abstract Object[] checkData(Object[] data, String ruleType, String ruleName);

	public abstract Object[] checkData(Object[] data, String[] ruleName);

	protected abstract Object[] getRuleFromRuleCenter(String ruleName);

	protected abstract void reportSuspect(Object[] data);

	/**
	 * 检举员 用于解读规则并提出质疑
	 *
	 * 1、规则类型分发器（ruleTypeDistributor） 2、规则具体类型解读
	 *
	 */
	protected abstract class Prosecutor {

		protected abstract void ruleTypeDistributor(Object[] data, String ruleType, String ruleName);

		/**
		 * 数据缺失查找（"MissData"）
		 * 
		 * @return
		 */
		protected abstract Object[] findMissData(Object[] data, String ruleName);

		/**
		 * 数据过期查找（"DataExpired"）
		 * 
		 * @return
		 */
		protected abstract Object[] findDataExpired(Object[] data, String ruleName);

		/**
		 * 数据错误查找（"DataWrong"）
		 * 
		 * @return
		 */
		protected abstract Object[] findDataWrong(Object[] data, String ruleName);

	}
}
