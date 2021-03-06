package com.xu.systemdemo.base.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
// 配置mybatis的接口类放的地方
@Configuration
@MapperScan(basePackages ="com.xu.systemdemo.*.dao", sqlSessionFactoryRef = "mySqlSessionFactory")
public class DataSourceConfigMysql {
    // 将这个对象放入Spring容器中
    @Bean(name = "mysqlDataSource")
    @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource getDateSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "mySqlSessionFactory")
    // 表示这个数据源是默认数据源
    // @Qualifier表示查找Spring容器中名字为test1DataSource的对象
    @Primary
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("mysqlDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        // 设置mybatis的xml所在位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/**/mapper/*.xml"));
        return bean.getObject();
    }
    // 表示这个数据源是默认数据源
    @Bean("mySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mySqlSessionTemplate(
            @Qualifier("mySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
