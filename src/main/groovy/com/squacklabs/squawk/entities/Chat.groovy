package com.squacklabs.squawk.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Chat parent

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    List<Chat> children = new ArrayList<>()

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    List<Message> messages = new ArrayList<>()

}
