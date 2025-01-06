package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.inject.Named

@CompileStatic
@Named
class MainCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(MainCommand)

    private static final String NAME = "main"

    private final Map<String, Command> commands

    @Inject
    MainCommand(List<Command> commands) {
        this.commands = commands.findAll { it.getName() != NAME }
        .inject(new LinkedHashMap<String, Command>()) { map, command ->
            map[command.getName()] = command
            map
        }
    }

    @Override
    void execute(CommandContext context) {
        log.debug("MainCommand executed")
        def nonOptionArgs = context.args.nonOptionArgs
        log.debug("Arguments: {}", context.args.nonOptionArgs)
        Optional.ofNullable(commands[nonOptionArgs?.first()])
        .ifPresentOrElse({ command ->
            command.execute(context)
        }, {
            commands["prompt"].execute(context)
        })
    }

    @Override
    String getName() {
        NAME
    }
}
