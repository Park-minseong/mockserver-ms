<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.FieldNamingPolicy"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
%>
<%=gson.toJson(request.getAttribute("data"))%>