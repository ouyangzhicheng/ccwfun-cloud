package cn.ccwfun.account.service;

import cn.ccwfun.account.pojo.UserRegister;


public interface UserService {

    UserRegister addUser(UserRegister user);

    Boolean checkData(Integer type, String data);
}
