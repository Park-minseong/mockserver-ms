package com.fourvaluesoft.mock.openbanking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    String getMethod();

    String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}