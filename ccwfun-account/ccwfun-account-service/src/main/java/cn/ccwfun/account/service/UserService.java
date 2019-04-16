package cn.ccwfun.account.service;

import cn.ccwfun.account.pojo.domain.UserRegisterDO;
import cn.ccwfun.account.pojo.vomain.UserLoginRequestVO;
import cn.ccwfun.account.pojo.vomain.UserRegisterRequestVO;

import javax.validation.Valid;


public interface UserService {

    UserRegisterDO addUser(UserRegisterDO user);

    Boolean checkData(Integer type, String data);

    void sendVerifyCode(String sourceAccount);

    void register(@Valid UserRegisterRequestVO userRegisterRequestVO);

    void login(@Valid UserLoginRequestVO userLoginRequestVO);
}
