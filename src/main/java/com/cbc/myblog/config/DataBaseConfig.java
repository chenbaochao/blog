package com.cbc.myblog.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.GlobalConfig;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by cbc on 2017/11/10.
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,MybatisPlusProperties.class})
@AllArgsConstructor
public class DataBaseConfig {

    private final MybatisPlusProperties mybatisPlusProperties;

    private final ResourceLoader resourceLoader;

    private final DataSource dataSource;

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(){
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        if(StringUtils.hasText(this.mybatisPlusProperties.getConfigLocation())){
            mybatisSqlSessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.mybatisPlusProperties.getConfigLocation()));
        }
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisPlusProperties.getConfiguration());
        mybatisSqlSessionFactoryBean.setPlugins(getInterceptors());
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setIdType(2);
        globalConfig.setDbColumnUnderline(true);
        globalConfig.setSqlInjector(new LogicSqlInjector());
        globalConfig.setLogicDeleteValue("1");
        globalConfig.setLogicNotDeleteValue("0");
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        if(StringUtils.hasLength(mybatisPlusProperties.getTypeAliasesPackage())){
            mybatisSqlSessionFactoryBean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        }
        if(!ObjectUtils.isEmpty(mybatisPlusProperties.resolveMapperLocations())){
            mybatisSqlSessionFactoryBean.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());
        }
        if(!ObjectUtils.isEmpty(mybatisPlusProperties.getTypeHandlersPackage())){
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage(mybatisPlusProperties.getTypeHandlersPackage());
        }
        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public Interceptor[] getInterceptors(){
        List<Interceptor> interceptors = new ArrayList<Interceptor>();
        Interceptor performanceInterceptor = new PerformanceInterceptor();
        OptimisticLockerInterceptor optimisticLockerInterceptor = new OptimisticLockerInterceptor();
        Properties performanceInterceptorProps = new Properties();
        performanceInterceptorProps.setProperty("maxTime", "100");
        performanceInterceptorProps.setProperty("format", "true");
        performanceInterceptor.setProperties(performanceInterceptorProps);
        PaginationInterceptor pagination = new PaginationInterceptor();
        pagination.setDialectType("mysql");
        interceptors.add(pagination);
        interceptors.add(performanceInterceptor);
        interceptors.add(optimisticLockerInterceptor);
        return interceptors.toArray(new Interceptor[]{});
    }
}
