package com.wolfhack.csvrest.MultipartFileUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class MultipartCSVUtils implements MultipartFileUploadUtils, MultipartFileParseUtils {

    public Map<String, String[]> parseFile(MultipartFile file) throws IOException {
        Map<String, String[]> parsedMap = new HashMap<>();

        String[] fileContent = new String(file.getBytes(), StandardCharsets.UTF_8).split("\\R", 2);

        String[] headings = fileContent[0].split(";");
        String[] rows = fileContent[1].replaceAll("\r\n", ";").split(";");

        for (int i = 0; i < headings.length; i++) {
            String[] rowAdapterList = new String[rows.length / headings.length];

            for (int j = i; j < rows.length; j += headings.length) {
                rowAdapterList[j / headings.length] = rows[j];
            }

            parsedMap.put(headings[i], rowAdapterList);
        }

        return parsedMap;
    }

    public boolean saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return false;
        }

        Path uploadPath = Paths.get("files/");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
