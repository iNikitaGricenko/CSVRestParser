package com.wolfhack.csvrest.MultipartFileUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface MultipartFileParseUtils {

    Map<String, String[]> parseFile(MultipartFile file) throws IOException;

}
