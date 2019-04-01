package com.jtdx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jtdx.bean.User;
import com.jtdx.dao.jdbc;

public class QueryListServlet implements Servlet {

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

		// 操作数据库的工具类
		jdbc jdbc = new jdbc();

		// 查询用户
		try {
			List<User> user = jdbc.selectUserList();
			System.out.println("用户列表:");
			System.out.println(user);
			String list = user.toString();
			res.setContentType("text/html;charset=UTF-8"); 
			res.getWriter().write(list);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
