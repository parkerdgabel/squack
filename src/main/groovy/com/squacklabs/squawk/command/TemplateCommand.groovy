package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@CompileStatic
@Component
class TemplateCommand implements Command{

    private static final String NAME = "template"

    @Override
    void execute(CommandContext context) {
        println "TemplateCommand executed"
    }

    @Override
    String getName() {
        return NAME
    }
}
