<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String data=(String)request.getAttribute("list"); %>
{
	"data":<%=data %>
}