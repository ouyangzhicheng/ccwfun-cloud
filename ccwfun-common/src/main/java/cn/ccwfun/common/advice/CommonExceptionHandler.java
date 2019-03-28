package cn.ccwfun.common.advice;

import cn.ccwfun.common.exception.CfException;
import cn.ccwfun.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//拦截所有的controller
@ControllerAdvice
public class CommonExceptionHandler{

    //抛运行时的异常时，进入该方法
    @ExceptionHandler(CfException.class)
    public ResponseEntity<ExceptionResult> handleException(CfException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
