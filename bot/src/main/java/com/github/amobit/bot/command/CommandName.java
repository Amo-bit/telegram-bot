package com.github.amobit.bot.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("/no"),
    FIND_AUTHOR("/find_author"),
    FIND_GENRE("/find_genre"),
    TOP_100("/top_100"),
    DETECTIVE_BOOK("/detective_book"),
    CLASSIC_BOOK("classic_book"),
    FANTASTIC_BOOK("fantastic_book"),
    FANTASY_BOOK("fantasy_book"),
    RUSSIAN_BOOK("russian_book"),
    BUSINESS_BOOK("business_book"),
    CHILDREN_BOOK("children_book"),
    FOREIGN_BOOK("foreign_book"),
    SCIENTIFIC_BOOK("scientific_book");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}