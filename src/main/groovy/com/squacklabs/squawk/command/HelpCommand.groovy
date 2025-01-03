package com.squacklabs.squawk.command

class HelpCommand implements Command {
    private static final String NAME = "help"

    @Override
    void execute(CommandContext context) {
        println "HelpCommand executed"
    }

    @Override
    String getName() {
        return NAME
    }
}
