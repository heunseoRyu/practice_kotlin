package org.example.practicekotlin.domain.todo.controller

import org.example.practicekotlin.domain.todo.dto.TodoRequest
import org.example.practicekotlin.domain.todo.dto.TodoResponse
import org.example.practicekotlin.domain.todo.service.TodoService
import org.example.practicekotlin.global.common.BaseResponse
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
    fun getList() =
        todoService.todoList()

    @GetMapping("/todos/{id}")
    fun get(@PathVariable(name="id") id:Long) =
        BaseResponse( message="할일 조회 성공",data = TodoResponse(todoService.todoInfo(id)))

    @PostMapping("/todos")
    fun create(@RequestBody todoRequest: TodoRequest) =
        todoService.create(todoRequest)

    @PatchMapping("/todos/{id}")
    fun modify(@PathVariable(name="id") id:Long,@RequestBody todoRequest: TodoRequest) =
        todoService.modify(id,todoRequest)

    @DeleteMapping("/todos/{id}")
    fun delete(@PathVariable(name="id") id:Long) =
        todoService.delete(id)

}