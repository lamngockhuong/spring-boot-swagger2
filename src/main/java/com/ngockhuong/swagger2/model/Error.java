package com.ngockhuong.swagger2.model;

import io.swagger.annotations.ApiModelProperty;

public class Error {
    @ApiModelProperty(notes = "Example: 1001")
    private Integer code;

    @ApiModelProperty(notes = "Example: Logical error")
    private String type;

    @ApiModelProperty(notes = "Example: Something went wrong!")
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
