package ru.exercise.webserver.common.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;
import javax.naming.NamingException;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource("classpath:dao.properties")
@EnableTransactionManagement
@EnableJpaRepositories("ru.exercise.webserver.da")
public class JpaConfiguration {

    private static final String PERSISTENCE_PACKAGES_SCAN = "ru.exercise.webserver.da";

    private static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL_PROPERTY = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO_PROPERTY = "hibernate.hbm2ddl.auto";

    private static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";

    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String JDBC_DATABASE_NAME = "jdbc.database.name";
    private static final String JDBC_HOST = "jdbc.host";
    private static final String JDBC_PORT = "jdbc.port";
    private static final String JDBC_URL = "jdbc.url";

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaConfiguration.class);

    @Autowired
    private Environment environment;

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

        return transactionManager;
    }

    @Bean
    public HikariConfig mysqlHikariConfig() throws NamingException {
        HikariConfig config = new HikariConfig();
        config.setPoolName("mysqlConnectionPool");
        config.setConnectionTestQuery("select 1");
        config.setDriverClassName(environment.getRequiredProperty(JDBC_DRIVER_CLASS_NAME));
        //config.setDataSourceClassName("mysqlDataSource");
        config.setUsername(environment.getRequiredProperty(JDBC_USERNAME));
        config.setPassword(environment.getRequiredProperty(JDBC_PASSWORD));
        if (environment.getRequiredProperty(JDBC_URL) != null && !"".equals(environment.getRequiredProperty(JDBC_URL).trim())) {
            config.setJdbcUrl(environment.getRequiredProperty(JDBC_URL));
        }
        //config.addDataSourceProperty("characterEncoding","utf8");
        config.addDataSourceProperty("encoding", "UTF-8");
        config.addDataSourceProperty("useUnicode", "true");
        return config;
    }

    @Bean(name = "mysqlDataSource")
    public HikariDataSource mysqlDataSource(@Qualifier("mysqlHikariConfig") final HikariConfig hikariConfig) {
        final HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("mysqlDataSource") final HikariDataSource mysqlDataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(mysqlDataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(PERSISTENCE_PACKAGES_SCAN);

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        final Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT_PROPERTY, environment.getRequiredProperty(HIBERNATE_DIALECT_PROPERTY));
        properties.put(HIBERNATE_SHOW_SQL_PROPERTY, environment.getRequiredProperty(HIBERNATE_SHOW_SQL_PROPERTY));
        properties.put(HIBERNATE_FORMAT_SQL_PROPERTY, environment.getRequiredProperty(HIBERNATE_FORMAT_SQL_PROPERTY));
        properties.put(HIBERNATE_HBM2DDL_AUTO_PROPERTY, environment.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO_PROPERTY));

        return properties;
    }
}
