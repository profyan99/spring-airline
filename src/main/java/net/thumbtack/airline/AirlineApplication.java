package net.thumbtack.airline;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.InputStream;

@SpringBootApplication
@Import(Utils.class)
public class AirlineApplication {

    private static final Logger logger = LoggerFactory.getLogger(AirlineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AirlineApplication.class, args);
    }

    @Bean(name = "SimpleSqlFactory")
    public SqlSessionFactory mysqlSessionFactory() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Bean(name = "SimpleSqlSession")
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(mysqlSessionFactory());
    }
}
