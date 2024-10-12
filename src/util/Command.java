package util;

/**
 * Command class containing type & source, target and numberOfCards in case of MOVE commandType
 */
public class Command {
    private final CommandType type;
    private final char source;
    private final char target;
    private final int numberOfCards;

    public Command(CommandType type, char source, char target, int numberOfCards) {
        this.type = type;
        this.source = source;
        this.target = target;
        this.numberOfCards = numberOfCards;
    }

    public Command(CommandType type) {
        this(type, ' ', ' ', 0);
    }

    public CommandType getType() {
        return type;
    }

    public char getSource() {
        return source;
    }

    public char getTarget() {
        return target;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
}
