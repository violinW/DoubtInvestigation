/**
 * 规则管理部门
 */
package ruleManager;

/**
 * 文案管理员类 
 * 下属于“规则管理部门”，负责查询和定期报告结果
 * 
 * 1、提供文案管理员名字（name）属性 不能被继承 不能被改写
 * 2、提供文案管理员名字的访问方法（getCopywritingAdministratorName） 公开方法 不需要权限验证
 * 3、提供文案的获取方法(getCopywritingContent)
 */
public abstract class CopywritingAdministratorClass {
	final static String name = "文案管理员violin";

	public abstract void getCopywritingAdministratorName();

	public abstract String getCopywritingContent();
}