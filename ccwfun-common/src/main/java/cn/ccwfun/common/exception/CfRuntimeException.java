package cn.ccwfun.common.exception;

import cn.ccwfun.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @program: ccwfun-cloud
 * @description: 运行时的异常增加参数
 * @author: Along
 * @create: 2019-04-03 15:27
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CfRuntimeException extends RuntimeException{

    private ExceptionEnum exceptionEnum;
}
