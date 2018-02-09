package com.guguangming.forwarder.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty({"forwarder.mysql.url"})
@ComponentScan(basePackages = {"com.guguangming.forwarder.dao", "com.guguangming.forwarder.aspect"})
public class DaoConfig {
    private Map<String, Object> initMap;
    @Value("${mysql.user}")
    private String user;
    @Value("${mysql.pass}")
    private String pass;
    @Value("${mysql.max.active:10}")
    private String maxActive;
    @Value("${forwarder.mysql.url}")
    private String forwarderUrl;

    @PostConstruct
    public void init() {
        initMap = new HashMap<>(16);
        initMap.put("driverClassName", "com.mysql.jdbc.Driver");
        initMap.put("initialSize", "1");
        initMap.put("minIdle", "1");
        initMap.put("maxWait", "20000");
        initMap.put("removeAbandoned", "true");
        initMap.put("removeAbandonedTimeout", "180");
        initMap.put("timeBetweenEvictionRunsMillis", "60000");
        initMap.put("minEvictableIdleTimeMillis", "300000");
        initMap.put("validationQuery", "SELECT 1");
        initMap.put("testWhileIdle", "true");
        initMap.put("testOnBorrow", "false");
        initMap.put("testOnReturn", "false");
        initMap.put("poolPreparedStatements", "true");
        initMap.put("maxPoolPreparedStatementPerConnectionSize", "50");
        initMap.put("initConnectionSqls", "SELECT 1");
        initMap.put("maxActive", maxActive + "");
    }

    @Primary
    @Bean(name = "dsForwarder")
    public DataSource dsForwarder() {
        log.info("初始化forwarder数据源");
        try {
            return DruidDataSourceFactory.createDataSource(dbProps(forwarderUrl));
        } catch (Exception e) {
            log.error("无法获得数据源[{}]:{}", forwarderUrl, ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("无法获得数据源");
        }
    }

    @Bean(name = "templateForwarder")
    public JdbcTemplate templateForwarder() {
        return new JdbcTemplate(this.dsForwarder());
    }

    @Bean(name = "namedTemplateForwarder")
    public NamedParameterJdbcTemplate namedTemplateForwarder() {
        return new NamedParameterJdbcTemplate(this.dsForwarder());
    }

    @Bean(name = "tmForwarder")
    public DataSourceTransactionManager tsForwarder() {
        return new DataSourceTransactionManager(this.dsForwarder());
    }

    private Map<String, Object> dbProps(String url) {
        Map<String, Object> dbProperties = new HashMap<>(initMap);
        dbProperties.put("url", url);
        dbProperties.put("username", user);
        if (StringUtils.isNotBlank(pass)) {
            dbProperties.put("password", pass);
        }
        return dbProperties;
    }
}