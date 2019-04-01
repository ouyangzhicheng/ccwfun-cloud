package cn.ccwfun.account.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="tb_user_register")
public class UserRegister {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="user_register_id")
    private Long userRegisterId;

    @Column(name="account_id")
    private String accountId;

    @JsonIgnore
    @Column(name="pass_word")
    private String password;

    @Column(name="user_number")
    private Long userNumber;

    @Column(name="user_name")
    private String userName;

    @Column(name="register_time")
    private Date registerTime;

    @JsonIgnore
    @Column(name="salt")
    private String salt;
}
