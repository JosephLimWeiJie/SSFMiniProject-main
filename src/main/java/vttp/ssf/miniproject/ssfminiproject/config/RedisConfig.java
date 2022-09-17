package vttp.ssf.miniproject.ssfminiproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;


@Configuration
@EnableCaching
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = loadConfig();

        final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();

        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisStandaloneConfiguration loadConfig() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(this.redisHost);
        config.setPort(this.redisPort);
        config.setPassword(this.redisPassword);
        return config;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer(getClass().getClassLoader()));
        template.setValueSerializer(new JdkSerializationRedisSerializer(getClass().getClassLoader()));

        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        template.setDefaultSerializer(serializer);

        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

}
