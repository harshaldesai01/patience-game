package util;

import exceptions.InvalidCommandException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.CommonConstants.DRAW_COMMAND;
import static util.CommonConstants.MOVE_COMMAND_REGEX;
import static util.CommonConstants.QUIT_COMMAND;

public class CommandParser {
    private final Scanner scanner;

    public CommandParser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Utility method to get user input using command parser
     * @return input taken by the scanner
     */
    public String getUserCommand() {
        System.out.println("Commands: Q to quit, D to draw a card, move format <source><target> or <source><target><number> | P = Uncovered Pile, Lanes = 1-7, Foundations = C, D, H, S");
        return scanner.nextLine().trim();
    }

    /**
     * Utility method to parse command String and return appropriate command object
     * @param command command string input
     * @return Command object based on type
     */
    public Command parseCommand(String command) {
        command = command.toUpperCase();

        // Handle quit command
        if(command.equals(QUIT_COMMAND)) {
            return new Command(CommandType.QUIT);
        }

        if(command.equals(DRAW_COMMAND)) {
            return new Command(CommandType.DRAW);
        }

        // Regex for moving cards
        Pattern pattern = Pattern.compile(MOVE_COMMAND_REGEX);
        Matcher matcher = pattern.matcher(command);
        if(matcher.matches()) {
            char source = command.charAt(0);
            char target = command.charAt(1);
            int numberOfCards = (command.length() == 3)? Character.getNumericValue(command.charAt(2)) : 1;

            return new Command(CommandType.MOVE, source, target, numberOfCards);
        }

        // If command does not match any expected patterns
        throw new InvalidCommandException("Please enter a valid command.");
    }

    /**
     * Utility method to display error message
     * @param message exception message
     */
    public void displayError(String message) {
        System.out.println("Error while parsing command: " + message);
    }

    /**
     * Utility method to close scanner of the command parser
     */
    public void close() {
        scanner.close();
    }
}
