package com.squacklabs.squawk.plugin

import com.squacklabs.squawk.Squack
import org.springframework.ai.model.Model
import org.springframework.ai.model.ModelDescription
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaModel
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.stereotype.Component

@Component
class OllamaPlugin implements Plugin {
    @Override
    void apply(Squack squack, PluginOption... options) {
        def opt = options.inject(new PluginOptions()) { opts, option ->
            option.apply(opts)
        }
        OllamaApi ollamaApi = Optional
                .ofNullable(opt.options['baseUrl'])
                .map { new OllamaApi(it) }
                .orElse(new OllamaApi())
        def models = ollamaApi.listModels().models()
        models.each { model ->
            OllamaChatModel ollamaModel = new OllamaChatModel(
                    ollamaApi,
                    OllamaOptions
                            .create()
                            .model(model))
            squack.registerModel(ollamaModel as Model, model.model())
        }
    }
}
