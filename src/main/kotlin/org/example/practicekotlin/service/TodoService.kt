package org.example.practicekotlin.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.practicekotlin.dto.TodoRequest
import org.example.practicekotlin.dto.TodoResponse
import org.example.practicekotlin.entity.Todo
import org.example.practicekotlin.entity.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service // TodoService 클래스는 서비스 레이어를 나타내며, 스프링에서 관리되는 빈으로 등록
@Transactional(rollbackFor = [Exception::class]) // 트랜잭션 설정을 적용하고, Exception 발생 시 롤백되도록 설정
class TodoService(
    val todoRepository: TodoRepository // TodoRepository를 주입받아 데이터베이스 연산을 수행할 수 있게 설정
) {

    suspend fun todoInfo(id: Long): Todo {
        // 주어진 id로 Todo 객체를 조회
        val todo: Todo? = todoRepository.findById(id)
        if (todo != null)
            return todo // Todo 객체가 존재하면 반환
        else
            throw Exception() // 존재하지 않으면 예외 발생
    }

    suspend fun create(todoRequest: TodoRequest): Todo =
        todoRepository.save(todoRequest.toEntity()) // 새로운 Todo 객체를 생성하고 저장

    suspend fun modify(id: Long, todoRequest: TodoRequest) {
        val todo = todoRepository.findById(id) // 주어진 id로 Todo 객체를 조회
        todo?.update(todoRequest.title, todoRequest.content) // Todo 객체가 존재할 경우 제목과 내용을 업데이트
    }

    suspend fun delete(id: Long) =
        todoRepository.deleteById(id) // 주어진 id에 해당하는 Todo 객체를 삭제

    fun todoList(): Flow<TodoResponse> {
        return todoRepository.findAll().map(TodoResponse::invoke) // 모든 Todo 객체를 조회하고, TodoResponse 형태로 변환
    }
}
