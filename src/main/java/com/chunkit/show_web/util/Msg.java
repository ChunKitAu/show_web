package com.chunkit.show_web.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Slf4j
public class Msg {

    private final static int SUCCESS_CODE = 200 ;
    private final static int FAILURE_CODE = 1000 ;
    private final static String SUCCESS_MESSAGE = "请求成功";
    private final static String FAILURE_MESSAGE = "请求成功";

    private int code ;
    private Object data;
    private String message;
    private boolean success;

    public static Msg success(Object data){
        return success().setData(data);
    }

    public static Msg success(){
        return new Msg()
                .setCode(SUCCESS_CODE)
                .setSuccess(true)
                .setMessage(SUCCESS_MESSAGE);
    }

    public static Msg failure(String message){
        return failure().setData(message);
    }

    public static Msg failure(){
        return new Msg()
                .setCode(FAILURE_CODE)
                .setSuccess(false)
                .setMessage(FAILURE_MESSAGE);

    }

    /**
     * 根据boolean自动选择返回成功/失败实例
     */
    public static Msg expect(boolean success) {
        return success ? success() : failure();
    }

    /**
     * 配合@expect（）方法使用
     */
    public Msg setSuccessMessage(String message) {
        return isSuccess() ? setMessage(message) : this;
    }

    /**
     * 配合@expect（）方法使用
     */
    public Msg setFailureMessage(String message) {
        return !isSuccess() ? setMessage(message) : this;
    }

    /**
     * 配合@expect（）方法使用
     */
    public Msg setSuccessData(Object data) {
        return isSuccess() ? setData(data) : this;
    }




}
