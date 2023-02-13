package com.fourvaluesoft.mock.openbanking.filedata.service;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
import com.google.gson.JsonObject;

import java.io.IOException;

public interface FileDataService {

    JsonObject loadData(String filename) throws IOException, DataNotFoundException;
}
