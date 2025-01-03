package com.squacklabs.squawk

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import org.springframework.ai.model.Model
import org.springframework.stereotype.Component

interface Squack {
    void registerModel(Model model, String name)
    void registerModel(Closure closure)
}

@Canonical
@CompileStatic
@Builder
@Component
class SquackImpl implements Squack {

    private final ModelRegistry modelRegistry

    SquackImpl(ModelRegistry modelRegistry) {
        this.modelRegistry = modelRegistry
    }

    @Override
    void registerModel(Model model, String name) {
        modelRegistry.registerModel(model, name)
    }

    @Override
    void registerModel(Closure closure) {
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }
}