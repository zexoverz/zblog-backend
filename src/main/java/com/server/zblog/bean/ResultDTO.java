package com.server.zblog.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ResultDTO<T> {

    private T data;
    private String message;
    private boolean isSuccess;

    public ResultDTO() {
        super();
    }

    public ResultDTO(T data, String message, boolean isSuccess) {
        super();
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public ResultDTO(String message, boolean isSuccess) {
        super();
        this.message = message;
        this.isSuccess = isSuccess;
    }
}
