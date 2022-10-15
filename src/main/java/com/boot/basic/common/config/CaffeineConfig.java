package com.boot.basic.common.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author HTuoZhou
 */
@Configuration
@EnableCaching
@Slf4j
public class CaffeineConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数 maximumSize和maximumWeight不可同时使用
                .maximumSize(1000)
                // 缓存的最大权重
                // .maximumWeight(1000)
                // 最后一次写入或访问后经过固定时间过期 expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
                .expireAfterAccess(30, TimeUnit.SECONDS)
                // 最后一次写入后经过固定时间过期
                // .expireAfterWrite(30,TimeUnit.MINUTES)
                // 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
                .refreshAfterWrite(30, TimeUnit.SECONDS)
                // 开发统计功能
                .recordStats()
                .writer(cacheWriter());

        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheLoader(cacheLoader());
        // 是否允许值为空
        return cacheManager;
    }

    @Bean
    public CacheWriter<Object, Object> cacheWriter() {
        return new CacheWriter<Object, Object>() {
            @Override
            public void write(@NonNull Object key, @NonNull Object value) {
                log.info("缓存写入：key={} value={}", key, value);
            }

            @Override
            public void delete(@NonNull Object key, @Nullable Object value, @NonNull RemovalCause removalCause) {
                log.info("缓存删除：key={} value={} removalCause={}", key, value, removalCause);
            }
        };
    }

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<Object, Object>() {
            @Override
            public Object load(@NonNull Object key) throws Exception {
                log.info("缓存加载：key={}", key);
                return null;
            }

            @Override
            public Object reload(@NonNull Object key, @NonNull Object oldValue) throws Exception {
                log.info("缓存重新加载：key={} oldValue={}", key, oldValue);
                return null;
            }
        };
    }
}
