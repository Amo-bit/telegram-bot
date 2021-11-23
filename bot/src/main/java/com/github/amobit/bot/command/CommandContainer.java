package com.github.amobit.bot.command;

import com.github.amobit.bot.command.genge.*;
import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.service.BookService;
import com.google.common.collect.ImmutableMap;

import static com.github.amobit.bot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, BookService bookService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(FIND_AUTHOR.getCommandName(), new FindAuthorCommand(sendBotMessageService, bookService))
                .put(FIND_GENRE.getCommandName(), new FindGenreCommand(sendBotMessageService, bookService))
                .put(TOP_100.getCommandName(), new TopCommand(sendBotMessageService, bookService))
                .put(CLASSIC_BOOK.getCommandName(), new ClassicBookCommand(sendBotMessageService, bookService))
                .put(RUSSIAN_BOOK.getCommandName(), new RussianBookCommand(sendBotMessageService, bookService))
                .put(FOREIGN_BOOK.getCommandName(), new ForeignBookCommand(sendBotMessageService, bookService))
                .put(BUSINESS_BOOK.getCommandName(), new BusinessBookCommand(sendBotMessageService, bookService))
                .put(SCIENTIFIC_BOOK.getCommandName(), new ScientificBookCommand(sendBotMessageService, bookService))
                .put(FANTASTIC_BOOK.getCommandName(), new FantasticBookCommand(sendBotMessageService, bookService))
                .put(FANTASY_BOOK.getCommandName(), new FantasyBookCommand(sendBotMessageService, bookService))
                .put(DETECTIVE_BOOK.getCommandName(), new DetectiveBookCommand(sendBotMessageService, bookService))
                .put(CHILDREN_BOOK.getCommandName(), new ChildrenBookCommand(sendBotMessageService, bookService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
