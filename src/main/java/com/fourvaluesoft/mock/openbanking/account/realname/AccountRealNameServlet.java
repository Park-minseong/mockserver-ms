package com.fourvaluesoft.mock.openbanking.account.realname;

import com.fourvaluesoft.mock.openbanking.account.common.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.fourvaluesoft.mock.openbanking.account.realname.service.impl.AccountRealNameServiceImpl;
import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AccountRealNameServlet", value = "/account/realname")
public class AccountRealNameServlet extends HttpServlet {

    private final String PAGES_PATH = "/WEB-INF/pages/";

    private AccountRealNameService realNameService;

    @Override
    public void init() throws ServletException {
        realNameService = new AccountRealNameServiceImpl(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accountNum = getKeyValueFromBody(request);

            AccountRealName accountRealName = realNameService.getRealName(accountNum);

            request.setAttribute("accountRealName", accountRealName);

            forwardToView(request, response);
        } catch (AccountNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터가 없습니다."));

            forwardToError(request, response);
        } catch (BadRequestException ex) {
            request.setAttribute("error", createErrorResponse("A0003", "요청전문 포맷 에러"));

            forwardToError(request, response);
        }
    }

    private String getKeyValueFromBody(HttpServletRequest request) throws IOException, BadRequestException {
        try (InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8)) {
            JsonElement accountNumJson = new Gson().fromJson(reader, JsonObject.class).get("account_num");

            return accountNumJson.getAsString();
        } catch (NullPointerException | JsonSyntaxException ex) {
            throw createBadRequestException();
        }
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGES_PATH + "realName/showAccountRealName.jsp").forward(request, response);
    }

    private void forwardToError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGES_PATH + "common/error.jsp").forward(request, response);
    }

    private BadRequestException createBadRequestException() {
        return new BadRequestException("Invalid Request Body");
    }

    private ErrorResponse createErrorResponse(String rspCode, String rspMessage) {
        return new ErrorResponse(rspCode, rspMessage);
    }
}
