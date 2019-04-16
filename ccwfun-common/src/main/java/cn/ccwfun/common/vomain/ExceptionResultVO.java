package cn.ccwfun.common.vomain;

import cn.ccwfun.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @program: ccwfun-cloud
 * @description: 异常返回实体定义
 * @author: Along
 * @create: 2019-04-03 15:27
 **/
@Data
public class ExceptionResultVO {

    private int status;

    private String message;

    private Long timestamp;

    public ExceptionResultVO(ExceptionEnum exceptionEnum){
        status=exceptionEnum.getCode();
        message=exceptionEnum.getMsg();
        timestamp=System.currentTimeMillis();
    }

    public ExceptionResultVO(int status, String message){
        this.status=status;
        this.message=message;
        this.timestamp=System.currentTimeMillis();
    }
}
