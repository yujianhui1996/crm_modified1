package com.comma.fit.fitcrm.conf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.imadcn.framework.idworker.config.ApplicationConfiguration;
import com.imadcn.framework.idworker.config.ZookeeperConfiguration;
import com.imadcn.framework.idworker.generator.IdGenerator;
import com.imadcn.framework.idworker.generator.SnowflakeGenerator;
import com.imadcn.framework.idworker.register.zookeeper.ZookeeperWorkerRegister;
import com.imadcn.framework.idworker.registry.zookeeper.ZookeeperRegistryCenter;

/**
 * @author comma
 */
@Configuration
public class SBC {
    //引用application-dev.yml中的zk：server值注入
    @Value("${zk.server}")
    private String zkServerIp;
    private String nameSpace="id_worker";
    private String groupName="id_worker_group";

    //zookeeper配置
    @Bean
    public ZookeeperConfiguration zookeeperConfiguration() {
        ZookeeperConfiguration configuration = new ZookeeperConfiguration();
        configuration.setServerLists(zkServerIp);
        configuration.setNamespace(nameSpace);
        return configuration;
    }
    //application配置
    @Bean
    public ApplicationConfiguration applicationConfiguration() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        applicationConfiguration.setGroup(groupName);
        return applicationConfiguration;
    }
    //以zookeeper配置创建注册中心
    @Bean
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration configuration) {
        ZookeeperRegistryCenter registryCenter = new ZookeeperRegistryCenter(configuration);
        return registryCenter;
    }

    @Bean
    public ZookeeperWorkerRegister zookeeperWorkerRegister(ZookeeperRegistryCenter registryCenter, ApplicationConfiguration applicationConfiguration) {
        ZookeeperWorkerRegister workerRegister = new ZookeeperWorkerRegister(registryCenter, applicationConfiguration);
        return workerRegister;
    }

    @Bean(initMethod="init")
    public IdGenerator idGenerator(ZookeeperWorkerRegister workerRegister) {
        IdGenerator idGenerator = new SnowflakeGenerator(workerRegister);
        return idGenerator;
    }
}
