package com.zonli.zslb.common.model;

import com.zonli.zslb.common.constants.ResponseConstants;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResponse {
    private String status;
    private String message;
    private Map<String, Object> data;
    private List<FieldError> errors;

    public ValidationResponse() {
    }

    public boolean success(){
        return status.equals(ResponseConstants.SUCCESS);
    }

    public void addData(String name, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(name, value);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
