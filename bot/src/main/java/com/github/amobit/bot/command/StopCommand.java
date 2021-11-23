package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */
public class StopCommand extends Command {

    public static final String STOP_MESSAGE = "До следующей встречи!";

    public StopCommand(SendBotMessageService sendBotMessageService) {
        super(sendBotMessageService);
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
