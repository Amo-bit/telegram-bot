package com.github.amobit.bot.bot;


import com.github.amobit.bot.command.CommandContainer;
import com.github.amobit.bot.service.SendBotMessageServiceImpl;

import com.github.amobit.db.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.amobit.bot.command.CommandName.NO;

/**
 * Telegrambot for search new book.
 */
@Slf4j
@Component
public class SearchNewBookTelegrambot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    @Autowired
    public SearchNewBookTelegrambot(BookService bookService) {
        SendBotMessageServiceImpl sendBotMessageService = new SendBotMessageServiceImpl(this);
        this.commandContainer = new CommandContainer(sendBotMessageService, bookService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
