package com.boot.basic.common.config;// package com.boot.basic.common.config;
//
// import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
// import com.baomidou.mybatisplus.core.injector.ISqlInjector;
// import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
// import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
// import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
// import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
// import org.apache.ibatis.reflection.MetaObject;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
//
// import java.time.LocalDateTime;
//
// /**
//  * @author HTuoZhou
//  */
// @Configuration
// @EnableTransactionManagement
// @MapperScan("com.boot.basic.mapper")
// public class OldMyBatisPlusConfig implements MetaObjectHandler{
//
//     /**分页插件*/
//     @Bean
//     public PaginationInterceptor paginationInterceptor() {
//         return new PaginationInterceptor();
//     }
//
//     /**逻辑删除插件*/
//     @Bean
//     public ISqlInjector sqlInjector(){
//         return new LogicSqlInjector();
//     }
//
//     /**乐观锁插件*/
//     @Bean
//     public OptimisticLockerInterceptor optimisticLockerInterceptor(){
//         return new OptimisticLockerInterceptor();
//     }
//
//     /**性能分析插件*/
//     @Bean
//     public PerformanceInterceptor performanceInterceptor() {
//         PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//         performanceInterceptor.setFormat(true);
//         return performanceInterceptor;
//     }
//
//     /**插入数据自动填充时间*/
//     @Override
//     public void insertFill(MetaObject metaObject) {
//         this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
//         this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
//     }
//
//     /**更新数据自动更新时间*/
//     @Override
//     public void updateFill(MetaObject metaObject) {
//         this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
//     }
// }
