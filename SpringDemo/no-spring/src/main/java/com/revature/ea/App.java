package com.revature.ea;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        System.out.println(context.getBean("employee1", Employee.class));
        System.out.println(context.getBean("address1", Address.class));

    }
}
