package cn.ccwfun.common.vomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: ccwfun-cloud
 * @description: 请求实体VO基类
 * @author: Along
 * @create: 2019-04-03 16:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestVO implements Serializable {

    private static final long serialVersionUID = -7622060107366046204L;

    @NotBlank(message = "请求标识不能为空")
    private String seq;

    @NotBlank(message = "版本号不能为空")
    private String versionNo = "1.0";

    @NotBlank(message = "客户端类型不能为空")
    private String clientType;
}
