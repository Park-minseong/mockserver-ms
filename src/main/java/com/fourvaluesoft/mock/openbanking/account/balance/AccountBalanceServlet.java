package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.service.AccountBalanceService;
import com.fourvaluesoft.mock.openbanking.account.balance.service.impl.AccountBalanceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountBalanceServlet", value = "/balance")
public class AccountBalanceServlet extends HttpServlet {

    private final AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tranDtime = getParameterAsString(request, "tran_dtime");

        AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);

        request.setAttribute("accountBalance", accountBalance);

        forwardToView(request, response);
    }

    protected String getParameterAsString(HttpServletRequest request, String parameterKey) {
        return request.getParameter(parameterKey);
    }

    protected long getParameterAsLong(HttpServletRequest request, String parameterKey) throws NumberFormatException {
        return Long.parseLong(request.getParameter(parameterKey));
    }

    protected void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/balance/showAccountBalance.jsp").forward(request, response);
    }
}
