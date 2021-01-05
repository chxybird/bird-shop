package com.bird.filter;

import com.bird.Utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author lipu
 * @Date 2021/1/5 12:45
 * @Description 认证拦截器
 */
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取token
        String token = request.getHeaders().getFirst(JwtUtils.HEADER_FLAG);
        if (StringUtils.isEmpty(token)){
            //设置没有权限状态码  401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        }else {
            try{
                //校验token
                JwtUtils.parseJwt(token);
                //校验成功放行 并将token设置到请求头中
                request.mutate().header(JwtUtils.HEADER_FLAG,token);
                return chain.filter(exchange);
            }catch (Exception e){
                //校验失败
                //设置没有权限状态码  401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //响应空数据
                return response.setComplete();
            }
        }
    }

    /**
     * @Author lipu
     * @Date 2021/1/5 12:46
     * @Description 拦截器优先级 值越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
