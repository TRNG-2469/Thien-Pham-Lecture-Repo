package com.rev.sp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

////        BaseBallCoach baseBallCoach = new BaseBallCoach();
////        System.out.println(baseBallCoach.getDailyWorkout());
////
////        SwimCoach swimCoach = new SwimCoach();
////        System.out.println(swimCoach.getDailyWorkout());
//
//        // create a spring container by using spring factory or applciation context
//
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//
//        // create a reader to pasrse the xml metatdata
//
//        // load thge configuration file into the facotry
//
//        // application context
//
//        // create a spring container
        ApplicationContext context = new ClassPathXmlApplicationContext("myConfiguration.xml");
//
//        BaseBallCoach coach = context.getBean("bbCoach", BaseBallCoach.class);
//        System.out.println(coach.getDailyWorkout());
//
//        SwimCoach swimCoach = context.getBean("swCoach", SwimCoach.class);
//        System.out.println(swimCoach.getDailyWorkout());
//
//        // student1 was built via constructor injection - values set at object creation time
//        Student student1 = context.getBean("student1", Student.class);
//        System.out.println(student1);
//
//        // student2 was built via setter injection - values set after object creation, via setX() calls
//        Student student2 = context.getBean("student2", Student.class);
//        System.out.println(student2);

        Student student1 = context.getBean("student1", Student.class);
        Student student2 = context.getBean("student2", Student.class);
        System.out.println(student1);
        System.out.println(student2);




    }
}
