package com.jtdx.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jtdx.bean.User;
import com.jtdx.dao.jdbc;

public class UpdateUser implements Servlet {

	@Override
	public void destroy() {
		System.out.println("被销毁了");
	}

	@Override
	public ServletConfig getServletConfig() {

		return null;
	}

	@Override
	public String getServletInfo() {

		return null;
	}

	/**
	 * servelt的初始化
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("初始化");

	}

	/**
	 * 提供服务
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		User user = new User();

		// 接收参数
		String name = req.getParameter("name");

		String newName = req.getParameter("newName");
		String newPassword = req.getParameter("passWord");

		// 操作数据库的工具类
		jdbc jdbc = new jdbc();

		// 查询用户是否注册过
		try {
			user = jdbc.selectUser(name);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (user.getName() == null) {
			res.setContentType("text/html;charset=UTF-8"); 
			res.getWriter().write("找不到用户名");
		} else {
			// 成功
			try {
				jdbc.updateUser(name,newName, newPassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
			res.setContentType("text/html;charset=UTF-8"); 
			res.getWriter().write("修改成功");
		}
	}

}
