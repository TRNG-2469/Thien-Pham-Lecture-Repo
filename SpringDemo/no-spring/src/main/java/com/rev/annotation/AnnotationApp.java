package com.rev.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        MusicCoach musicCoach = context.getBean("musicCoach", MusicCoach.class);

        System.out.println(musicCoach.getDailyMusic());
        System.out.println(musicCoach.getDailySymphony());

    }
}
