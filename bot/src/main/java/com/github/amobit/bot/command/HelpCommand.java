package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.amobit.bot.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand extends Command {

    public static final String HELP_MESSAGE = String.format("✨<b>Доступные команды</b>✨\n\n"
                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - поиск книг по автору, укажите автора через пробел после команды\n"
                    + "%s - поиск книг по автору, укажите жанр через пробел после команды\n"
                    + "%s - получить список топ 100 книг\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(),
            FIND_AUTHOR.getCommandName(),
            TOP_100.getCommandName(),
            FIND_GENRE.getCommandName(),
            STOP.getCommandName(),
            HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        super(sendBotMessageService);
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
