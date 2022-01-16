package com.wolfhack.csvrest.MultipartFileUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MultipartFileUploadUtils {

    boolean saveFile(MultipartFile file) throws IOException;

}
