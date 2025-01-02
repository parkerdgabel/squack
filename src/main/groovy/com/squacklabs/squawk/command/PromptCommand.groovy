package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@CompileStatic
@Component
class PromptCommand implements Command{

    private static final String NAME = "prompt"

    @Override
    void execute(CommandContext context) {
        println "PromptCommand executed"
    }

    @Override
    String getName() {
        return NAME
    }
}
