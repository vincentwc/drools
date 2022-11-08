package com.vincent.template.config;

import com.vincent.template.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xu
 * @Description: http过滤器，容器生命周期及请求前执行
 * @Date: 2021/12/8 18:06
 */
@Slf4j
@Component
public class HttpFilter implements Filter {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig){
        log.info("容器初始化开始");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse responses = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String ip = HttpUtil.getIp(req);
        ip=ip.replace(":", "");
        if (redisTemplate.hasKey(ip) && Integer.parseInt(redisTemplate.opsForValue().get(ip).toString())>60){
            log.error("请求频率过高");
            responses.getWriter().write("请求频率过高");
            responses.setStatus(508);
            return;
        }
        if (!redisTemplate.hasKey(ip)){
            redisTemplate.opsForValue().set(ip, 0,60, TimeUnit.SECONDS);
        }
        redisTemplate.opsForValue().increment(ip);
        log.info( redisTemplate.opsForValue().get(ip).toString());
        //所有请求，解决跨域
        responses.setHeader("Access-Control-Allow-Origin", "*");
        //配置所有请求方式都可以请求，解决跨域
        responses.setHeader("Access-Control-Allow-Methods", "*");
        responses.setHeader("Content-type", "application/json");
//        保持连接的最大时长
        responses.setHeader("Access-Control-Max-Age", "1728000");
//        是否有资格证书
        responses.setHeader("Access-Control-Allow-Credentials", "true");
//        前端可以携带的请求头key
        responses.setHeader("Access-Control-Expose-Headers", "token,uuid,fail");
//        后端返回响应头可以暴露的key
        responses.setHeader("Access-Control-Allow-Headers", "*");
//        统一设置返回的字符编码
        responses.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("servlet容器停止");
    }
}
