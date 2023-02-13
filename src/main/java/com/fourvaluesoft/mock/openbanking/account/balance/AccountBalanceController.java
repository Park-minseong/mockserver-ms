package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.balance.service.impl.AccountBalanceServiceImpl;
import com.fourvaluesoft.mock.openbanking.controller.KeyValueController;
import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountBalanceController extends KeyValueController {

    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(String webResourcesPath) {
        this.method = "GET";

        accountBalanceService = new AccountBalanceServiceImpl(webResourcesPath);
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) {
        String tranDtime = getKeyValueFromParameter(request, "tran_dtime");

        try {
            AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);
            request.setAttribute("accountBalance", accountBalance);

            return getSucceedViewPath();
        } catch (DataNotFoundException exception) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return ERROR_VIEW;
        }
    }

    @Override
    protected String getSucceedViewPath() {
        return "/balance/showAccountBalance.jsp";
    }
}