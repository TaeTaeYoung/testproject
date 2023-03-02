package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import com.young.frame.Controller;

public class IndexController implements Controller {

	@Override
	public String execute(HttpServletRequest req) {
		
		return "index";
	}

}
