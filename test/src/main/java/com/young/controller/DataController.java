package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;
import com.young.model.TestDao;

public class DataController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		if(req.getParameter("type").equals("list")) {
			TestDao testDao=new TestDao();
			req.setAttribute("list", testDao.selectAll().toString());
		}
		if(req.getParameter("type").equals("detail")) {
			int num=Integer.parseInt(req.getParameter("num"));
			TestDao testDao=new TestDao();
			req.setAttribute("list", testDao.selectOne(num).toString());
		}
		return "young/test";
	}

}
