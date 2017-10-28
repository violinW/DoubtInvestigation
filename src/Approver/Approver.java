/**
 * 审核者
 */
package Approver;

/**
 * 审核者类 审核者是黑白名单的实际用户
 * 
 * 黑名单审核： 一条数据第一次失误时置入slightBlacklist黑名单，审核员会定期跟踪黑名单客户，
 * 如果slightBlacklist中的某条数据在最近三次审核中都没有失误则删除该黑名单用户，
 * 如果slightBlacklist数据在最近十次审核中出现了超过一次失误，则列入seriousBlacklist名单
 * 如果seriousBlacklist数据在连续二十次审核中都没有出现失误，则回到slightBlacklist
 * 
 * 白名单审核： 某些数据在某些规则下不需要被审核的则放入白名单(whitelist) 白名单需注明规则类型、规则名称
 * 审核员在做相关规则审核的时候会忽略白名单里的数据
 *
 * 1、接收可疑数据（getSuspectData） 私有方法 2、记录举报处理情况（recodeSuspectData） 私有方法
 */
public abstract class Approver {

	protected abstract void getSuspectData(String ruleType, String ruleName, Object data);

	protected abstract void recodeSuspectData(Object[] data);
}
