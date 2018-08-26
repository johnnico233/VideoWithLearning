package com.yosoro.video.domain.result;

public class Result {
    private ResultCode code;
    private String text;

    public Result() {
    }

    public Result(ResultCode code, String text) {
        this.code = code;
        this.text = text;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
