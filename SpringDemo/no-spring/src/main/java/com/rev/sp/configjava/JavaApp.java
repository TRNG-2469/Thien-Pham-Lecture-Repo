package com.rev.sp.configjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class JavaApp {

    public static void main(String[] args) {
        // My configuration is not comming from a xml file it is comming from the class file
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

        CricketCoach musicCoach = context.getBean("music", CricketCoach.class);
        System.out.println(musicCoach.getDailyWorkout());
        System.out.println(musicCoach.getDailyFortune());
    }
}

// when your application grow, xml config become very verbose
