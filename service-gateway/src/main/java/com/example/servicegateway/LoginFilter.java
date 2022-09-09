package com.example.servicegateway;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.common.utils.JWTUtils;
import com.example.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoginFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入到全局过滤器了");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        if(path.contains("/user")){
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("token");
        try {
            JWTUtils.verify(token);
            return chain.filter(exchange);
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            return getMono(response, "Token已经过期");
        } catch (SignatureVerificationException e){
            e.printStackTrace();
            return getMono(response, "签名错误");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            return getMono(response, "加密算法不匹配");
        } catch (Exception e) {
            e.printStackTrace();
            return getMono(response,"无效token");
        }
    }

    public Mono<Void> getMono(ServerHttpResponse response,String msg){
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        DataBuffer wrap = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        return response.writeWith(Flux.just(wrap));

    }

    @Override
    public int getOrder() {
        return -1;
    }
}
