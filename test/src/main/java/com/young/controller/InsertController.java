package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;
import com.young.model.TestDao;

public class InsertController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		//int num=Integer.parseInt(req.getParameter("num"));
		String id=req.getParameter("id");
		String sub=req.getParameter("sub");
		String content=req.getParameter("content");
		String success="{\n" + 
				"	\"success\": \"success\"\n" + 
				"}";
		TestDao testDao=new TestDao();
		testDao.insertOne(id, sub, content);
		req.setAttribute("success", success);
		//System.out.println("Insert success"+id+sub+content);
		return "young/test";
	}

}
