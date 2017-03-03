package com.zonli.zslb.util;

import com.zonli.zslb.common.model.ValidationResponse;
import com.zonli.zslb.common.constants.MessageConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sh on 2017/2/16.
 */
public class UploadUtil {
    /**
     * 支持的图片后缀
     */
    private static Set<String> imageSuffixes;

    static {
        imageSuffixes = new HashSet<>();
        imageSuffixes.add("png");
        imageSuffixes.add("jpg");
    }

    /**
     * 上传文件,返回成功上传的文件名
     *
     * @param file
     * @param path
     * @return data{ filename : filename }
     * @throws IOException
     */
    public static ValidationResponse upload(MultipartFile file, String path) throws IOException {
        String message;
        if (!file.isEmpty()) {
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            if (imageSuffixes.contains(suffix)) {
                byte[] bytes = file.getBytes();
                String filename = UUID.randomUUID().toString() + "." + suffix;
                File newFile = new File(path + "/" + filename);
                FileUtils.writeByteArrayToFile(newFile, bytes);
                ValidationResponse response = ResponseUtil.successValidation();
                response.addData("filename", filename);
                return response;
            } else {
                message = MessageConstants.UPLOAD_NOT_SUPPORT;
            }
        } else {
            message = MessageConstants.UPLOAD_EMPTY;
        }
        return ResponseUtil.failValidation(message);
    }
}
