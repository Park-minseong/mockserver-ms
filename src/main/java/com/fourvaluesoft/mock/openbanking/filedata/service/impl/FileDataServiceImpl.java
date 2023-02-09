package com.fourvaluesoft.mock.openbanking.filedata.service.impl;

import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.google.gson.JsonObject;

public class FileDataServiceImpl implements FileDataService {

    private final String webResourcesPath;

    public FileDataServiceImpl(String webResourcesPath) {
        this.webResourcesPath = webResourcesPath;
    }

    @Override
    public JsonObject loadData(String filename) {
        return null;
    }
}
