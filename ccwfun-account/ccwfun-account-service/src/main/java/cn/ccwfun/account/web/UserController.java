package cn.ccwfun.account.web;

import cn.ccwfun.account.pojo.domain.UserRegisterDO;
import cn.ccwfun.account.pojo.vomain.UserLoginRequestVO;
import cn.ccwfun.account.pojo.vomain.UserRegisterRequestVO;
import cn.ccwfun.account.service.UserService;

import cn.ccwfun.common.enums.ExceptionEnum;
import cn.ccwfun.common.exception.CfRuntimeException;
import cn.ccwfun.common.vomain.BaseResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkData/{type}/{data}")
    public ResponseEntity<Boolean> checkData(@PathVariable("type") Integer type,@PathVariable("data") String data){
        return ResponseEntity.ok(userService.checkData(type,data));
    }

    @PostMapping("/sendVerifyCode")
    public ResponseEntity<BaseResponseVO> sendVerifyCode(@RequestParam String sourceAccount){
        userService.sendVerifyCode(sourceAccount);
        return ResponseEntity.ok(new BaseResponseVO());
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponseVO> register(@Valid @RequestBody UserRegisterRequestVO userRegisterRequestVO){
        userService.register(userRegisterRequestVO);
        return ResponseEntity.ok(new BaseResponseVO(userRegisterRequestVO.getSeq()));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseVO> login(@Valid @RequestBody UserLoginRequestVO userLoginRequestVO){
        userService.login(userLoginRequestVO);
        return ResponseEntity.ok(new BaseResponseVO(userLoginRequestVO.getSeq()));
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public String test(@PathVariable("id") Integer id){
        System.out.print(id);
        return "success";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<UserRegisterDO> saveUser(@RequestBody UserRegisterDO user){
        if(StringUtils.isBlank(user.getUserName())){
            //全局异常处理，返回restful风格错误码
            throw new CfRuntimeException(ExceptionEnum.USER_NAME_CANNOT_BE_NULL);
        }
        return ResponseEntity.ok(userService.addUser(user));
    }
}
