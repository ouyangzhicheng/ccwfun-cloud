package cn.ccwfun.common.advice;

import cn.ccwfun.common.exception.CfRuntimeException;
import cn.ccwfun.common.vomain.ExceptionResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @program: ccwfun-cloud
 * @description: 全局异常处理
 * @author: Along
 * @create: 2019-04-03 15:27
 **/
@ControllerAdvice
public class CommonExceptionHandler{

    /**
     * 拦截捕捉全局运行时的异常 MethodArgumentNotValidException.class
     * @param cfRuntimeException
     * @return
     */
    @ExceptionHandler(CfRuntimeException.class)
    public ResponseEntity<ExceptionResultVO> handleRuntimeException(CfRuntimeException cfRuntimeException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ExceptionResultVO(cfRuntimeException.getExceptionEnum()));
    }

    /**
     * 拦截捕捉全局请求参数异常 MethodArgumentNotValidException.class
     * @param exception
     * @return
     */
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResultVO> MethodArgumentNotValidHandler(MethodArgumentNotValidException exception)
    {
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息，将list拼成一个字符串
        String field=exception.getBindingResult().getFieldErrors().stream().map(e->e.getField()).collect(Collectors.joining("|"));
        String message=exception.getBindingResult().getFieldErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.joining("|"));
        String resultMsg="【"+field+"】"+message;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ExceptionResultVO(HttpStatus.BAD_REQUEST.value(),resultMsg));
    }
}
