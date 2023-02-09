package com.fourvaluesoft.mock.openbanking.account.realname;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.account.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.account.realname.domain.AccountRealName;
import com.fourvaluesoft.mock.openbanking.account.realname.service.AccountRealNameService;
import com.fourvaluesoft.mock.openbanking.account.realname.service.impl.AccountRealNameServiceImpl;
import com.fourvaluesoft.mock.openbanking.controller.Controller;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AccountRealNameController extends Controller {

    private final AccountRealNameService realNameService;

    public AccountRealNameController(String webResourcesPath) throws ServletException {
        realNameService = new AccountRealNameServiceImpl(webResourcesPath);
    }

    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String accountNum = getKeyValueFromBody(request);

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

    private String getKeyValueFromBody(HttpServletRequest request) throws IOException, BadRequestException {
        try (InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8)) {
            JsonElement accountNumJson = new Gson().fromJson(reader, JsonObject.class).get("account_num");
            
            return accountNumJson.getAsString();
        } catch (NullPointerException | JsonSyntaxException ex) {
            throw createBadRequestException("Invalid request body");
        }
    }

    @Override
    protected String getSucceedViewPath() {
        return "realName/showAccountRealName.jsp";
    }
}
