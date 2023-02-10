package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.controller.FileDataController;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.fourvaluesoft.mock.openbanking.filedata.service.impl.FileDataServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpParameterFileDataController extends FileDataController {

    private final String requestUri;
    private final String keyName;

    private final FileDataService fileDataService;

    public HttpParameterFileDataController(String webResourcesPath, String requestUri, String keyName) {
        this.requestUri = requestUri;
        this.keyName = keyName;
        this.method = "GET";

        this.fileDataService = new FileDataServiceImpl(webResourcesPath);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyValue = getKeyValue(request, keyName);
        String filename = keyValue + ".json";

        try {
            JsonObject resultJson = fileDataService.loadData(filename, requestUri);

            request.setAttribute("data", resultJson);

            return SUCCEED_VIEW;
        } catch (AccountNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return ERROR_VIEW;
        }
    }
}
