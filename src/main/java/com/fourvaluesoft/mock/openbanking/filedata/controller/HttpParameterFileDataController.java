package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.fourvaluesoft.mock.openbanking.controller.FileDataController;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.fourvaluesoft.mock.openbanking.filedata.service.impl.FileDataServiceImpl;
import com.google.gson.JsonObject;

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
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyValue = getKeyValue(request, keyName);
        String filename = getFilename(keyValue);

        try {
            JsonObject resultJson = fileDataService.loadData(filename, requestUri);

            request.setAttribute("data", resultJson);

            return SUCCEED_VIEW;
        } catch (DataNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return ERROR_VIEW;
        }
    }

    private String getFilename(String keyValue) {
        return keyValue + ".json";
    }
}