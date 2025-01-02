package com.squacklabs.squawk

import org.springframework.ai.model.Model
import org.springframework.ai.model.ModelDescription
import org.springframework.stereotype.Component

@Component
class ModelRegistry {
    private final Map<String, Model> models = [:]

    void registerModel(Model model, String name) {
        models[name] = model
    }

    Model getModel(String name) {
        models[name]
    }
}
