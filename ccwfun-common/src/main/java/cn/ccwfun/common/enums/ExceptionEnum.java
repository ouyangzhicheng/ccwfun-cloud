package cn.ccwfun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    USER_NAME_CANNOT_BE_NULL(400,"用户名不能为空！");
    ;

    private int code;

    private String msg;
}
