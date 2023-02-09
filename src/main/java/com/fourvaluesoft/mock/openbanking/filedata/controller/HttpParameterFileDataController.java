package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.controller.FileDataController;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.fourvaluesoft.mock.openbanking.filedata.service.impl.FileDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpParameterFileDataController extends FileDataController {

    private final String webResourcesPath;
    private final String requestUri;
    private final String keyName;

    private final FileDataService fileDataService;

    public HttpParameterFileDataController(String webResourcesPath, String requestUri, String keyName) {
        this.webResourcesPath = webResourcesPath;
        this.requestUri = requestUri;
        this.keyName = keyName;
        this.fileDataService = new FileDataServiceImpl(webResourcesPath);
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getSucceedViewPath() {
        return null;
    }
}
