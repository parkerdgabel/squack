package com.squacklabs.squawk

import com.squacklabs.squawk.command.Command
import com.squacklabs.squawk.command.CommandContext
import com.squacklabs.squawk.command.CommandResult
import groovy.transform.CompileStatic
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.inject.Named

@CompileStatic
@Component
class CliApp implements ApplicationRunner {

    private final Squack squack
    private final Command mainCommand

    @Inject
    CliApp(Squack squack, @Named Command mainCommand) {
        this.squack = squack
        this.mainCommand = mainCommand
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
        if (args.containsOption("help") || args.sourceArgs.length == 0) {
            println helpMessage
            return
        }

        CommandContext context = executeCommand(args)
        if (context.result == CommandResult.FAILURE) {
            println "Error: ${context.result.errorMessage}"
        }
    }

    private CommandContext executeCommand(ApplicationArguments args) {
        CommandContext context = new CommandContext(args)
        mainCommand.execute(context)
        context
    }
}
