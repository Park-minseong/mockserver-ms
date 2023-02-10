package com.fourvaluesoft.mock.openbanking.filedata.service.impl;

import com.fourvaluesoft.mock.openbanking.account.exception.AccountNotFoundException;
import com.fourvaluesoft.mock.openbanking.filedata.service.FileDataService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataServiceImpl implements FileDataService {

    private final String webResourcesPath;
    private final String dataPath = "/WEB-INF/data";

    public FileDataServiceImpl(String webResourcesPath) {
        this.webResourcesPath = webResourcesPath;
    }

    @Override
    public JsonObject loadData(String filename, String requestUri) throws AccountNotFoundException {
        String dataFilePath = getDataFilePath(filename, requestUri);

        try {
            return loadFromFile(dataFilePath);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            throw createAccountNotFoundException(filename);
        }
    }

    private static JsonObject loadFromFile(String dataPath) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(dataPath)), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();

            return gson.fromJson(reader, JsonObject.class);
        }
    }

    private String getDataFilePath(String filename, String requestUri) {
        return webResourcesPath + dataPath + requestUri + filename;
    }

    private AccountNotFoundException createAccountNotFoundException(String filename) {
        return new AccountNotFoundException("Invalid filename: " + filename);
    }
}
