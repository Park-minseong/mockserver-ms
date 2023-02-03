<%@ page import="com.google.gson.Gson"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
Gson gson = new Gson();
if (request.getAttribute("error") != null)
     request.getRequestDispatcher("/WEB-INF/pages/public/error.jsp").forward(request, response);

String accountBalanceJson = gson.toJson(request.getAttribute("accountBalance"));
out.println(accountBalanceJson);
%>