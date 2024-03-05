package dev.pavan.prodser.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration


  //@Configuration: This annotation marks the class as a configuration class.
// Spring will scan this class for bean definitions and other configuration.


public class ApplicationConfiguration {

    //RestTemplate Bean: Since you're using RestTemplate to make HTTP requests to the External Services in this case (FakeStore API)
    // make sure you have a RestTemplate bean configured in your application.

@Bean

    //@Bean: This annotation is used on methods inside a @Configuration class to define a bean.
    //The method name (restTemplate in this case) is
    // the bean's name, and the return type (RestTemplate) is the type of the bean.


    public RestTemplate restTemplate(){

    return new RestTemplate();
   }
}




//RestTemplate Bean: The restTemplate method creates a new instance of RestTemplate and returns it.
// This method is annotated with @Bean, so Spring will manage this instance of RestTemplate
// and make it available for injection in other parts of your application.
//By defining RestTemplate as a bean in this way, you can easily inject it into your controllers, services, or other components
// where you need to make HTTP requests to external APIs, such as the FakeStore API in your case.