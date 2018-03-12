package com.sptwin.sptserver.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = MybatisConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")// 扫描 Mapper 接口并容器管理
public class MybatisConfig {

    static final String PACKAGE = "com.sptwin.sptserver.**.mapper";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/**/*.xml";
    static final String CONFIG_LOCATION = "mybatis/mybatis-config.xml";

    @Autowired
    private DataSource dataSource;


    public PlatformTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public SqlSessionFactory masterSqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MybatisConfig.MAPPER_LOCATION));
        sessionFactory.setConfigLocation(new ClassPathResource(CONFIG_LOCATION));
        return sessionFactory.getObject();
    }

    //AOP实现事务配置
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("save*", "PROPAGATION_REQUIRED");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
        properties.setProperty("add*", "PROPAGATION_REQUIRED");
        properties.setProperty("update*", "PROPAGATION_REQUIRED");
        properties.setProperty("edit*", "PROPAGATION_REQUIRED");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
        properties.setProperty("batch*", "PROPAGATION_REQUIRED");
        properties.setProperty("find*", "PROPAGATION_SUPPORTS,readOnly");
        properties.setProperty("select*", "PROPAGATION_SUPPORTS,readOnly");
        properties.setProperty("get*", "PROPAGATION_SUPPORTS,readOnly");
        properties.setProperty("query*", "PROPAGATION_SUPPORTS,readOnly");
        TransactionInterceptor tsi = new TransactionInterceptor(masterTransactionManager(),properties);
        return tsi;
    }
    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
