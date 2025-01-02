package com.squacklabs.squawk

import com.squacklabs.squawk.command.Command
import com.squacklabs.squawk.command.CommandContext
import groovy.transform.CompileStatic
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

import javax.inject.Inject

@CompileStatic
@Component
class CliApp implements ApplicationRunner {

    private final Squack squack
    private final List<Command> commands

    @Inject
    CliApp(Squack squack, List<Command> commands) {
        this.squack = squack
        this.commands = commands
    }

    private static final String helpMessage = """
Usage: java -jar squawk.jar [prompt] [options] [command] [command options]
Commands:
  template        Manage prompt templates
  completion      Generate shell completion scripts
  embed          Embed a prompt
  
Options:
  --help       Show this help message
  """

    @Override
    void run(ApplicationArguments args) throws Exception {
        if (args.containsOption "help") {
            println helpMessage
        } else if (args.containsOption "name") {
            String name = args.getOptionValues("name").get(0)
            println "Hello, ${name}!"
        } else {
            println "No valid options provided. Use --help for usage information."
        }
    }

    private CommandContext executeCommand(ApplicationArguments args) {
        CommandContext context = new CommandContext()
        String commandName = args.getNonOptionArgs().get(0)
        Command command = commands.find { it.getName() == commandName }
        if (command) {
            command.execute(context)
        } else {
            println "Command not found: ${commandName}"
        }
        return context
    }
}
