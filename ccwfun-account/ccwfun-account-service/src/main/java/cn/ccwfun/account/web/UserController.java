package cn.ccwfun.account.web;

import cn.ccwfun.account.pojo.UserRegister;
import cn.ccwfun.account.service.UserService;

import cn.ccwfun.common.enums.ExceptionEnum;
import cn.ccwfun.common.exception.CfException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkData/{type}/{data}")
    public ResponseEntity<Boolean> checkData(@PathVariable("type") Integer type,@PathVariable("data") String data){
        return ResponseEntity.ok(userService.checkData(type,data));
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public String test(@PathVariable("id") Integer id){
        System.out.print(id);
        return "success";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<UserRegister> saveUser(@RequestBody UserRegister user){
        if(StringUtils.isBlank(user.getUserName())){
            //全局异常处理，返回restful风格错误码
            throw new CfException(ExceptionEnum.USER_NAME_CANNOT_BE_NULL);
        }
        return ResponseEntity.ok(userService.addUser(user));
    }
}
