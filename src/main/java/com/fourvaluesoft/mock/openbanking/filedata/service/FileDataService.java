package com.fourvaluesoft.mock.openbanking.filedata.service;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.google.gson.JsonObject;

import java.io.IOException;

public interface FileDataService {

    JsonObject loadData(String filename, String requestUri) throws IOException, AccountNotFoundException;
}
