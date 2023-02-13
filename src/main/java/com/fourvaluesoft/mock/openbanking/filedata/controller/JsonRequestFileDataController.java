package com.fourvaluesoft.mock.openbanking.filedata.controller;

import com.fourvaluesoft.mock.openbanking.controller.FileDataController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JsonRequestFileDataController extends FileDataController {

    public JsonRequestFileDataController(String webResourcesPath, String requestUri, String keyName) {
        super(webResourcesPath, "POST", requestUri, keyName);
    }

    protected String getFilename(HttpServletRequest request, String keyName) throws IOException {
        return getWebResourcesPath() + getDataPath() + getRequestUri() + getKeyValueFromBody(request, keyName) + ".json";
    }
}