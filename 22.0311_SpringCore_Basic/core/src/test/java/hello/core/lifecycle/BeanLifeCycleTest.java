package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //AnnotationConfigApplicationContext 타입이거나, ConfigurableApplicationContext 타입이어야 호출 가능
    }

    @Configuration
    static class LifeCycleConfig {

        //destroyMethod 의 추론 속성으로 close, shutdown 이름으로 된 메서드를 자동으로 호출해준다.
        //추론 기능을 사용하지 않으려면 destroyMethod = "" 으로 지정해주면 된다.
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean //@PostConstruct, @PreDestroy 사용을 권고한다
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
