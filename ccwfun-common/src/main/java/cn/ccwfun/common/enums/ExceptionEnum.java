package cn.ccwfun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @program: ccwfun-cloud
 * @description: 运行时异常返回编码和信息定义
 * @author: Along
 * @create: 2019-04-03 15:27
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    ACCESS_TOKEN_CANNOT_BE_NULL(-100,"请登录！"),
    ACCESS_TOKEN_ERROR(-101,"用户状态异常，请登录"),
    USER_NAME_CANNOT_BE_NULL(400,"用户名不能为空！"),
    USER_DATA_CHECK_ERROR(400,"数据校验异常"),
    USER_VERIFY_CODE_ERROR(1004,"验证码错误"),
    USER_ACCOUNTID_PASSWORD_ERROR(1005,"数据校验异常")
    ;


    private int code;

    private String msg;
}
