package org.example.practicekotlin.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.practicekotlin.dto.TodoRequest
import org.example.practicekotlin.dto.TodoResponse
import org.example.practicekotlin.entity.Todo
import org.example.practicekotlin.entity.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class TodoService(
    val todoRepository: TodoRepository
) {

    suspend fun todoInfo(id: Long): Todo {
        val todo: Todo? = todoRepository.findById(id)
        if(todo !=null)
            return todo
        else
            throw Exception()
    }

    suspend fun create(todoRequest: TodoRequest): Todo =
        todoRepository.save(todoRequest.toEntity())

    suspend fun modify(id: Long,todoRequest: TodoRequest){
        val todo = todoRepository.findById(id)
        todo?.update(todoRequest.title,todoRequest.content)
    }

    suspend fun delete(id: Long) =
        todoRepository.deleteById(id)

    fun todoList(): Flow<TodoResponse> {
        return todoRepository.findAll().map(TodoResponse::invoke)
    }


}