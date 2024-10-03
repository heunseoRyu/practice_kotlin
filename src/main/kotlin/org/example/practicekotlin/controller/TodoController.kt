package org.example.practicekotlin.controller

import org.example.practicekotlin.dto.TodoRequest
import org.example.practicekotlin.dto.TodoResponse
import org.example.practicekotlin.service.TodoService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController(
    val todoService: TodoService
) {

    @GetMapping("/todos")
    suspend fun getList() =
        todoService.todoList()

    @GetMapping("/todos/{id}")
    suspend fun get(@PathVariable(name="id") id:Long) =
        TodoResponse(todoService.todoInfo(id))

    @PostMapping("/todos")
    suspend fun create(@RequestBody todoRequest: TodoRequest) =
        TodoResponse(todoService.create(todoRequest))

    @PatchMapping("/todos/{id}")
    suspend fun modify(@PathVariable(name="id") id:Long,@RequestBody todoRequest: TodoRequest) =
        todoService.modify(id,todoRequest)

    @DeleteMapping("/todos/{id}")
    suspend fun delete(@PathVariable(name="id") id:Long) =
        todoService.delete(id)

}