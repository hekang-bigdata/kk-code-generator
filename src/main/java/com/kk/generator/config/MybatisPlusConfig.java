package com.kk.generator.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;

/**
 * @ProjectName: hyspgxptkf
 * @Package: com.vdata.cloud.hyspgxptkf.config
 * @ClassName: MybatisPlusConfig
 * @Author: HK
 * @Description:
 * @Date: 2023/4/3 17:54
 * @Version: 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 注册分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 注册Druid数据源监控Servlet
     */
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        registration.addInitParameter("allow", "127.0.0.1");
        registration.addInitParameter("deny", "");
        registration.addInitParameter("loginUsername", "admin");
        registration.addInitParameter("loginPassword", "admin");
        return registration;
    }

    /**
     * 注册Druid数据源监控Filter
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new WebStatFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registration;
    }


    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        performanceInterceptor.setMaxTime(1000); // SQL执行最大时间，超过此时间警告
        return performanceInterceptor;
    }
}
