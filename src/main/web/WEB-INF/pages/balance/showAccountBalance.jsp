<%@ page import="com.google.gson.Gson"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
Gson gson = new Gson();

String accountBalanceJson = gson.toJson(request.getAttribute("accountBalance"));
out.println(accountBalanceJson);
%>