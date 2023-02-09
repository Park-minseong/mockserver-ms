package com.fourvaluesoft.mock.openbanking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    abstract String getMethod();

    abstract String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    abstract String getSucceedViewPath();
}