package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.fourvaluesoft.mock.openbanking.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.controller.FileDataController;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.fourvaluesoft.mock.openbanking.filedata.service.impl.FileDataServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonRequestFileDataController extends FileDataController {

    private final String requestUri;
    private final String keyName;

    private final FileDataService fileDataService;

    public JsonRequestFileDataController(String webResourcesPath, String requestUri, String keyName) {
        this.requestUri = requestUri;
        this.keyName = keyName;
        this.method = "POST";

        this.fileDataService = new FileDataServiceImpl(webResourcesPath);
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String filename = getFilename(request, keyName);

            JsonObject resultJson = fileDataService.loadData(filename, requestUri);

            request.setAttribute("data", resultJson);

            return SUCCEED_VIEW;
        } catch (BadRequestException ex) {
            request.setAttribute("error", createErrorResponse("A0003", "요청전문 포맷 에러"));

            return ERROR_VIEW;

        } catch (DataNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return ERROR_VIEW;
        }
    }

    private String getFilename(HttpServletRequest request, String keyName) throws IOException {
        return getKeyValueFromBody(request, keyName) + ".json";
    }
}