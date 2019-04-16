package cn.ccwfun.zuul.filter;

import cn.ccwfun.common.enums.ExceptionEnum;
import cn.ccwfun.common.utils.JsonUtils;
import cn.ccwfun.common.vomain.ExceptionResultVO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;


/**
 *
 */
@Slf4j
@Component
public class LoginFilter extends ZuulFilter{
    @Override
    public String filterType() {
        //过滤器的类型定义为前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤顺序,选择靠前的
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        //需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求上下文
        RequestContext ctx= RequestContext.getCurrentContext();
        //设置响应字符格式
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        //获取request对象
        HttpServletRequest request=ctx.getRequest();
        //获取请求头（获取access-token）
        String accessToken=request.getHeader("access-token");
        //获取缓存里的access-token
        String redis_accessToken="123456789abc";

        if(StringUtils.isBlank(accessToken)){
            //未登录，拦截
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            ctx.setResponseBody(JsonUtils.serialize(new ExceptionResultVO(ExceptionEnum.ACCESS_TOKEN_CANNOT_BE_NULL)));
        }else {
            //校验对错
            if(!accessToken.equals(redis_accessToken)){
                ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                ctx.setResponseBody(JsonUtils.serialize(new ExceptionResultVO(ExceptionEnum.ACCESS_TOKEN_ERROR)));
            }
//            //获取Ip
//            String requestIpAddress= CusAddressUtil.getIpAddress(request);
//            //获取完整的url
//            String requestUrl=request.getRequestURI();
//            //获取完整的参数
//            String requestParameter=JsonUtils.serialize(request.getParameterMap());
//            String par=request.getParameter("userName");
//            log.info(par);
//            //记录请求（accountId:ip:url+参数）
//            log.info("15915789343:"+requestIpAddress+":"+requestUrl+":"+requestParameter);

        }
        return null;
    }
}
