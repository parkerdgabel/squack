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
            readLine()
        }
    }
}
