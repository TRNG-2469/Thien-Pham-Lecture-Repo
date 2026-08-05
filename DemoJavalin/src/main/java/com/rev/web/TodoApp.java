package com.rev.web;

import com.rev.web.controller.TodoController;
import com.rev.web.dao.TodoDao;
import com.rev.web.exceptions.ErrorResponses;
import com.rev.web.exceptions.NotFoundResponse;
import com.rev.web.service.TodoService;
import io.javalin.Javalin;

public class TodoApp {

    public static void main(String[] args) {
        TodoDao todoDao = new TodoDao();
        TodoService todoService = new TodoService(todoDao);
        TodoController todoController = new TodoController(todoService);

        // Routes and exception handlers must be registered before the server starts.
        Javalin app = Javalin.create();

        app.get("/api/todo", todoController::getAllTodos);
        app.get("/api/todo/{id}", todoController::getTodoById);
        // delete todos by id
        app.delete("/api/todos/{id}", todoController::deleteTodoById);
        // Create a todo.
        app.post("/api/todos", todoController::createTodo);
        // update data, should take param to update description
        app.put("/api/todos/{id}", todoController::updateTodoById);



        // Centralized exception handling.
        app.exception(IllegalArgumentException.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.json(new ErrorResponses("An unexpected error occurred."));
        });

        app.exception(NotFoundResponse.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.json(new ErrorResponses(exception.getMessage()));
        });

        app.exception(Exception.class, (exception, ctx) -> {
            ctx.status(500);
            ctx.json(new ErrorResponses("An unexpected server error occurred."));
        });

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        app.start(port);

    }
}
