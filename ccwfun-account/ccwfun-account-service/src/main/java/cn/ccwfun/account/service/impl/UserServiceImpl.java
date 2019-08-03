package cn.ccwfun.account.service.impl;

import cn.ccwfun.account.mapper.UserInfoMapper;
import cn.ccwfun.account.mapper.UserRegisterMapper;
import cn.ccwfun.account.pojo.domain.UserInfoDO;
import cn.ccwfun.account.pojo.domain.UserRegisterDO;
import cn.ccwfun.account.pojo.vomain.UserLoginRequestVO;
import cn.ccwfun.account.pojo.vomain.UserRegisterRequestVO;
import cn.ccwfun.account.service.UserService;
import cn.ccwfun.account.utils.CodecUtils;
import cn.ccwfun.common.enums.ExceptionEnum;
import cn.ccwfun.common.exception.CfRuntimeException;
import cn.ccwfun.common.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX="user:verify:sourceAccount";

    @Override
    public UserRegisterDO addUser(UserRegisterDO user){
        if ("ccwfun".equals(user.getUserName())&&"123456".equals(user.getPassword())){
            user.setUserRegisterId((long)new Random().nextInt(100000));
            return user;
        }
        return null;
    }

    @Override
    public Boolean checkData(Integer type, String data) {
        UserRegisterDO userRegister=new UserRegisterDO();
        switch (type){
            case 1:
                userRegister.setUserName(data);
                break;
            case 2:
                userRegister.setAccountId(data);
                break;
            default:
                throw new CfRuntimeException(ExceptionEnum.USER_DATA_CHECK_ERROR);
        }
        return userRegisterMapper.selectCount(userRegister)==0;
    }

    /**
     *
     * @param sourceAccount 用户
     */
    @Override
    public void sendVerifyCode(String sourceAccount) {

        //生成验证码1
        String verifyCode= NumberUtils.generateCode(6);
        //发送验证码1
        Map<String,String> msg=new HashMap<>();
        msg.put("sourceAccount",sourceAccount);
        msg.put("verifyCode",verifyCode);
        amqpTemplate.convertAndSend("cf.email.exchange","email.verify.code",msg);

        //生成缓存key
        String key=KEY_PREFIX+sourceAccount;
        //保存验证码
        stringRedisTemplate.opsForValue().set(key,verifyCode,30, TimeUnit.MINUTES);
    }

    @Override
    public void register(@Valid UserRegisterRequestVO userRegisterRequestVO) {

        //把VO参数copy到2个DO实体
        UserRegisterDO userRegisterDO=new UserRegisterDO();
        UserInfoDO userInfoDO=new UserInfoDO();
        BeanUtils.copyProperties(userRegisterRequestVO.getUserDTO(),userRegisterDO);
        BeanUtils.copyProperties(userRegisterRequestVO.getUserDTO(),userInfoDO);

        //从redis取出验证码
        String cacheVerifyCode=stringRedisTemplate.opsForValue().get(KEY_PREFIX+userRegisterDO.getAccountId());

        //校验验证码
        if(!StringUtils.equals(userRegisterRequestVO.getVerifyCode(),cacheVerifyCode)){
            throw new CfRuntimeException(ExceptionEnum.USER_VERIFY_CODE_ERROR);
        }

        //生成密码盐
        String salt= CodecUtils.generateSalt();
        userRegisterDO.setSalt(salt);
        userRegisterDO.setPassword(CodecUtils.md5Hex(userRegisterDO.getPassword(),salt));

        //写入数据库
        userRegisterDO.setRegisterTime(new Date());
        userRegisterMapper.insertSelective(userRegisterDO);


        userInfoDO.setCreateTime(new Date());
        userInfoDO.setUserRegisterId(userRegisterDO.getUserRegisterId());
        userInfoDO.setStatus(0);
        userInfoMapper.insert(userInfoDO);
    }

    @Override
    public void login(@Valid UserLoginRequestVO userLoginRequestVO) {
        //判断账号是否存在
        UserRegisterDO record=new UserRegisterDO();
        record.setAccountId(userLoginRequestVO.getAccountId());
        UserRegisterDO userRegisterDO=userRegisterMapper.selectOne(record);
        if (userRegisterDO==null){
            throw new CfRuntimeException(ExceptionEnum.USER_ACCOUNTID_PASSWORD_ERROR);
        }
        //对比密码是否正确
        String encryptPassword=CodecUtils.md5Hex(userRegisterDO.getPassword(),userRegisterDO.getSalt());
        if (!StringUtils.equals(encryptPassword,userLoginRequestVO.getPassword())) {
            throw new CfRuntimeException(ExceptionEnum.USER_ACCOUNTID_PASSWORD_ERROR);
        }
        //获取账号权限

        //生成token,并存在缓存里
    }
}
