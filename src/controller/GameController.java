package controller;

import exceptions.InvalidCommandException;
import exceptions.InvalidMoveException;
import service.GameService;
import util.Command;
import util.CommandParser;
import util.CommandType;

import static util.CommonConstants.GAME_WON_MESSAGE;
import static util.CommonConstants.QUIT_MESSAGE;
import static util.CommonConstants.WELCOME_MESSAGE;

/**
 * Class to control the game flow
 */
public class GameController {
    private final GameService gameService;
    private final CommandParser commandParser;

    public GameController() {
        this.gameService = new GameService();
        this.commandParser = new CommandParser();
    }

    /**
     * Method containing logic for the game flow. Take input from user & execute the command
     */
    public void startGame() {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            gameService.displayGameState();
            try {
                Command command = commandParser.parseCommand(commandParser.getUserCommand());
                if (command.getType() == CommandType.QUIT) {
                    System.out.println(QUIT_MESSAGE);
                    break;
                }

                gameService.executeCommand(command);

                if (gameService.isGameOver()) {
                    gameService.displayGameState();
                    System.out.println(GAME_WON_MESSAGE);
                    break;
                }
            } catch (InvalidCommandException e) {
                commandParser.displayError(e.getMessage());
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }

        commandParser.close();
    }
}
