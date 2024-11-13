package org.example.practicekotlin.entity;

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "todo") // 데이터베이스의 todo 테이블과 매핑
class Todo(
        @Id
        var id: Long? = null, // 기본 키 설정
        var title: String, // 할 일 제목
        var content: String // 할 일 내용
) {
        fun update(
                title: String,
                content: String
        ) {
                this.title = title // 제목 업데이트
                this.content = content // 내용 업데이트
        }
}

