package com.squacklabs.squawk.entities

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

import java.time.LocalDateTime

@CompileStatic
enum SenderType {
    AI,
    USER
}

@CompileStatic
@Entity
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false)
    String content

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    SenderType senderType

    @ManyToOne
    @JoinColumn(name = "chat_id")
    Chat chat

    @Column(nullable = false)
    LocalDateTime timestamp = LocalDateTime.now()
}
