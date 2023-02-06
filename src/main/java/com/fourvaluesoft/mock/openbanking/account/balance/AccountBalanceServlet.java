package com.fourvaluesoft.mock.openbanking.account.balance;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.AccountBalance;
import com.fourvaluesoft.mock.openbanking.account.balance.domain.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.account.balance.exception.AccountBalanceNotFoundException;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rootPath = getServletContext().getRealPath("/");

        AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl(rootPath);

        String tranDtime = getKeyValue(request);

        try {
            AccountBalance accountBalance = accountBalanceService.getBalance(tranDtime);
            request.setAttribute("accountBalance", accountBalance);
        } catch (AccountBalanceNotFoundException exception) {
            request.setAttribute("error", new ErrorResponse("A0021", "데이터가 없습니다."));
        }

        forwardToView(request, response);
    }

    protected String getKeyValue(HttpServletRequest request) {
        return request.getParameter("tran_dtime");
    }

    protected void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/balance/showAccountBalance.jsp").forward(request, response);
    }
}