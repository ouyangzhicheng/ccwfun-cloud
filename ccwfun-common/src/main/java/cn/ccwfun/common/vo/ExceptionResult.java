package cn.ccwfun.common.vo;

import cn.ccwfun.common.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {

    private int status;

    private String message;

    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum){
        status=exceptionEnum.getCode();
        message=exceptionEnum.getMsg();
        timestamp=System.currentTimeMillis();
    }
}
