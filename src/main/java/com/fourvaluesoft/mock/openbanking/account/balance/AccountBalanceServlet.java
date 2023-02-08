package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.common.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.balance.service.impl.AccountBalanceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountBalanceServlet", value = "/account/balance")
public class AccountBalanceServlet extends HttpServlet {

    private final String PAGES_PATH = "/WEB-INF/pages/";

    private AccountBalanceService accountBalanceService;

    @Override
    public void init() throws ServletException {
        accountBalanceService = new AccountBalanceServiceImpl(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tranDtime = getKeyValue(request);

        try {
            AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);
            request.setAttribute("accountBalance", accountBalance);

            forwardToView(request, response);
        } catch (AccountNotFoundException exception) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터가 없음"));

            forwardToError(request, response);
        }
    }

    private String getKeyValue(HttpServletRequest request) {
        return request.getParameter("tran_dtime");
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGES_PATH + "balance/showAccountBalance.jsp").forward(request, response);
    }

    private void forwardToError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGES_PATH + "common/error.jsp").forward(request, response);
    }

    private ErrorResponse createErrorResponse(String rspCode, String rspMessage) {
        return new ErrorResponse(rspCode, rspMessage);
    }
}