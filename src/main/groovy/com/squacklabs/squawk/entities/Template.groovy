package com.squacklabs.squawk.entities

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@CompileStatic
@Entity
class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(nullable = false)
    String name
    @Column(nullable = false)
    String model
    @Column
    String systemPrompt
}
