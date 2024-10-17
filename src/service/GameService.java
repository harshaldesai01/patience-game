package service;

import exceptions.InvalidCommandException;
import exceptions.InvalidMoveException;
import model.Card;
import model.Deck;
import model.Foundation;
import model.Lane;
import model.Pile;
import model.StockPile;
import model.WastePile;
import model.dto.GameStateDTO;
import model.enums.Suite;
import util.Command;
import util.CommandType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static util.CommonConstants.UNCOVERED_PILE;

/**
 * The GameService class manages the core game logic, including card movements,
 * score updates, and checking game state. It handles commands like drawing and moving cards.
 */
public class GameService {
    private final StockPile stockPile;
    private final WastePile wastePile;
    private final ArrayList<Lane> lanes;
    private final Map<Suite, Foundation> foundations;
    private final DisplayService displayService;
    private final MoveValidator moveValidator;
    private final ScoreManager scoreManager;
    private int moveCount;

    public GameService() {
        Deck deck = new Deck();
        wastePile = new WastePile();
        scoreManager = new ScoreManager();
        moveCount = 0;

        GameInitializer gameInitializer = new GameInitializer();

        // Initialize game components
        lanes = gameInitializer.initializeLanes();
        foundations = Collections.unmodifiableMap(gameInitializer.initializeFoundations());
        gameInitializer.dealInitialCards(deck, lanes);

        stockPile = new StockPile(deck.getRemainingCards());
        deck.clear();

        displayService = new DisplayService();
        moveValidator = new MoveValidator(wastePile);
    }

    /**
     * Checks if all foundation piles are complete
     * @return true if all foundation piles are complete
     */
    public boolean isGameOver() {
        return foundations.values().stream().allMatch(Foundation::isComplete);
    }

    /**
     * DRAW or MOVE the Cards based on parsedCommand
     * @param command Command given by the parser
     */
    public void executeCommand(Command command) {
        switch (command.getType()) {
            case CommandType.DRAW -> drawCardFromStockPile();
            case CommandType.MOVE -> executeMoveCommand(command);
            default -> throw new InvalidCommandException("Unknown command!");
        }
    }

    /**
     * Method to draw card from stock pile and move to waste pile, replenish it from waste pile if empty
     */
    public void drawCardFromStockPile() {
        if(!stockPile.isEmpty()) {
            Card drawnCard = stockPile.drawCard();
            drawnCard.flip();
            wastePile.addCard(drawnCard);
            moveCount++;
        } else {
            handleEmptyStockPile();
        }
    }

    /**
     * Replenish stock from waste pile or throw invalid move exception if both are empty
     */
    private void handleEmptyStockPile() {
        if (!wastePile.isEmpty()) {
            replenishStockFromWaste();
            drawCardFromStockPile();
        } else {
            throw new InvalidMoveException("No more cards to draw! Both Stock and Waste Pile are empty.");
        }
    }

    /**
     * Replenishes the stock pile from the waste pile.
     */
    private void replenishStockFromWaste() {
        System.out.println("....Replenishing Stock Pile from Waste Pile...");
        stockPile.replenishFromWastePile(wastePile);
    }

    /**
     * Moves cards from the source pile to the target pile based on the given command.
     * @param command The command containing move details.
     */
    private void executeMoveCommand(Command command) {
        Pile sourcePile = getPileFromLocation(String.valueOf(command.getSource()));
        Pile targetPile = getPileFromLocation(String.valueOf(command.getTarget()));
        int numberOfCards = command.getNumberOfCards();

        moveValidator.validate(sourcePile, targetPile, numberOfCards);

        Stack<Card> cardsToMove = new Stack<>();
        for (int i = 0; i < numberOfCards; i++) {
            cardsToMove.push(sourcePile.removeCard());
        }

        if(targetPile.canAddCard(cardsToMove.peek())){
            moveCardsToTargetPile(targetPile, cardsToMove, sourcePile);
        } else {
            revertCardMove(sourcePile, cardsToMove);
            throw new InvalidMoveException("Move not possible! Try another move.");
        }
        moveCount++;
    }

    /**
     * Moves the cards to the target pile and updates the score.
     *
     * @param targetPile The target pile to which cards will be moved.
     * @param cardsToMove The stack of cards to move.
     * @param sourcePile The source pile from which cards are removed.
     */
    private void moveCardsToTargetPile(Pile targetPile, Stack<Card> cardsToMove, Pile sourcePile) {
        List<Card> cardsMoved = new ArrayList<>(); // To store the cards being moved

        while(!cardsToMove.isEmpty()){
            Card movedCard = cardsToMove.pop();
            targetPile.addCard(movedCard);
            cardsMoved.add(movedCard);
        }

        // Update the score with the source, target, and cards moved
        scoreManager.updateScore(sourcePile, targetPile, cardsMoved);

        // flip next card in the lane
        if(sourcePile instanceof Lane && !sourcePile.isEmpty()) {
            ((Lane) sourcePile).faceUpTopCard();
        }
    }

    /**
     * Reverts the card move by adding cards back to the source pile.
     *
     * @param sourcePile The source pile to which cards will be returned.
     * @param cardsToMove The stack of cards to revert.
     */
    private void revertCardMove(Pile sourcePile, Stack<Card> cardsToMove) {
        while (!cardsToMove.isEmpty()) {
            sourcePile.addCard(cardsToMove.pop());
        }
    }

    /**
     * Identify which pile is given based on given location in the command
     * @param location location in command
     * @return The appropriate instance of Pile, Foundation or Lane
     */
    private Pile getPileFromLocation(String location) {
        if(UNCOVERED_PILE.equals(location)){
            return wastePile;
        }
        if(Character.isDigit(location.charAt(0))) {
            int laneIndex = Integer.parseInt(location) - 1;
            // Get the actual using number
            if(laneIndex >= 0 && laneIndex < lanes.size()) {
                return lanes.get(laneIndex);
            }
        } else if (Suite.getAllSymbols().contains(location.charAt(0))) {
            return getFoundationPile(location.charAt(0));
        }
        throw new InvalidCommandException("Invalid pile location: " + location);
    }

    /**
     * Get the appropriate Foundation pile based on suite symbol
     * @param suiteSymbol suite symbol
     * @return instance of the appropriate foundation pile
     */
    private Foundation getFoundationPile(char suiteSymbol) {
        Suite suite = Suite.fromSymbol(suiteSymbol);
        return foundations.get(suite);
    }

    /**
     * Display current state of the game using GameStateDTO
     */
    public void displayGameState() {
        GameStateDTO gameStateDTO = getGameStateDTO();
        displayService.displayCurrentState(gameStateDTO);
    }

    /**
     * Create game state using the required game state variables
     * @return current game state
     */
    private GameStateDTO getGameStateDTO() {
        return new GameStateDTO(scoreManager.getScore(), moveCount, wastePile, stockPile, lanes, foundations);
    }
}