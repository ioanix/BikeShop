package ro.ubb.lab6.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.ubb.lab6.client.ui.Console;


@Configuration
public class ClientConfig {

    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    Console console() {

        return new Console();
    }
}
