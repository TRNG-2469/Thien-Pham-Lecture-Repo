package com.revature.ce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    System.out.println(context.getBean("car1", Car.class));
    System.out.println(context.getBean("engine1", Engine.class));

}


}
