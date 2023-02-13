package com.fourvaluesoft.mock.openbanking.controller;

import com.fourvaluesoft.mock.openbanking.exception.BadRequestException;
import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.fourvaluesoft.mock.openbanking.filedata.service.impl.FileDataServiceImpl;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FileDataController extends KeyValueController {

    private static final String SUCCEED_VIEW = "/common/result.jsp";

    private final String requestUri;
    private final String keyName;
    private final String webResourcesPath;
    private final String dataPath = "/WEB-INF/data";

    private final FileDataService fileDataService;

    public FileDataController(String webResourcesPath, String method, String requestUri, String keyName) {
        this.webResourcesPath = webResourcesPath;
        this.method = method;
        this.requestUri = requestUri;
        this.keyName = keyName;

        this.fileDataService = new FileDataServiceImpl();
    }

    protected String getWebResourcesPath() {
        return webResourcesPath;
    }

    protected String getDataPath() {
        return dataPath;
    }

    protected String getRequestUri() {
        return requestUri;
    }

    protected String getKeyName() {
        return keyName;
    }

    @Override
    protected String getSucceedViewPath() {
        return SUCCEED_VIEW;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String filename = getFilename(request, keyName);

            JsonObject dataJsonObject = fileDataService.loadData(filename);

            request.setAttribute("data", dataJsonObject);

            return getSucceedViewPath();
        } catch (BadRequestException ex) {
            request.setAttribute("error", createErrorResponse("A0003", "요청전문 포맷 에러"));

            return getErrorView();
        } catch (DataNotFoundException ex) {
            request.setAttribute("error", createErrorResponse("A0021", "데이터 없음"));

            return getErrorView();
        }
    }

    protected abstract String getFilename(HttpServletRequest request, String keyName) throws IOException;
}