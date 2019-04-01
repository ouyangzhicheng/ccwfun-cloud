package cn.ccwfun.account.service.impl;

import cn.ccwfun.account.mapper.UserRegisterMapper;
import cn.ccwfun.account.pojo.UserRegister;
import cn.ccwfun.account.service.UserService;
import cn.ccwfun.common.enums.ExceptionEnum;
import cn.ccwfun.common.exception.CfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    public UserRegister addUser(UserRegister user){
        if ("ccwfun".equals(user.getUserName())&&"123456".equals(user.getPassword())){
            user.setUserRegisterId((long)new Random().nextInt(100000));
            return user;
        }
        return null;
    }

    @Override
    public Boolean checkData(Integer type, String data) {
        UserRegister userRegister=new UserRegister();
        switch (type){
            case 1:
                userRegister.setUserName(data);
                break;
            case 2:
                userRegister.setAccountId(data);
                break;
            default:
                throw new CfException(ExceptionEnum.USER_DATA_CHECK_ERROR);
        }
        return userRegisterMapper.selectCount(userRegister)==0;
    }
}
