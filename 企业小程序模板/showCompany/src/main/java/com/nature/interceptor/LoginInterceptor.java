package com.nature.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 登录过滤器.
 * @description 用于限制未登录用户的操作
 * @author len
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object account = ActionContext.getContext().getSession().get("company");
		if (account != null) {
			return invocation.invoke();
		}
		return "noLogin";
	}

}
