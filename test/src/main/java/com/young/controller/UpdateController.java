package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;
import com.young.model.TestDao;

public class UpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		int num=Integer.parseInt(req.getParameter("num"));
		String sub=req.getParameter("sub");
		String content=req.getParameter("content");
		String success="{\n" + 
				"	\"success\": \"success\"\n" + 
				"}";
		TestDao testDao=new TestDao();
		testDao.updateOne(num, sub, content);
		req.setAttribute("success", success);
		return "young/test";
	}

}
