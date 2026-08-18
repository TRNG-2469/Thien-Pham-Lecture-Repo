package com.rev.sp.configjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    // <bean id = "cricketCoach" class = "com.rev.sp.configjava.CricketCoach"/>

    // in a config file we create a public method to return me an instance of cricket coach
    // need to annotate it to make sure spring see it as a bean
    @Bean
    // create and register the Class CricketCoach, the id cricketCoach (Method name)
    public CricketFortune cricketFortune(){
        return new CricketFortune();
    }

    @Bean
    public CricketCoach cricketCoach(){
        return new CricketCoach(cricketFortune());

    }
}
