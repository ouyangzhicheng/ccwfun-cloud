package cn.ccwfun.account.pojo.vomain;

import cn.ccwfun.account.pojo.dtomain.UserDTO;
import cn.ccwfun.common.vomain.BaseRequestVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/**
 * @program: ccwfun-cloud
 * @description: 用户注册请求参数实体
 * @author: Along
 * @create: 2019-04-03 15:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestVO extends BaseRequestVO {

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**用户信息DTO*/
    private UserDTO userDTO;
}
