package com.rev.web.service;

import com.rev.web.dao.TodoDao;
import com.rev.web.model.Todo;

import java.util.List;

public class TodoService {
    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<Todo> getAllTodos() {
        return todoDao.findAll();
    }

    public Todo getTodoById(int id) {
        return todoDao.findById(id);
    }

    public Todo deleteTodoById(int id) {
        return todoDao.deleteById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoDao.save(todo);
    }

    public Todo updateTodoById(int id, Todo todo) {
        todo.setId(id);
        return todoDao.update(todo);
    }
}
