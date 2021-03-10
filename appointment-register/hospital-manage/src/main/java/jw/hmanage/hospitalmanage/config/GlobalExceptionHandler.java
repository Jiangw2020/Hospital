package jw.hmanage.hospitalmanage.config;

import jw.hmanage.hospitalmanage.utils.HospitalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理类
 *
 * @author qy
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String error(Exception e){
        e.printStackTrace();
        return "error";
    }

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(HospitalException.class)
    public String error(HospitalException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
