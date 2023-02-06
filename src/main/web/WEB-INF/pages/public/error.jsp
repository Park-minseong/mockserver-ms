<%@ page import="com.google.gson.Gson"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
Gson gson = new Gson();

String errorJson = gson.toJson(request.getAttribute("error"));
out.println(errorJson);
%>