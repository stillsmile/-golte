package com.golte.common.util;

/**
 * 
 * @author 作者 GXS
 * @date 创建时间 2017年6月7日 下午1:49:39
 * @description 异常定义
 */
public class UtilMessage {
	/*--------- 通用的获取数据提示--------*/
	public static final String GET_MESSAGE_SUCCESS = "提示：获取数据成功！";

	public static final String GET_MESSAGE_ERROR = "提示：获取数据失败！";

	/*--------- 通用的保存成功提示--------*/
	public static final String SAVE_MESSAGE_SUCCESS = "提示：保存成功！";

	public static final String PASSWORD_MESSAGE_SUCCESS = "提示：重置成功！";

	public static final String DISABLE_MESSAGE_SUCCESS = "提示：禁用成功！";

	public static final String ENABLE_MESSAGE_SUCCESS = "提示：启用成功！";

	public static final String SAVE_MESSAGE_ERROR = "提示：保存失败！";

	public static final String UPLOAD_MESSAGE_SUCCESS = "提示：上传成功！";

	public static final String UPLOAD_MESSAGE_ERROR = "提示：上传失败！";
	
	/*--------- 通用的查询成功提示--------*/
	public static final String SEARCH_MESSAGE_SUCCESS = "提示：查询成功！";

	public static final String SEARCH_MESSAGE_ERROR = "提示：查询失败！";


	/*--------- 通用的删除成功提示--------*/
	public static final String DEL_MESSAGE_SUCCESS = "提示：删除成功！";

	public static final String DEL_MESSAGE_ERROR = "提示：删除失败！";

	/*--------- 通用的发送成功提示--------*/
	public static final String SEND_MESSAGE_SUCCESS = "提示：消息发送成功！";

	public static final String SEND_MESSAGE_ERROR = "提示：消息发送失败！";

	public static final String SEND_SMS_MESSAGE_SUCCESS = "提示：验证码发送成功！";

	public static final String SEND_SMS_MESSAGE_ERROR = "提示：验证码发送失败！";

	/*--------- 通用的登录提示--------*/
	public static final String ERROR_MESSAGE = "提示：服务器出现了一些小状况，正在修复中！";

	public static final String LOGIN_SUCCESS = "提示：登录成功！";

	public static final String LOGIN_ERROR = "提示：登录失败！";

	public static final String LOGIN_OUT_SUCCESS = "提示：登出成功！";

	public static final String UPDATE_PASSWORD_SUCCESS = "提示：修改密码成功！";

	/*--------- 其他--------*/
	public static final String HTTP_ERROR = "提示：请求错误！";

	public static final String RESOURCE_ERROR = "提示：没有资源权限，请重新登录！";

	public static final String LOGIN_STATUS_ERROR = "提示：账号已失效，请重新登录！";

	/*--------- 登录业务--------*/
	public static final String LOGIN_NO_ACCOUNT = "提示：账号不存在";

	public static final String LOGIN_ACCOUNT_INVALID = "提示：账号已失效";

	public static final String LOGIN_PASSWORD_ERROR = "提示：密码不正确";

	public static final String UPDATE_PWD_LENGTH = "提示：请保持密码长度在6-20位";

	public static final String UPDATE_PWD_SAME_PASSWORD = "提示：新密码和旧密码不能相同";

	public static final String UPDATE_PWD_OLD_PASSWORD_ERROR = "提示：旧密码错误";

	/*--------- 系统设置--------*/
	public static final String ROLE_NAME_REPEAT = "提示：角色名称重复";

	public static final String ROLE_STATUS_ERROR = "提示：角色已关联用户，不可置为失效";

	public static final String ROLE_INIT_DELETE_ERROR = "提示：初始化角色不可删除";

	public static final String ROLE_DELETE_ERROR = "提示：存在角色已关联用户，不可删除";
	
	public static final String EMPLOYEE_ACCOUNT_REPEAT = "提示：用户账号重复";
	
	
	/*--------- 通用的获取资源点位提示--------*/
	public static final String GET_RESOURCEPOINT_SUCCESS = "提示：获取资源点位成功！";

	public static final String SAVE_RESOURCEPOINT_SUCCESS = "提示：保存资源点位成功！";

	public static final String SAVE_RESOURCEPOINT_ERROR = "提示：保存资源点位失败！";
	
	public static final String UPDATE_RESOURCEPOINT_SUCCESS = "提示：编辑资源点位成功！";

	public static final String UPDATE_RESOURCEPOINT_ERROR = "提示：编辑资源点位失败！";
	
	public static final String DELETE_RESOURCEPOINT_SUCCESS = "提示：删除资源点位成功！";

	public static final String DELETE_RESOURCEPOINT_ERROR = "提示：资源点位下存在子资源，不可删除！";
	
	public static final String DELETE_RESOURCEPROJECT_SUCCESS = "提示：资源项目删除成功！";
	
	public static final String DELETE_RESOURCEPROJECT_ERROR = "提示：资源项目已关联资源明细，不可删除！";

	/*--------- 基础数据城市管理提示--------*/

	public static final String CITY_NAME_REPEAT = "提示：城市名称重复！";

	public static final String CENTRAL_CITY_NAME_REPEAT = "提示：中心城市名称重复";

		/*--------- 基础数据城市管理提示--------*/

	public static final String MERCHANT_NAME_REPEAT = "提示：商家名称已存在！";

	public static final String CITY_MERCHANT_NAME_REPEAT = "提示：该城市商家名称已存在！";

	/*--------- 资源点位提示--------*/

	public static final String POINT_NODE_ERROR = "提示：存在节点已关联子节点，不可删除";

	public static final String DELETE_POINT_NODE_ERROR = "提示：存在已关联此节点的资源，不可删除";

	public static final String POINT_NAME_ERROR = "提示：已存在相同点位名称";

	/*--------- 资源项目提示--------*/
	public static final String PROJECT_NAME_ERROR = "提示：该城市下存在相同的项目名称";

	public static final String REPEAT_NAME_ERROR = "提示：该城市下存在相同的项目名称";

	/*--------- 激励管理提示--------*/
	public static final String PROJECT_CONTRACT_ERROR = "提示：该项目对应的合同不存在";

	public static final String ROLE_NAME_ERROR = "提示：不存在对应的激励角色";

	public static final String EXIXATION_DELETE_ERROR = "提示：存在非草稿状态激励信息，不可删除";

}
