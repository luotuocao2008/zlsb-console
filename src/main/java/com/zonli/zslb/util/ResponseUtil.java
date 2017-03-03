package com.zonli.zslb.util;

import com.zonli.zslb.common.model.ValidationResponse;
import com.zonli.zslb.common.constants.MessageConstants;
import com.zonli.zslb.common.constants.ResponseConstants;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ResponseUtil {
    /**
     * 返回验证失败信息
     * @param message
     * @return
     */
    public static ValidationResponse failValidation(String message) {
        ValidationResponse response = failValidation();
        response.setMessage(message);
        return response;
    }
    public static ValidationResponse failValidation() {
        ValidationResponse response = new ValidationResponse();
        response.setStatus(ResponseConstants.FAIL);
        return response;
    }

    /**
     * 返回验证成功信息
     * @param message
     * @return
     */
    public static ValidationResponse successValidation(String message) {
        ValidationResponse response = successValidation();
        response.setMessage(message);
        return response;
    }

    public static ValidationResponse successValidation() {
        ValidationResponse response = new ValidationResponse();
        response.setStatus(ResponseConstants.SUCCESS);
        return response;
    }

    /**
     * 字段验证失败
     * @param fieldErrors
     * @return
     */
    public static ValidationResponse failFieldValidation(List<FieldError> fieldErrors) {
        ValidationResponse response = failValidation(MessageConstants.FIELD_VALIDATION_FAIL);
        response.setErrors(fieldErrors);
        return response;
    }

    public static ValidationResponse failFieldValidation(BindingResult bindingResult) {
        ValidationResponse response = failValidation(MessageConstants.FIELD_VALIDATION_FAIL);
        FieldError field = bindingResult.getFieldError();
        response.setMessage(field.getDefaultMessage());
        return response;
    }

}
