package com.fourvaluesoft.mock.openbanking.filedata.service;

import com.google.gson.JsonObject;

public interface FileDataService {

    JsonObject loadData(String filename);
}
