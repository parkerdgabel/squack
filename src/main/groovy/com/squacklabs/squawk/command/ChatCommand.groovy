package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@CompileStatic
@Component
class ChatCommand implements Command{

    private static final String NAME = "chat"

    @Override
    void execute(CommandContext context) {
        println "ChatCommand executed"
    }

    @Override
    String getName() {
        return NAME
    }
}
