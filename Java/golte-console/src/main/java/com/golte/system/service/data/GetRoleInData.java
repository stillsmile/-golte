package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 操作角色请求
 *
 * @author wj@163.com
 * @date 2018/5/29 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleInData implements Serializable {
	private static final long serialVersionUID = 1L;

	// 角色id
	private Long id;

	// 角色名称
	private String roleName;

	// 角色描述
	private String roleDes;

	// 角色排序值
	private Integer roleSortValue;

	// 状态（0无效 1有效）
	private String roleStatus;

	private String supperAdmin;

	// 操作人[默认取当前登录人]
	private String loginAccount;

	// 删除id集合
	private List<Long> ids;

	// 页大小
	private int pageSize;

	// 页码
	private int pageNum;
}
