package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;
import com.young.model.TestDao;

public class DeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		int num=Integer.parseInt(req.getParameter("num"));
		TestDao testDao=new TestDao();
		testDao.deleteOne(num);
		String success="{\n" + 
				"	\"success\": \"success\"\n" + 
				"}";
		req.setAttribute("success", success);
		return "young/test";
	}

}
