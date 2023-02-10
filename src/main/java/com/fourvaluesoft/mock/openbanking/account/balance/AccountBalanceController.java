package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.balance.service.impl.AccountBalanceServiceImpl;
import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.controller.AccountController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountBalanceController extends AccountController {

    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(String webResourcesPath) throws ServletException {
        this.method = "GET";

        accountBalanceService = new AccountBalanceServiceImpl(webResourcesPath);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tranDtime = getKeyValue(request, "tran_dtime");

        try {
            AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);
            request.setAttribute("accountBalance", accountBalance);

            return getSucceedViewPath();
        } catch (AccountNotFoundException exception) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return getErrorViewPath();
        }
    }

    @Override
    protected String getSucceedViewPath() {
        return "/balance/showAccountBalance.jsp";
    }
}