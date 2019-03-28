package cn.ccwfun.common.exception;

import cn.ccwfun.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CfException extends RuntimeException{

    private ExceptionEnum exceptionEnum;
}
