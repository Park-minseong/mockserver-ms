package com.fourvaluesoft.mock.openbanking.controller;

import com.fourvaluesoft.mock.openbanking.account.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.common.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Controller {

    public abstract String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public abstract String getMethod();

    protected abstract String getSucceedViewPath();

    protected String getErrorViewPath() {
        return "common/error.jsp";
    }

    protected ErrorResponse createErrorResponse(String rspCode, String rspMessage) {
        return new ErrorResponse(rspCode, rspMessage);
    }

    protected BadRequestException createBadRequestException(String message) {
        return new BadRequestException(message);
    }
}