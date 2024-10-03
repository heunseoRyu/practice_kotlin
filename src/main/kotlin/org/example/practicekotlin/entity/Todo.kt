package org.example.practicekotlin.entity;

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "todo")
class Todo(
        @Id
        var id: Long? = null,
        var title: String,
        var content: String
){
        fun update(
                title: String,
                content: String
        ){
                this.title = title
                this.content = content
        }
}
