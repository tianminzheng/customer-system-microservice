package org.geekbang.projects.cs.gateway.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1) // 保证优先级高于默认的Spring Cloud Gateway的ErrorWebExceptionHandler实现
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 已经 commit，则直接返回异常
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // 转换成自定义Result
        Result<?> result;
        if (ex instanceof ResponseStatusException) {
            //处理网关默认抛出的ResponseStatusException异常
            result = responseStatusExceptionHandler(exchange, (ResponseStatusException) ex);
        } else {
            //处理其他任何系统异常
            result = globalExceptionHandler(exchange, ex);
        }

        // 返回异常信息
        return writeResult(exchange, result);
    }

    private Result<?> responseStatusExceptionHandler(ServerWebExchange exchange, ResponseStatusException ex) {
        ServerHttpRequest request = exchange.getRequest();
        log.error("[responseStatusExceptionHandler][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
        return Result.error(ex.getRawStatusCode(), ex.getReason());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> globalExceptionHandler(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        log.error("[globalExceptionHandler][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
        return Result.error(500, "服务网关全局异常");
    }

    public  Mono<Void> writeResult(ServerWebExchange exchange, Object object) {
        // 设置header
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 设置body
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(JSON.toJSONBytes(object));
            } catch (Exception ex) {
                ServerHttpRequest request = exchange.getRequest();
                log.error("[writeResult][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
