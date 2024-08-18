package net.sayilir.api.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author omersayilir
 */
public class ResponseUtil {
    public static <T> ApiResponse success (T data,String message,String path){
        ApiResponse<T> response=new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setPath(path);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }


    public static <T> ApiResponse<T> error(List<String> errors, int errorCode, String message, String path){
        ApiResponse<T> response=new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrors(errors);
        response.setErrorCode(errorCode);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }
    public static <T> ApiResponse<T> error(String error,int errorCode, String message, String path){
        return error(Arrays.asList(error),errorCode,message,path);
    }
}
