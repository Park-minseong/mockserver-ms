package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.controller.FileDataController;

import javax.servlet.http.HttpServletRequest;

public class HttpParameterFileDataController extends FileDataController {

    public HttpParameterFileDataController(String webResourcesPath, String requestUri, String keyName) {
        super(webResourcesPath, "GET", requestUri, keyName);
    }

    protected String getFilename(HttpServletRequest request, String keyName) {
        return getWebResourcesPath() + getDataPath() + getRequestUri() + getKeyValueFromParameter(request, keyName) + ".json";
    }
}