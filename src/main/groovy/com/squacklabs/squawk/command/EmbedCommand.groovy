package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@CompileStatic
@Component
class EmbedCommand implements Command{
    private static final String NAME = "embed"

    @Override
    void execute(CommandContext context) {
        println "EmbedCommand executed"
    }

    @Override
    String getName() {
        return NAME
    }
}
