package com.waoqi.common.utils;

public class Result<T> {

    private int retcode = 0;
    private String msg = "操作成功";
    private T data;

    public Result() {
    }

    public boolean isSuccess(){
        return this.retcode == 0;
    }
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
    public static <T> Result<T> success() {
        return new Result(CodeMsg.SUCCESS);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }
    public static <T> Result<T> error(CodeMsg codeMsg,String msg) {
        codeMsg.setMsg(msg);
        return new Result<T>(codeMsg);
    }

    public Result addData(T data){
        this.data = data;
        return this;
    }

    public static Result errors(CodeMsg codeMsg){
        Result result = new Result();
        result.setMsg(codeMsg.getMsg());
        result.setRetcode(codeMsg.getCode());
        return result;
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.retcode = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
