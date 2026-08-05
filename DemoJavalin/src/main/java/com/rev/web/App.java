package com.rev.web;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        //create a new javalin instance and start it on a port and start it on that port
        Javalin app = Javalin.create().start(8080);

        // defined a GET endpoint, if i hit the end point it should return hello workd
        // whenver i hit the path, give me hello world?
        app.get("/", ctx -> ctx.result("Hello World!"));

        app.get("/hello", ctx -> ctx.result("Hello World! again"));

        // we cam use post and get etc http method


        // i need to send data tp server
        // path paramet {name}
        app.get("/user/{name}", ctx ->{
            String name = ctx.pathParam("name");
            ctx.result("Hello again" + name.toUpperCase());
        });

        // query parameters
        app.get("/user", ctx -> {
            String name = ctx.queryParam("name");
            String age = ctx.queryParam("age");

            ctx.result("Hello Mate" + name.toUpperCase() + "your age is " + age);
        });


    }
}
