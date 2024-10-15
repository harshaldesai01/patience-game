package util;

/**
 * Class containing common constants used across the game
 */
public final class CommonConstants {
    public static final String QUIT_COMMAND = "Q";
    public static final String DRAW_COMMAND = "D";
    public static final String UNCOVERED_PILE = "P";
    public static final String WELCOME_MESSAGE = "Welcome to the Patience Game! Enter your commands (Q to quit):";
    public static final String QUIT_MESSAGE = "You've quit the game. Thanks for playing!";
    public static final String GAME_WON_MESSAGE = "CONGRATULATIONS! YOU WON THE GAME!";

    /**
     * Regular expression for validating Patience game move commands.
     * Matches commands in the format:
     *   Label1 (P or 1-7) followed by Label2 (1-7 or D, H, S, C) and an optional number (1-13).
     * Example: Valid commands include "P1D", "235", and "3C".
     */
    public static final String MOVE_COMMAND_REGEX = "^([P1-7][1-7DCHS]([1-9]|1[0-3])?)$";
    public static final int FOUNDATION_CARD_COUNT = 13;
    public static final int SCORE_MOVE_WASTE_TO_FOUNDATION = 10;
    public static final int SCORE_MOVE_LANE_TO_FOUNDATION = 20;
    public static final int SCORE_MOVE_BETWEEN_LANES = 5;
    public static final int NUMBER_OF_LANES = 7;
    public static final int MAX_HISTORY_SIZE = 50;
}
