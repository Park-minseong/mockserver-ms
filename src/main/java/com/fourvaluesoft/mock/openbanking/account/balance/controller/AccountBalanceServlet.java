package com.fourvaluesoft.mock.openbanking.account.balance.controller;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl();//AccountBalanceService 객체 생성

        String fintechUseNum = request.getParameter("fintech_use_num");//핀테크 이용번호
        String bankTranId = request.getParameter("bank_tran_id");//은행거래고유번호
        String tranDtime = request.getParameter("tran_dtime");//요청일시

        AccountBalance accountBalance = accountBalanceService.getBalance(fintechUseNum, bankTranId, tranDtime);//핀테크 이용번호, 은행거래고유번호,요청일시으로 잔액 조회 후 AccountBalance객체 반환

        request.setAttribute("accountBalance", accountBalance);//조회 결과 request에 추가

        request.getRequestDispatcher("").forward(request, response);
    }
}
