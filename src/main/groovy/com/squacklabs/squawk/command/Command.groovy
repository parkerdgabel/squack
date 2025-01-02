package com.squacklabs.squawk.command

interface Command {
    String getName()
    void execute(CommandContext context)
}