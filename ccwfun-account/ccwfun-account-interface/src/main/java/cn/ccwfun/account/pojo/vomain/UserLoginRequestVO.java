package cn.ccwfun.account.pojo.vomain;

import cn.ccwfun.common.vomain.BaseRequestVO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ccwfun-cloud
 * @description: 用户登录参数接收
 * @author: Along
 * @create: 2019-04-04 10:33
 **/
@Data
public class UserLoginRequestVO extends BaseRequestVO{

    @NotBlank(message = "账号不能为空")
    private String accountId;

    @NotBlank(message = "密码不能为空")
    private String password;
}
