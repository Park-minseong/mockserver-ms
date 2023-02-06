<%@ page import="com.google.gson.Gson"%><%@ page import="com.google.gson.GsonBuilder"%><%@ page import="com.google.gson.FieldNamingPolicy"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

String accountBalanceJson = gson.toJson(request.getAttribute("accountBalance"));
out.println(accountBalanceJson);
%>