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
        String fintechUseNum = getStringParameter(request, "fintech_use_num");
        String bankTranId = getStringParameter(request, "bank_tran_id");
        String tranDtime = getStringParameter(request, "tran_dtime");

        AccountBalance accountBalance;

        if (fintechUseNum != null && bankTranId != null && tranDtime != null) {
            accountBalance = accountBalanceService.getBalance(fintechUseNum, bankTranId, tranDtime);
        } else if (fintechUseNum == null && bankTranId == null && tranDtime != null) {
            accountBalance = accountBalanceService.getBalance(tranDtime);
        } else {
            accountBalance = new AccountBalance();
        }

        request.setAttribute("accountBalance", accountBalance);

        forwardToView(request, response);
    }

    protected String getStringParameter(HttpServletRequest request, String parameterKey) {
        String result = request.getParameter(parameterKey);

        if (result != null && result.equals(""))
            return null;

        return result;
    }

    protected long getLongParameter(HttpServletRequest request, String parameterKey) throws NumberFormatException {
        return Long.parseLong(request.getParameter(parameterKey));
    }

    protected void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/balance/showAccountBalance.jsp").forward(request, response);
    }
}
