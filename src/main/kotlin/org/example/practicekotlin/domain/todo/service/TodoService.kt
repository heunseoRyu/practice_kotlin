package org.example.practicekotlin.domain.todo.service

import com.fasterxml.jackson.databind.ser.Serializers.Base
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.practicekotlin.domain.todo.dto.TodoRequest
import org.example.practicekotlin.domain.todo.dto.TodoResponse
import org.example.practicekotlin.domain.todo.entity.Todo
import org.example.practicekotlin.domain.todo.entity.TodoRepository
import org.example.practicekotlin.global.common.BaseResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class TodoService(
    val todoRepository: TodoRepository
) {

    fun todoInfo(id: Long): Todo {
        val todo: Todo = todoRepository.findById(id).get()
        if(todo !=null)
            return todo
        else
            throw Exception()
    }

    fun create(todoRequest: TodoRequest): BaseResponse<Todo> =
        BaseResponse(message = "할일 등록 성공",data = todoRepository.save(todoRequest.toEntity()))

    fun modify(id: Long,todoRequest: TodoRequest){
        val todo = todoRepository.findById(id).get()
        todo.title = todoRequest.title
        todo.content = todoRequest.content
    }

    fun delete(id: Long) =
        todoRepository.deleteById(id)

    fun todoList(): BaseResponse<List<TodoResponse>> {
        val result = todoRepository.findAll().map(TodoResponse::invoke)
        return BaseResponse(
            message = "할일 리스트 조회 성공",
            data = result
        )
    }


}