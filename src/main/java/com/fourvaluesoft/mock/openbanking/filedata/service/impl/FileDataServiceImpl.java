package com.fourvaluesoft.mock.openbanking.filedata.service.impl;

import com.fourvaluesoft.mock.openbanking.exception.DataNotFoundException;
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

    @Override
    public JsonObject loadData(String filename) throws DataNotFoundException {

        try {
            return loadFromFile(filename);
        } catch (IOException | JsonIOException | JsonSyntaxException ex) {
            throw createDataNotFoundException(filename);
        }
    }

    private static JsonObject loadFromFile(String dataPath) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(dataPath)), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();

            return gson.fromJson(reader, JsonObject.class);
        }
    }

    private DataNotFoundException createDataNotFoundException(String filename) {
        return new DataNotFoundException("Invalid filename: " + filename);
    }
}
