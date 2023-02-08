package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.balance.service.impl.AccountBalanceServiceImpl;
import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountBalanceController extends Controller {

    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(String webResourcesPath) throws ServletException {
        accountBalanceService = new AccountBalanceServiceImpl(webResourcesPath);
    }

    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tranDtime = getKeyValue(request);

        try {
            AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);
            request.setAttribute("accountBalance", accountBalance);

            return getSucceedViewPath();
        } catch (AccountNotFoundException exception) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터가 없음 "));

            return getErrorViewPath();
        }
    }

    private String getKeyValue(HttpServletRequest request) {
        return request.getParameter("tran_dtime");
    }

    @Override
    protected String getSucceedViewPath() {
        return "balance/showAccountBalance.jsp";
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}