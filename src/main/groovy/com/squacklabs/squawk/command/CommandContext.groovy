package com.squacklabs.squawk.command

import org.springframework.boot.ApplicationArguments

enum CommandResult {
    SUCCESS,
    FAILURE

    String errorMessage

    CommandResult(String errorMessage = null) {
        this.errorMessage = errorMessage
    }

}

class CommandContext {
    ApplicationArguments args
    CommandResult result

    CommandContext(ApplicationArguments args) {
        this.args = args
    }
}
