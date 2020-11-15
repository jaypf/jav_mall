package org.mall.pay.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName EhcacheConfig
 * @Description TODO
 * @Author Jay
 * @Date 2020/11/8 1:39
 * @Version 1.0
 */
@Configuration
public class EhcacheConfig {

    @Primary
    @Bean(name = "ehCacheManager")
    public CacheManager cacheManager(EhCacheManagerFactoryBean bean) {
        CacheManager cm = bean.getObject();
        return cm;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        // 设置完属性后，cacheManagerFactoryBean会执行afterProertiesSet()方法，
        // 所以不能在这里直接执行cacheManagerFactoryBean.getObject(),直接执行的话，因为在afterPropertiesSet()方法之前执行，所以：getObject()会得到null值
        return cacheManagerFactoryBean;
    }
}
