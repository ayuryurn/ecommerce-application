package com.ecommerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.app.cache.Cache;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.validation.Validator;
import redis.clients.jedis.Jedis;
import com.ecommerce.app.cache.RedisCache;


@SpringBootApplication
public class EcommerceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}
	
	

	@Bean
	CommandLineRunner init(com.ecommerce.app.storage.StorageService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
		};
	}

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public Validator productValidator(){
	    return new com.ecommerce.app.validators.ProductValidator();
    }
    @Bean
    public Validator groupValidator(){
        return new com.ecommerce.app.validators.GroupValidator();
    }
    @Bean
    public Validator orderValidator(){
        return new com.ecommerce.app.validators.OrderValidator();
    }

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;
//    @Value("${redis.password}")
//    private String redisPassword;
//    @Bean
    private Jedis redisCliFactory(){
        Jedis jedis = new Jedis(redisHost, redisPort);
        //jedis.auth(redisPassword);
        return jedis;
    }

    @Bean
    @Autowired
    public Cache cacheObject(ObjectMapper objectMapper){
        return new RedisCache(objectMapper, redisCliFactory());
    }

}
