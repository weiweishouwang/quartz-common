package com.zhw.quartz.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zhw.quartz.properties.QuartzDataSourceProperties;
import com.zhw.quartz.properties.TaskProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Hikari DataSource configuration.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(DataSource.class)
@EnableConfigurationProperties({QuartzDataSourceProperties.class, TaskProperties.class})
public class QuartzConfig {

    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "quartz.datasource.hikari")
    @ConditionalOnClass(HikariDataSource.class)
    @ConditionalOnProperty(name = "quartz.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource")
    HikariDataSource quartzDataSource(QuartzDataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }
}
