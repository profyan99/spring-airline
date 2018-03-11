package net.thumbtack.airline;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.InputStream;

@SpringBootApplication
@Import(ConstantsSetting.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class AirlineApplication {
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
