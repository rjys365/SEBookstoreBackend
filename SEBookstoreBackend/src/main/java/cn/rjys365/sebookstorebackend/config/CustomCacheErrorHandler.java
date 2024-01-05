package cn.rjys365.sebookstorebackend.config;

import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import java.net.ConnectException;

public class CustomCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        handleException(exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        handleException(exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        handleException(exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        handleException(exception);
    }

    private void handleException(RuntimeException exception) {
        if (exception instanceof RedisCommandTimeoutException) {
            System.out.println("Redis timed out");
        }
        else {
            // 如果是其他类型的异常，可能需要重新抛出
            System.out.println("Redis error");
//            throw exception;
        }
    }
}