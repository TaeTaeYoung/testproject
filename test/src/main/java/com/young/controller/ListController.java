package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;
import com.young.model.TestDao;

public class ListController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		return "list";
	}

}
