package cn.ccwfun.account.pojo.dtomain;

import lombok.Data;

import java.util.Date;

/**
 * @program: ccwfun-cloud
 * @description: 用户信息DTO
 * @author: Along
 * @create: 2019-04-03 15:23
 **/
@Data
public class UserDTO {

    private Long userInfoId;

    private String accountId;

    private String password;

    private String salt;

    private Long userNumber;

    private String userName;

    private String tureName;

    private String nickName;

    private Integer age;

    private Integer sex;

    private String iconUrl;

    private Date birthday;

    private Long  phone;

    private String email;
}
