package com.fourvaluesoft.mock.openbanking.controller;

import com.fourvaluesoft.mock.openbanking.account.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.common.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class AccountController implements Controller{

    protected String getErrorViewPath() {
        return "common/error.jsp";
    }

    protected ErrorResponse createErrorResponse(String rspCode, String rspMessage) {
        return new ErrorResponse(rspCode, rspMessage);
    }

    protected BadRequestException createBadRequestException(String message) {
        return new BadRequestException(message);
    }

    protected String getKeyValueFromBody(HttpServletRequest request, String keyName) throws IOException, BadRequestException {
        try (InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8)) {
            JsonElement accountNumJson = new Gson().fromJson(reader, JsonObject.class).get(keyName);

            return accountNumJson.getAsString();
        } catch (NullPointerException | JsonSyntaxException ex) {
            throw createBadRequestException("Invalid request body");
        }
    }

    protected String getKeyValue(HttpServletRequest request, String keyName) {
        return request.getParameter(keyName);
    }
}
