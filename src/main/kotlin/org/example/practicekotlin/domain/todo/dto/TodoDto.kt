package org.example.practicekotlin.domain.todo.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.example.practicekotlin.domain.todo.entity.Todo


data class TodoRequest @JsonCreator constructor(
    @JsonProperty("title") val title: String,
    @JsonProperty("content") val content: String
){
    fun toEntity(): Todo {
        return Todo(
            title = title,
            content = content
        )
    }
}

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String
){
    companion object {
        operator fun invoke(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id!!,
                /* nullable 변수를 강제로 null이 아니라고 선언 ->
                 auto increment 로 인해 이미 id가 할당된 이후 이므로
                 null이면 안되다 따라서 에러 처리를 해야 하기 때문에 */
                title = todo.title,
                content = todo.content
            )
        }
    }
}