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

@RestController // REST 컨트롤러로 등록
class TodoController(
    val todoService: TodoService // TodoService 주입
) {

    @GetMapping("/todos")
    suspend fun getList() =
        todoService.todoList() // 전체 Todo 목록 조회

    @GetMapping("/todos/{id}")
    suspend fun get(@PathVariable(name = "id") id: Long) =
        TodoResponse(todoService.todoInfo(id)) // 특정 id의 Todo 조회 후 TodoResponse로 반환

    @PostMapping("/todos")
    suspend fun create(@RequestBody todoRequest: TodoRequest) =
        TodoResponse(todoService.create(todoRequest)) // 새로운 Todo 생성 후 TodoResponse로 반환

    @PatchMapping("/todos/{id}")
    suspend fun modify(@PathVariable(name = "id") id: Long, @RequestBody todoRequest: TodoRequest) =
        todoService.modify(id, todoRequest) // 특정 id의 Todo 수정

    @DeleteMapping("/todos/{id}")
    suspend fun delete(@PathVariable(name = "id") id: Long) =
        todoService.delete(id) // 특정 id의 Todo 삭제
}