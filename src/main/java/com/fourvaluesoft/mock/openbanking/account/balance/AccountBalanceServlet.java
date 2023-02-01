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
        String fintechUseNum = getParameterAsString(request, "fintech_use_num");
        String bankTranId = getParameterAsString(request, "bank_tran_id");
        String tranDtime = getParameterAsString(request, "tran_dtime");

        AccountBalance accountBalance = getAccountBalanceByParamter(fintechUseNum,bankTranId,tranDtime);

        request.setAttribute("accountBalance", accountBalance);

        forwardToView(request, response);
    }

    private AccountBalance getAccountBalanceByParamter(String fintechUseNum, String bankTranId, String tranDtime) {
        if (fintechUseNum != null && bankTranId != null && tranDtime != null) {
            return accountBalanceService.getBalance(fintechUseNum, bankTranId, tranDtime);
        } else if (fintechUseNum == null && bankTranId == null && tranDtime != null) {
            return accountBalanceService.getBalance(tranDtime);
        } else {
            return new AccountBalance();
        }
    }

    protected String getParameterAsString(HttpServletRequest request, String parameterKey) {
        String result = request.getParameter(parameterKey);

        if (result != null && result.equals(""))
            return null;

        return result;
    }

    protected long getParameterAsLong(HttpServletRequest request, String parameterKey) throws NumberFormatException {
        return Long.parseLong(request.getParameter(parameterKey));
    }

    protected void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/balance/showAccountBalance.jsp").forward(request, response);
    }


}
