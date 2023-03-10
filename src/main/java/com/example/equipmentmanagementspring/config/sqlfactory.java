package com.example.equipmentmanagementspring.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.jeffreyning.mybatisplus.base.MppSqlInjector;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class sqlfactory {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dateSource, MybatisPlusProperties properties, MppSqlInjector mppSqlInjector) throws Exception {
        MybatisSqlSessionFactoryBean bean=new MybatisSqlSessionFactoryBean();
        GlobalConfig globalConfig = properties.getGlobalConfig();
        globalConfig.setSqlInjector(mppSqlInjector);
        bean.setDataSource(dateSource);
        bean.setGlobalConfig(globalConfig);
        return bean.getObject();
    }
}
