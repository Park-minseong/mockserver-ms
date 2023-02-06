package com.fourvaluesoft.mock.openbanking.account.realname;

import com.fourvaluesoft.mock.openbanking.account.balance.domain.ErrorResponse;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.fourvaluesoft.mock.openbanking.account.realname.service.impl.AccountRealNameServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

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

    AccountRealNameService accountRealNameService;

    @Override
    public void init() throws ServletException {
        accountRealNameService = new AccountRealNameServiceImpl(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject bodyJson = getJsonFromBody(request);
        String accountNum = bodyJson.get("account_num").getAsString();

        try {
            AccountRealName accountRealName = accountRealNameService.getRealName(accountNum);
            request.setAttribute("accountRealName", accountRealName);
            System.out.println(accountRealName.getBankName());

            forwardToView(request, response);
        } catch (Exception exception) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터가 없습니다."));

            forwardToError(request, response);
        }
    }

    private JsonObject getJsonFromBody(HttpServletRequest request) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8)) {
            return new Gson().fromJson(reader, JsonObject.class);
        } catch (JsonSyntaxException ex) {
            throw new JsonSyntaxException("");
        } catch (JsonParseException ex) {
            throw new JsonParseException("");
        }
    }

    private void forwardToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/realName/pages/showAccountRealName.jsp").forward(request, response);
    }

    private void forwardToError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/public/pages/error.jsp").forward(request, response);
    }

    private ErrorResponse createErrorResponse(String rspCode, String rspMessage) {
        return new ErrorResponse(rspCode, rspMessage);
    }
}
