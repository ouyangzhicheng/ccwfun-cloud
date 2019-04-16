package cn.ccwfun.common.vomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ccwfun-cloud
 * @description: 响应参数基类
 * @author: Along
 * @create: 2019-04-03 21:16
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseVO implements Serializable {

    private static final long serialVersionUID = -7146494875607752321L;

    private String seq=System.currentTimeMillis()+"";

    private int code;

    private String msg="success ^_^";

    private Map<String, Object> resMap = new HashMap<String, Object>();

    public BaseResponseVO(String seq){
        this.seq=seq;
    }
}
