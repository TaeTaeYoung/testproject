package com.young.frame;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet {
	Map<String,Controller> handler=new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// init은 시작시 한번만 실행하며 컨트롤러 객체를 생성해서 성능 향상
		Map<String,String> mapping=new HashMap<>();
		String fname=config.getServletName()!=null?config.getServletName():"young";
		//System.out.println("fname:"+fname);
		URL res= config.getServletContext().getClassLoader().getResource(fname+".properties");
		//System.out.println("res:"+res);
		Properties prop=new Properties();
		try
		{
			prop.load(res.openStream());
			
			Set<Entry<Object, Object>> entrys = prop.entrySet();
			for(Entry<Object, Object> entry:entrys) {
				mapping.put(entry.getKey().toString(), entry.getValue().toString());
			}
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		Set<Entry<String,String>> entrys= mapping.entrySet();
		Iterator<Entry<String,String>> ite=entrys.iterator();
		try {
			
			while(ite.hasNext()) {
				Entry<String, String> entry=ite.next();
				Controller controller = (Controller) Class.forName(entry.getValue()).newInstance();
				handler.put(entry.getKey(), controller);
				
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doDo(req,resp);
	}
	protected void doDo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//handler map
		String url=req.getRequestURI();
		url=url.substring(req.getContextPath().length());
		//
		Controller controller=handler.get(url);
		String viewName=controller.execute(req);
		String prefix="/WEB-INF/views/";
		String suffix=".html";
		//view 
		if(viewName.startsWith("redirect:")) {
			resp.sendRedirect(viewName.substring("redirect:".length()));
			return;
		}
		if(viewName.contains("test")) suffix=".jsp";
		
		//System.out.println("req.toString() :"+req.toString());
		System.out.println("req.getAttribute.list :"+req.getAttribute("list"));
		//System.out.println("resp.getOutputStream.list :"+resp.getOutputStream());
		//resp.setStatus(200);
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req,resp);
	}
}
