package com.heinqi.curry_base.http;

import com.heinqi.curry_base.utils.JsonUtil;

import java.util.List;

public class ResultTO {
    // 结果状态
    private int status;
    // 结果
    private Object data;
    // 结果消息
    private String message;
    // 验证错误列表
    private List<ErrorTO> errorList;

    public ResultTO() {
    }

    public ResultTO(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorTO> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorTO> errorList) {
        this.errorList = errorList;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }

}
