package org.example.practicekotlin.dto

import org.example.practicekotlin.entity.Todo

data class TodoRequest(
    val title: String, // 할 일 제목
    val content: String = "" // 할 일 내용, 기본값은 빈 문자열
) {
    fun toEntity(): Todo {
        return Todo(
            title = title,
            content = content
        ) // TodoRequest를 Todo 엔티티로 변환
    }
}

data class TodoResponse(
    val id: Long, // 할 일의 ID
    val title: String, // 할 일 제목
    val content: String // 할 일 내용
) {
    companion object {
        operator fun invoke(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id!!, // nullable id를 non-null로 변환, null일 경우 에러 처리 필요
                // id는 auto increment로 이미 할당되어 있어 null이 아니어야 함
                title = todo.title,
                content = todo.content
            ) // Todo 엔티티를 TodoResponse로 변환
        }
    }
}