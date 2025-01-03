package com.squacklabs.squawk.command

import com.squacklabs.squawk.Squack
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import javax.inject.Inject

@CompileStatic
@Component
class PromptCommand implements Command{

    private final Logger log = LoggerFactory.getLogger(PromptCommand.class)

    private static final String NAME = "prompt"

    private final Squack squack

    @Inject
    PromptCommand(Squack squack) {
        this.squack = squack
    }

    @Override
    void execute(CommandContext context) {
        def prompt = context.args.nonOptionArgs.first()
        log.debug("PromptCommand executed with prompt: {}", prompt)

        // Handle options
        if (context.args.containsOption("no-stream")) {
            println "Streaming is disabled"
        }

        if (context.args.containsOption("model") || context.args.containsOption("m")) {
            def model = context.args.getOptionValues("model").first()
        }

        if (context.args.containsOption("option") || context.args.containsOption("o")) {
            context.options = context.args.getOptionValues("option")
        }

        if (context.args.containsOption("attachment") || context.args.containsOption("a")) {
            context.attachments = context.args.getOptionValues("attachment")
        }

        if (context.args.containsOption("system") || context.args.containsOption("s")) {
            context.systemPrompt = context.args.getOptionValues("system").first()
        }

        if (context.args.containsOption("continue") || context.args.containsOption("c")) {
            context.continueConversation = true
        }

        if (context.args.containsOption("conversation")) {
            context.conversationId = context.args.getOptionValues("conversation").first()
        }

        println "PromptCommand executed with prompt: $prompt"
    }

    @Override
    String getName() {
        return NAME
    }
}
