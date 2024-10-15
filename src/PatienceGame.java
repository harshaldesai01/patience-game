import controller.GameController;

/**
 * The PatienceGame class serves as the entry point for the Patience game application.
 * It initializes and starts the game by delegating control to the GameController.
 */
public class PatienceGame {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        try {
            gameController.startGame();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}