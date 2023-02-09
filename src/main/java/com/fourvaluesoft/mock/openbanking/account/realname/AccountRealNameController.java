package com.fourvaluesoft.mock.openbanking.account.realname;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.fourvaluesoft.mock.openbanking.account.realname.service.impl.AccountRealNameServiceImpl;
import com.fourvaluesoft.mock.openbanking.controller.AccountController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountRealNameController extends AccountController {

    private final AccountRealNameService realNameService;

    public AccountRealNameController(String webResourcesPath) throws ServletException {
        realNameService = new AccountRealNameServiceImpl(webResourcesPath);
    }

    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accountNum = getKeyValueFromBody(request, "account_num");

            AccountRealName accountRealName = realNameService.getRealName(accountNum);

            request.setAttribute("accountRealName", accountRealName);

            return getSucceedViewPath();
        } catch (AccountNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return getErrorViewPath();
        } catch (BadRequestException ex) {
            request.setAttribute("error", createErrorResponse("A0003", "요청전문 포맷 에러"));

            return getErrorViewPath();
        }
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public String getSucceedViewPath() {
        return "realName/showAccountRealName.jsp";
    }
}