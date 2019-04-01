package cn.ccwfun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    ACCESS_TOKEN_CANNOT_BE_NULL(-100,"请登录！"),
    ACCESS_TOKEN_ERROR(-101,"用户状态异常，请登录"),
    USER_NAME_CANNOT_BE_NULL(400,"用户名不能为空！"),
    USER_DATA_CHECK_ERROR(400,"数据校验异常");
    ;

    private int code;

    private String msg;
}
