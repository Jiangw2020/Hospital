package jw.hosptial.common.exception;

import jw.hosptial.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(HosptialException.class)
    @ResponseBody
    public Result error(HosptialException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
