//package io.daocloud.kpanda.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
//import io.seata.rm.datasource.DataSourceProxy;
//import io.seata.rm.datasource.xa.DataSourceProxyXA;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.TransactionIsolationLevel;
//import org.apache.ibatis.type.JdbcType;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//
//@Configuration
//public class XADataSourceConfiguration {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DruidDataSource druidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/kpanda?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
//        druidDataSource.setUsername("root");
//        druidDataSource.setPassword("root");
//        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        druidDataSource.setInitialSize(0);
//        druidDataSource.setMaxActive(180);
//        druidDataSource.setMaxWait(60000);
//        druidDataSource.setMinIdle(0);
//        druidDataSource.setValidationQuery("Select 1 from DUAL");
//        druidDataSource.setTestOnBorrow(false);
//        druidDataSource.setTestOnReturn(false);
//        druidDataSource.setTestWhileIdle(true);
//        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
//        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
//        druidDataSource.setRemoveAbandoned(true);
//        druidDataSource.setRemoveAbandonedTimeout(1800);
//        druidDataSource.setLogAbandoned(true);
//        return druidDataSource;
//    }
//
//    @Bean("dataSourceProxy")
//    public DataSource dataSource(DruidDataSource druidDataSource) {
//        // DataSourceProxy for AT mode
//        // return new DataSourceProxy(druidDataSource);
//
//        // DataSourceProxyXA for XA mode
//        return new DataSourceProxyXA(druidDataSource);
//    }
//
//    @Bean("jdbcTemplate")
//    public JdbcTemplate jdbcTemplate(DataSource dataSourceProxy) {
//        return new JdbcTemplate(dataSourceProxy);
//    }
//
////    @Bean
////    public PlatformTransactionManager txManager(DataSource dataSourceProxy) {
////        return new DataSourceTransactionManager(dataSourceProxy);
////    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSourceProxy) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        sqlSessionFactoryBean.setTypeAliasesPackage("");
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        sqlSessionFactoryBean.setConfiguration(configuration);
//        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
//        return sqlSessionFactoryBean.getObject();
//    }
//}
