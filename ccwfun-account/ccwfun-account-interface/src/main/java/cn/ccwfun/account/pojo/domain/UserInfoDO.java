package cn.ccwfun.account.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: ccwfun-cloud
 * @description: 用户信息表
 * @author: Along
 * @create: 2019-04-03 13:35
 **/
@Data
@Table(name="user_info")
public class UserInfoDO {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="user_info_id")
    private Long userInfoId;

    @Column(name="user_register_id")
    private Long userRegisterId;

    @Column(name="ture_name")
    private String tureName;

    @Column(name="nick_name")
    private String nickName;

    @Column(name="age")
    private Integer age;

    @Column(name="sex")
    private Integer sex;

    @Column(name="icon_url")
    private String iconUrl;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="phone")
    private Long  phone;

    @Column(name="email")
    private String email;

    @JsonIgnore
    @Column(name="status")
    private Integer status;

    @Column(name="create_time")
    private Date createTime;


}
