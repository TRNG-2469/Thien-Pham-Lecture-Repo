package com.rev.web.controller;

import com.rev.web.exceptions.NotFoundResponse;
import com.rev.web.model.Todo;
import com.rev.web.service.TodoService;
import io.javalin.http.Context;

import java.util.List;

public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public void getAllTodos(Context ctx) {
        List<Todo> todos = todoService.getAllTodos();
        ctx.json(todos);
    }

    public void getTodoById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Todo todo = todoService.getTodoById(id);

        if (todo == null) {
            throw new NotFoundResponse("Todo with id " + id + " not found");
        }

        ctx.json(todo);
    }

    public void deleteTodoById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Todo deletedTodo = todoService.deleteTodoById(id);

        if (deletedTodo == null) {
            throw new NotFoundResponse("Todo with id " + id + " not found");
        }

        ctx.status(204);
    }

    public void createTodo(Context ctx) {
        Todo todo = ctx.bodyAsClass(Todo.class);
        Todo createdTodo = todoService.createTodo(todo);
        ctx.status(201).json(createdTodo);
    }

    public void updateTodoById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Todo todo = ctx.bodyAsClass(Todo.class);
        Todo updatedTodo = todoService.updateTodoById(id, todo);

        if (updatedTodo == null) {
            throw new NotFoundResponse("Todo with id " + id + " not found");
        }

        ctx.json(updatedTodo);
    }
}
