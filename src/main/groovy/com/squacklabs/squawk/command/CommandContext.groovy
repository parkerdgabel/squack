package com.squacklabs.squawk.command

import groovy.transform.CompileStatic
import org.springframework.boot.ApplicationArguments

@CompileStatic
enum CommandResult {
    SUCCESS,
    FAILURE

    String errorMessage

    CommandResult(String errorMessage = null) {
        this.errorMessage = errorMessage
    }
}

@CompileStatic
class CommandContext {
    ApplicationArguments args
    CommandResult result
    Map<String, String> options = [:]
    List<String> attachments = []
    String systemPrompt
    boolean continueConversation = false
    String conversationId

    CommandContext(ApplicationArguments args) {
        this.args = args
    }

    static String readFromStandardInput() {
        System.in.withReader {
            it.readLine()
        }
    }
}
